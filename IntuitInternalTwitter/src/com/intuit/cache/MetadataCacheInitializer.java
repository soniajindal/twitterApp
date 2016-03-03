package com.intuit.cache;


import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;
import java.util.logging.Logger;

import javax.annotation.PostConstruct;
import javax.inject.Singleton;

@Singleton
public class MetadataCacheInitializer {
	@PostConstruct
    public static void initMetadataCache() throws Exception{
   		//start the thread for cache loading from DB
   		startDaemonThread();
		
    }
    
    

	public static void startDaemonThread() {
        try{
            
            ScheduledExecutorService service = Executors.newSingleThreadScheduledExecutor();
        	service.scheduleAtFixedRate(MetadataServiceScheduler.getInstance(), 10, 10,TimeUnit.MINUTES);
        }catch(Exception e){
               /* getLogger().log(LogLevel.ERROR,
                        "Error in running Daemon Thread: ex=" + e.getMessage());*/
            }
        }
	
}


