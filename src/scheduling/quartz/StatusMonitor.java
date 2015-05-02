package scheduling.quartz;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;

import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;

public class StatusMonitor {
	private Scheduler scheduler;
	public static String sufix = "02";
	public static String jobName = "job" + sufix;
	public static String groupName = "group" + sufix;
	public static String triggerName = "trigger" + sufix;
	
	public void start() throws SchedulerException {
		SchedulerFactory schedFact = new StdSchedulerFactory();
		Scheduler sched = schedFact.getScheduler();
		
		JobDetail job = newJob(AddLineFile.class).withIdentity(jobName, groupName).build();
		job.getJobDataMap().put("NOMBRE", "Green: ");
		
		Trigger trigger = newTrigger()
				.withIdentity(triggerName, groupName)
				.startNow()
				.withSchedule(
						simpleSchedule().withIntervalInSeconds(5)
								.repeatForever()).build();
		
		
//			sched.getListenerManager().addJobListener(new MyJobFailedListener(), KeyMatcher.keyEquals(new JobKey(jobName, groupName)));
//			sched.getListenerManager().addSchedulerListener(new MySchedulerListener());
		sched.getListenerManager().addSchedulerListener(new MyOtherSchedulerListener());
		sched.start();
		sched.scheduleJob(job, trigger);
	}
	
	public void stop(){  
        try {
        	System.out.println("Es llamado el metodo stop ...");
            scheduler.shutdown(false);  
        } catch (Exception ex) {  
        }             
    } 
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StatusMonitor monitor = new StatusMonitor();  
        try {  
            monitor.start();  
            System.out.println("Pausa... pulse una tecla para finalizar la aplicación");  
            System.in.read();  
            monitor.stop();           
        } catch (Exception ex) {  
            System.err.println(ex);  
        }  
	}

}
