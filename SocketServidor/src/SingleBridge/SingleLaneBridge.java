//https://orajavasolutions.wordpress.com/2014/05/03/single-lane-bridge-problem/
package SingleBridge;


import Utils.ConfigReceiver;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;
import java.util.logging.Level;
import java.util.logging.Logger;
 
public class SingleLaneBridge {
    
   public static boolean  bridgeCompleted = false;
 
    public static void startSingleBridge(PrintWriter out, ConfigReceiver configFarmer)
    {
        final Bridge bridge = new Bridge(out);
       
        Thread toNorth = new Thread( new Runnable() {
             
            @Override
            public void run() {
                createFarmers(configFarmer, Direction.NORTH, "INDO AO NORTE", bridge);
            }
        });
         
        Thread toSouth = new Thread( new Runnable() {
             
            @Override
            public void run() { 
                createFarmers(configFarmer, Direction.SOUTH, "INDO AO SUL", bridge);
            }
        });
         
        toNorth.start();
        toSouth.start();
       
     
        try {
            toNorth.join();
            toSouth.join();                        
        } catch (InterruptedException e) {
            e.printStackTrace();       
        }         
    }
    
    
    public static void createFarmers (ConfigReceiver configFarmer, Direction direction, String name, Bridge bridge ){
                
        Random random = new Random();
    
        List<Thread> threads = new ArrayList<>();
        for(int i = 0; i < configFarmer.getNumFarmer(); i++)
        {
            try
            {
                TimeUnit.SECONDS.sleep(configFarmer.getTempChegada());             
          
                Farmer farmer = new Farmer(
                        bridge,  
                        direction, 
                        configFarmer.getTempMin(),
                        configFarmer.getTempMax()
                );
                Thread th = new Thread(farmer);
                farmer.setName(i + "-"+ name+"-"+th.getId());
                th.start();
                threads.add(th);
            }
            catch(InterruptedException iex)
            {
                iex.printStackTrace();
            }
        }
        for (Thread th : threads) {
            try {
                th.join();
            } catch (InterruptedException ex) {
                Logger.getLogger(SingleLaneBridge.class.getName()).log(Level.SEVERE, null, ex);
            }
        } 
    }
    
}
 