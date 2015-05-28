package com.foo;

import org.apache.logging.log4j.Level;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.apache.logging.log4j.core.appender.FileAppender;

public class MyApp {
	
	private static final Logger logger = LogManager.getLogger(MyApp.class);
	private static final Logger info1 = LogManager.getLogger("info1." + MyApp.class.getName());
	private static final Logger info2 = LogManager.getLogger("info2." + MyApp.class.getName());
	
	
	public static void main(String[] args) {
		try {
			String value = "as";
			int val = Integer.parseInt(value);
			} catch (Exception e) {
				logger.error( "failed!", e );
			}
		
		info1.info("Este es un mensaje de salida del log info1");
		info2.info("Este es un mensaje de salida del log info2");
		
		logger.info("Este es un mensaje de logger");
		logger.trace("Entering application.");
        Bar bar = new Bar();
        if (!bar.doIt()) {
            logger.error("Didn't do it.");
        }
        logger.trace("Exiting application.");

	}

}
