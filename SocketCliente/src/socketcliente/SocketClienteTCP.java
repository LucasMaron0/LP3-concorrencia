package socketcliente;

import Utils.ConfigSender;
import java.io.*;
import java.net.*;
import javax.swing.*;

public class SocketClienteTCP {
    private Socket socket;
    private BufferedReader in;
    private PrintWriter out;
    private BufferedReader inReader;
    private String mensagemEnviar;
    private Home home;

    public SocketClienteTCP(String servidor, int porta, Home home, ConfigSender configFarmers) {
        this.home = home;

        SwingWorker<Void, String> worker = new SwingWorker<Void, String>() {
            @Override
            protected Void doInBackground() throws Exception {
                try {
                    /* Inicialização de socket TCP */
                    socket = new Socket(servidor, porta);

                    /* Inicialização dos fluxos de entrada e saída */
                    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
                    out = new PrintWriter(socket.getOutputStream(), true);

                    /* Envio da mensagem */
                    out.println(configFarmers.generateStringData());

                    // Adiciona um loop para ler todas as linhas enviadas pelo servidor
                    String resposta;
                    publish("Resposta do servidor:");
                    while ((resposta = in.readLine()) != null) {
                        System.out.println(resposta);
                        publish(resposta);
                    }

                    publish("Operação concluída, encerrando conexão");

                } catch (IOException ex) {
                    System.out.println(ex);
                } 
                return null;
            }

            @Override
            protected void process(java.util.List<String> chunks) {
                // atualiza a interface do usuário com base nos chunks recebidos
                for (String chunk : chunks) {
                    home.updateOutput(chunk);
                }
            }

            @Override
            protected void done() {
                try {
                        if (out != null) out.close();
                        if (in != null) in.close();
                        if (socket != null) socket.close();
                } catch (Exception ex) {
                     System.out.println(ex);
                }
                    
            }
        };

        worker.execute();
    }

    public static void start(Home home, ConfigSender configFarmers) {
        SocketClienteTCP socketClienteTCP = new SocketClienteTCP("localhost", 6789, home, configFarmers);
    }
}
