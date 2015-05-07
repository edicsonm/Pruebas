package scheduling.quartz;

import java.util.Collection;

import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.impl.StdSchedulerFactory;

public class ShutdownScheduler {

	public static void main(String[] args) {
		try {
			SchedulerFactory schedFact = new StdSchedulerFactory();
			Scheduler sched = schedFact.getScheduler();
			System.out.println("Name " + sched.getSchedulerName());
			sched.shutdown(false);
			Collection<Scheduler>  list = schedFact.getAllSchedulers();
			for (Scheduler scheduler : list) {
				System.out.println("scheduler.getSchedulerInstanceId()" + scheduler.getSchedulerInstanceId());
				System.out.println("scheduler.getSchedulerName()" + scheduler.getSchedulerName());
			}
			System.exit(0);
		} catch (SchedulerException e) {
			e.printStackTrace();
		}
	}

}
