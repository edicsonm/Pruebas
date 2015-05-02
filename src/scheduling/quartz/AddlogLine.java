package scheduling.quartz;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Calendar;

import org.quartz.JobDataMap;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

public class AddlogLine {
	
	private static final String newLine = System.getProperty("line.separator");
	
	public static void main(String[] args) {
		AddlogLine("Error ...");
	}
	
	public static void AddlogLine(String msg) {
		System.out.println("Ejecuta job ...");
		
		String fileName = "/home/edicson/Documents/logFile.txt";
	    PrintWriter printWriter = null;
	    File file = new File(fileName);
	    try {
	        if (!file.exists()) file.createNewFile();
	        printWriter = new PrintWriter(new FileOutputStream(fileName, true));
	        printWriter.write(newLine + ":"+ msg + ":"+ Calendar.getInstance().getTime().toGMTString());
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
