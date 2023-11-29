package Utils;


public class ConfigSender {
    int numFarmer;
    int tempChegada;
    int tempMin;
    int tempMax;

    public ConfigSender(int numFarmer, int tempChegada, int tempMin, int tempMax) {
        this.numFarmer = numFarmer;
        this.tempChegada = tempChegada;
        this.tempMin = tempMin;
        this.tempMax = tempMax;
    }
    
    public String generateStringData(){
        return this.numFarmer + "/" + this.tempChegada + "/" + this.tempMin + "/" + this.tempMax;
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
