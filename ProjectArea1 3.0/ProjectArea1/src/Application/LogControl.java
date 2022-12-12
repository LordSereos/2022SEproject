package Application;

import java.util.logging.Level;
import java.util.logging.Logger;

public class LogControl {
	private final static Logger LOGGER = Logger.getLogger("Log");
    
    public static void InfoLog(String log)
    {
        LOGGER.log(Level.INFO, log);
    }
    
    public static void WarningLog(String log)
    {
        LOGGER.log(Level.WARNING, log);
    }
    
    public static void ErrorLog(String log)
    {
        LOGGER.log(Level.SEVERE, log);
    }
    
    public static Logger getLogger()
    {
    	return LOGGER;
    }
}
