package SingleBridge;

import java.io.PrintWriter;
import java.util.List;
import java.util.concurrent.CopyOnWriteArrayList;
import java.util.concurrent.Semaphore;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.atomic.AtomicReference;

public class Bridge {

    private final Semaphore semaphore;
    private final PrintWriter out;
    private AtomicReference<Direction> direction = new AtomicReference<>(Direction.NORTH);
    private List<Farmer> farmersOnLane = new CopyOnWriteArrayList<>();

    public Bridge(PrintWriter out) {
        semaphore = new Semaphore(1);
        this.out = out;
    }

    public void crossBridge(Farmer farmer) throws InterruptedException {
      
        out.printf("%s ESPERANDO PARA ATRAVESSAR ( tempo para atravessar : %d segundos).\n", farmer.getName(), farmer.getCrossingTime());
        
        if (semaphore.availablePermits() == 0 && farmer.getDirection() == direction.get()) {
            try {
                farmersOnLane.add(farmer);
                out.printf("%s ESTA ATRAVESSANDO EM GRUPO:\n", farmer.getName());          
                for (Farmer f : farmersOnLane) {
                    out.printf("-----MEMBRO DO GRUPO: %s.\n", f.getName());
                }
                TimeUnit.SECONDS.sleep(farmer.getCrossingTime());
            } finally {
                farmersOnLane.remove(farmer);
                out.printf("%s ATRAVESSOU EM GRUPO.\n", farmer.getName());               
            }
        } else {
            try {
                semaphore.acquire();
                farmersOnLane.add(farmer);
                direction.set(farmer.getDirection());               
                out.printf("%s ESTA ATRAVESSANDO.\n", farmer.getName());
                TimeUnit.SECONDS.sleep(farmer.getCrossingTime());
            } finally {
                       
                while(farmersOnLane.size() > 1){
                    out.printf("%s ESPERANDO FAZENDEIROS NA PONTE.\n", farmer.getName());
                    for (Farmer f : farmersOnLane) {
                        out.printf("****MEMBRO DO GRUPO: %s.\n", f.getName());
                    } 
                    TimeUnit.SECONDS.sleep(1);               
                } 
                farmersOnLane.remove(farmer);  
                semaphore.release();
                out.printf("%s ATRAVESSOU.\n", farmer.getName());
                
            }
        }
    }
    
}
