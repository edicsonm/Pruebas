package scheduling.quartz;

import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.quartz.JobExecutionContext;
import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class Monitor {
	public static void main(String[] args) {
		try {
			Scheduler scheduler = new StdSchedulerFactory().getScheduler();
			List<JobExecutionContext> jobs = scheduler.getCurrentlyExecutingJobs();
			for (Iterator iterator = jobs.iterator(); iterator.hasNext();) {
				JobExecutionContext jobExecutionContext = (JobExecutionContext) iterator.next();
				System.out.println("--> " + jobExecutionContext.getJobDetail().getDescription());
			}
			
			
			for (String groupName : scheduler.getJobGroupNames()) {
				for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
					String jobName = jobKey.getName();
					String jobGroup = jobKey.getGroup();
					// get job's trigger
					List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
					Date nextFireTime = triggers.get(0).getNextFireTime();
					System.out.println("[jobName] : " + jobName + " [groupName] : " + jobGroup + " - " + nextFireTime);
				}
			}
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
		
	}
}
