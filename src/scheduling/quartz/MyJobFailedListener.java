package scheduling.quartz;

import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;
import org.quartz.JobListener;

public class MyJobFailedListener implements JobListener {
	
	public String getName() {
		return "FAILED JOB";
	}
	
	@Override
	public void jobToBeExecuted(JobExecutionContext arg0) {
		System.out.println("Ejecuta jobToBeExecuted ...");
	}

	@Override
	public void jobWasExecuted(JobExecutionContext context, JobExecutionException exception) {
		System.out.println("Ejecuta jobWasExecuted ...");
		if (exception != null) {
			System.out.println("Report generation error");
			// TODO notify development team
		}
	}

	@Override
	public void jobExecutionVetoed(JobExecutionContext arg0) {
		// TODO Auto-generated method stub
		System.out.println("Ejecuta jobExecutionVetoed ...");
	}

	
}
