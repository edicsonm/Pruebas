package scheduling.quartz;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class HelloJob implements Job {
	@Override
	public void execute(JobExecutionContext arg0) throws JobExecutionException {
//		System.out.println("Hello world since Australia ... ");
		System.out.println("" + arg0.getJobDetail().getKey().getName() + "<-->" +arg0.getTrigger().getKey().getName()+ "<-->" +arg0.getTrigger().getNextFireTime()+ "<-->" +arg0.getTrigger().getStartTime() + "<-->" +arg0.getTrigger().getEndTime());
//		throw new JobExecutionException();
	}
}
