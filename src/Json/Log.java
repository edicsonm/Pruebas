package Json;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

public class Log {
	
	private static final Logger logger = LogManager.getLogger(Log.class);
	
	public static void main(String[] args) {
		logger.trace("Entering application.");
		logger.error("Did it again!");
	}

}
