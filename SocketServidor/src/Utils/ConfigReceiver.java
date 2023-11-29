package Utils;


public class ConfigReceiver {
    int numFarmer;
    int tempChegada;
    int tempMin;
    int tempMax;

    public ConfigReceiver(int numFarmer, int tempChegada, int tempMin, int tempMax) {
        this.numFarmer = numFarmer;
        this.tempChegada = tempChegada;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }
    

    
    public static ConfigReceiver getStringData(String data){
        try {
            String splitString[] = data.split("/");
            return new ConfigReceiver(
                Integer.valueOf(splitString[0]),
               Integer.valueOf(splitString[1]),
                  Integer.valueOf(splitString[2]),
                  Integer.valueOf(splitString[3])
            );
        } catch (Exception ex) {
            System.out.print(ex);
            return null;
        }
    }
    
    public void printData(){
        System.out.print("numero de fazendeiros: " + this.numFarmer);
        System.out.print("tempo de chegada de novos fazendeiros: " + this.tempChegada);
        System.out.print("tempo minímo de cruzamento: " + this.tempMin);
        System.out.print("tempo máximo de cruzamento " + this.tempMax);
    }
     
    public int getNumFarmer() {
        return numFarmer;
    }

    public void setNumFarmer(int numFarmer) {
        this.numFarmer = numFarmer;
    }

    public int getTempChegada() {
        return tempChegada;
    }

    public void setTempChegada(int tempChegada) {
        this.tempChegada = tempChegada;
    }

    public int getTempMin() {
        return tempMin;
    }

    public void setTempMin(int tempMin) {
        this.tempMin = tempMin;
    }

    public int getTempMax() {
        return tempMax;
    }

    public void setTempMax(int tempMax) {
        this.tempMax = tempMax;
    }
    
    
    
}
