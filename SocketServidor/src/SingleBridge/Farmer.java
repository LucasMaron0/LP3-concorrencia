
package SingleBridge;

import java.util.Random;
import java.util.logging.Level;
import java.util.logging.Logger;


class Farmer implements Runnable
{
    private String name;
    private Bridge bridge;
    private Direction direction;
    private int crossingTime;
    private static Random random = new Random();
     
    public Farmer(Bridge bridge, Direction direction, int tempMin, int tempMax) {
        this.bridge = bridge;
        this.direction = direction;
        
        if(tempMin == tempMax){
            this.crossingTime = tempMin;  
        }else{
            this.crossingTime = random.nextInt(tempMin,tempMax);
        }
    }
     
    public void run()
    {
        try {
            bridge.crossBridge(this);
        } catch (InterruptedException ex) {
            Logger.getLogger(Farmer.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
 
    public Direction getDirection() {
        return direction;
    }

    public void setDirection(Direction direction) {
        this.direction = direction;
    }
    public String getName() {
        return name;
    }
 
    public void setName(String name) {
        this.name = name;
    }

    public Bridge getBridge() {
        return bridge;
    }

    public void setBridge(Bridge bridge) {
        this.bridge = bridge;
    }

    public int getCrossingTime() {
        return crossingTime;
    }

    public void setCrossingTime(int crossingTime) {
        this.crossingTime = crossingTime;
    }
 
    
}