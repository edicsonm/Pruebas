package scheduling.quartz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.quartz.Job;
import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AddLineFile implements Job {
	private static final String newLine = System.getProperty("line.separator");
	
	@Override
	public void execute(JobExecutionContext paramJobExecutionContext) throws JobExecutionException {
		System.out.println("Ejecuta job ...");
		JobDataMap data = paramJobExecutionContext.getJobDetail().getJobDataMap();
		String NOMBRE = data.getString("NOMBRE");
		
		String fileName = "/home/edicson/Documents/recordFile.txt";
	    PrintWriter printWriter = null;
	    File file = new File(fileName);
	    try {
	        if (!file.exists()) file.createNewFile();
	        printWriter = new PrintWriter(new FileOutputStream(fileName, true));
	        printWriter.write(newLine + NOMBRE +":"+ Calendar.getInstance().getTime().toGMTString());
	    } catch (IOException ioex) {
	        ioex.printStackTrace();
	    } finally {
	        if (printWriter != null) {
	            printWriter.flush();
	            printWriter.close();
	        }
	    }
		
	}

}
