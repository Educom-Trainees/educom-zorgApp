package nu.zapp.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class ScheduledTaskService {

    @Autowired
    private DatabaseScheduledService dbService;

    @Scheduled(cron = "0 0 0 * * *")
    public void performInactivityCheck() {
        dbService.callInactivityCheckProcedure();
    }

}


