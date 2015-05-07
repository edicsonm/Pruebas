import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;


public class LoggerJava {

	public static void main(String[] args) {
		Logger logger = Logger.getLogger("MyLog");  
	    FileHandler fh;  
	    try {  
	        fh = new FileHandler("/run/media/Edicson/Informacion IPG/PendingMaxMin.log");  
	        logger.addHandler(fh);
	        SimpleFormatter formatter = new SimpleFormatter();  
	        fh.setFormatter(formatter);  

	        logger.info("My first log");  

	    } catch (SecurityException e) {  
	        e.printStackTrace();  
	    } catch (IOException e) {  
	        e.printStackTrace();  
	    }  

	    logger.info("Hi How r u?");  


	}

}
