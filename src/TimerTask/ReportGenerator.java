package TimerTask;

import java.util.Calendar;
import java.util.Timer;
import java.util.TimerTask;

public class ReportGenerator extends TimerTask {

	public static void main(String[] args) {
		Timer timer = new Timer();
	    Calendar date = Calendar.getInstance();
//	    date.set(Calendar.DAY_OF_WEEK,Calendar.SUNDAY);
	    date.set(Calendar.HOUR, 0);
	    date.set(Calendar.MINUTE, 0);
	    date.set(Calendar.SECOND, 0);
	    date.set(Calendar.MILLISECOND, 0);
	    // Schedule to run every Sunday in midnight
	    timer.schedule(new ReportGenerator(), date.getTime(),  1000 * 60 * 60 * 24 * 7 );
	    timer.schedule(new ReportGenerator(), date.getTime(),  10000);
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("Generating report");
	}

}
