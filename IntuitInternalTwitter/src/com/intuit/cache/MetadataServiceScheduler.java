package com.intuit.cache;

public class MetadataServiceScheduler implements Runnable{
	
	 //private static Logger s_logger = Logger.getInstance(MetadataServiceScheduler.class);
     private static final MetadataServiceScheduler instance = new MetadataServiceScheduler();

     
 	public static MetadataServiceScheduler getInstance() {
		return instance;
	}

     
	public void run(){
        try {
            //Calendar m_calendar = Calendar.getInstance();
            //getLogger().log(LogLevel.WARN,  "Not a warning -thread for metadata full refresh started at hour " + m_calendar.get(Calendar.HOUR));
            MetadataServiceCacheManager.getInstance().loadAllCache();
            //getLogger().log(LogLevel.WARN,  "Not a warning -thread for metadata full refresh end at hour " + m_calendar.get(Calendar.HOUR));
            
        } catch (Exception e) {
            //getLogger().log(
                    //LogLevel.ERROR, "Unexpected exception during metadata cache refresh", e);
        }
     }
	

       
  
    /*private static Logger getLogger() {
        if (s_logger == null) {
            s_logger = Logger.getInstance(MetadataServiceScheduler.class);
        }
        return s_logger;
    }*/

}

