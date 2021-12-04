package com.yankovltd.tunes.service.scheduler;

import com.yankovltd.tunes.service.StatsService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class StatsDeletionScheduler {

    private final StatsService statsService;
    private final static Logger LOGGER = LoggerFactory.getLogger(StatsDeletionScheduler.class);

    public StatsDeletionScheduler(StatsService statsService) {
        this.statsService = statsService;
    }

    // Every day at 0:00 o'clock
    @Scheduled(cron = "0 0 0 * * *")
    public void clearStatsDaily() {
        statsService.clearVisits();
        LOGGER.info("Visits should be cleared...");
    }
}
