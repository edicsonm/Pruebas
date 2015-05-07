package scheduling.quartz;

import org.quartz.Trigger;
import org.quartz.listeners.SchedulerListenerSupport;

public class MyOtherSchedulerListener extends SchedulerListenerSupport {
    @Override 
    public void schedulerStarted() { 
        // do something with the event 
    	System.out.println("Ejecutando schedulerStarted ... en MyOtherSchedulerListener ... " );
    } 
    @Override 
    public void schedulerShutdown() { 
        // do something with the event 
    	System.out.println("Ejecutando schedulerShutdown ... en MyOtherSchedulerListener ... " );
    } 
    @Override 
    public void jobScheduled(Trigger trigger) { 
        // do something with the event 
    	System.out.println("Ejecutando jobScheduled ... en MyOtherSchedulerListener ... " );
    } 
}
