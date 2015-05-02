package scheduling.quartz;

import java.util.List;

import org.quartz.JobKey;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.Trigger;
import org.quartz.impl.StdSchedulerFactory;
import org.quartz.impl.matchers.GroupMatcher;

public class CleanJobs {
	public static void main(String args[]) throws SchedulerException {
		Scheduler scheduler = new StdSchedulerFactory().getScheduler();
		for (String groupName : scheduler.getJobGroupNames()) {
			for (JobKey jobKey : scheduler.getJobKeys(GroupMatcher.jobGroupEquals(groupName))) {
				String jobName = jobKey.getName();
				String jobGroup = jobKey.getGroup();
				List<Trigger> triggers = (List<Trigger>) scheduler.getTriggersOfJob(jobKey);
				for (Trigger trigger : triggers) {
					scheduler.unscheduleJob(trigger.getKey());
				}
				scheduler.deleteJob(jobKey);
			}
		}
	System.out.println("Termina ...");
	System.exit(0);
	}
}
