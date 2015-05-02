package scheduling.quartz;

import org.quartz.JobDetail;
import org.quartz.JobKey;
import org.quartz.SchedulerException;
import org.quartz.SchedulerListener;
import org.quartz.Trigger;
import org.quartz.TriggerKey;

public class MySchedulerListener implements SchedulerListener {

	@Override
	public void jobScheduled(Trigger paramTrigger) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobScheduled ... ");
	}

	@Override
	public void jobUnscheduled(TriggerKey paramTriggerKey) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobUnscheduled ... ");
	}

	@Override
	public void triggerFinalized(Trigger paramTrigger) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  triggerFinalized ... ");
	}

	@Override
	public void triggerPaused(TriggerKey paramTriggerKey) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  triggerPaused ... ");
	}

	@Override
	public void triggersPaused(String paramString) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  triggersPaused ... ");
	}

	@Override
	public void triggerResumed(TriggerKey paramTriggerKey) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  triggerResumed ... ");
	}

	@Override
	public void triggersResumed(String paramString) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  triggersResumed ... ");
	}

	@Override
	public void jobAdded(JobDetail paramJobDetail) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobAdded ... ");
	}

	@Override
	public void jobDeleted(JobKey paramJobKey) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobDeleted ... ");
	}

	@Override
	public void jobPaused(JobKey paramJobKey) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobPaused 1 ... ");
	}

	@Override
	public void jobsPaused(String paramString) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobsPaused 2 ... ");
	}

	@Override
	public void jobResumed(JobKey paramJobKey) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobResumed 1 ... ");
	}

	@Override
	public void jobsResumed(String paramString) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  jobsResumed 2 ... ");
	}

	@Override
	public void schedulerError(String paramString,
			SchedulerException paramSchedulerException) {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulerError ... ");
	}

	@Override
	public void schedulerInStandbyMode() {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulerInStandbyMode ... ");
	}

	@Override
	public void schedulerStarted() {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulerStarted ... ");
	}

	@Override
	public void schedulerStarting() {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulerStarting ... ");
	}

	@Override
	public void schedulerShutdown() {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulerShutdown ... ");
	}

	@Override
	public void schedulerShuttingdown() {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulerShuttingdown ... ");
	}

	@Override
	public void schedulingDataCleared() {
		// TODO Auto-generated method stub
		AddlogLine.AddlogLine("Ejecutando MySchedulerListener  schedulingDataCleared ... ");
	}

}
