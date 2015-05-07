package scheduling.quartz;

import java.util.Collection;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.KeyMatcher;
import org.quartz.SimpleTrigger;

import static org.quartz.JobBuilder.newJob;
import static org.quartz.SimpleScheduleBuilder.simpleSchedule;
import static org.quartz.TriggerBuilder.newTrigger;


public class QuartzTest {
	
	public static String sufix = "12";
	public static String jobName = "job" + sufix;
	public static String groupName = "group" + sufix;
	public static String triggerName = "trigger" + sufix;
	
	public static void main(String[] args) {
		try {
			// Grab the Scheduler instance from the Factory
			SchedulerFactory schedFact = new StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
//			sched.getListenerManager().addJobListener(new MyJobFailedListener(), KeyMatcher.keyEquals(new JobKey(jobName, groupName)));
//			sched.getListenerManager().addSchedulerListener(new MySchedulerListener());
			sched.getListenerManager().addSchedulerListener(new MyOtherSchedulerListener());
			sched.start();
			
			/*System.out.println("Name " + sched.getSchedulerName());
			Collection<Scheduler>  list = schedFact.getAllSchedulers();
			for (Scheduler scheduler : list) {
				System.out.println("scheduler.getSchedulerInstanceId() " + scheduler.getSchedulerInstanceId());
				System.out.println("scheduler.getSchedulerName() " + scheduler.getSchedulerName());
			}*/
//			sched.getListenerManager().addJobListener(new MyJobFailedListener(), groupEquals("myJobGroup"));
			
			// define the job and tie it to our HelloJob class
			JobDetail job = newJob(AddLineFile.class).withIdentity(jobName, groupName).build();
			job.getJobDataMap().put("NOMBRE", "Green: ");
			
			// Trigger the job to run now, and then repeat every 40 seconds
			Trigger trigger = newTrigger()
					.withIdentity(triggerName, groupName)
					.startNow()
					.withSchedule(
							simpleSchedule().withIntervalInSeconds(5)
									.repeatForever()).build();
			
		/*	Trigger trigger = newTrigger()
				    .withIdentity(triggerName, groupName)
//				    .startAt(myTimeToStartFiring)  // if a start time is not given (if this line were omitted), "now" is implied
				    .withSchedule(simpleSchedule()
				        .withIntervalInSeconds(2)
				        .withRepeatCount(3)) // note that 10 repeats will give a total of 11 firings
//				    .forJob(jobName) // identify job with handle to its JobDetail itself                   
				    .build();*/
			
			
			// Tell quartz to schedule the job using our trigger
			if(!sched.checkExists(job.getKey()))sched.scheduleJob(job, trigger);
			else System.out.println("El job ya existe ... ");
//	        sched.shutdown();
		} catch (SchedulerException se) {
			se.printStackTrace();
		}

	}

}
