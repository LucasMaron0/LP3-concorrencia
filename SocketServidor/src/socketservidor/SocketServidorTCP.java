package socketservidor;

import SingleBridge.SingleLaneBridge;
import Utils.ConfigReceiver;
import java.net.*;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

public class SocketServidorTCP {
    ServerSocket serverSocket;
    Socket clientSocket;
    PrintWriter out;
    BufferedReader in;
    String configString;

    public SocketServidorTCP(int porta) {
        try {
            /* Inicializacao do server socket TCP */
            serverSocket = new ServerSocket(porta);
            System.out.println("Servidor executando ... ");
            while (true) {
                /* Espera por um cliente */
                clientSocket = serverSocket.accept();
                System.out.println("Novo cliente: " + clientSocket.toString());
                
                /* Preparacao dos fluxos de entrada e saida */
                out = new PrintWriter(clientSocket.getOutputStream(), true);
                in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                
                /* Recuperacao dos comandos */
                while ((configString = in.readLine()) != null) {               
                    try{
                       ConfigReceiver configFarmer =  ConfigReceiver.getStringData(configString);
                       configFarmer.printData();
                        
                       SingleLaneBridge.startSingleBridge(out, configFarmer);
                    }catch(Exception ex){
                        System.out.println(ex);
                    }   
                    break;
                }
                /* Finaliza tudo */
                System.out.print("Cliente desconectando... ");
                out.close();
                in.close();
                clientSocket.close();
                System.out.println("ok");
            }
        } catch (IOException ex) {
            System.out.println(ex);
        }
    }
    
    public static void main(String[] args) throws Exception {
        SocketServidorTCP socketServidorTCP = new SocketServidorTCP(6789);
    }
}
