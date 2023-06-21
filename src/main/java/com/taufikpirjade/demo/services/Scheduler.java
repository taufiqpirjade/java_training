package com.taufikpirjade.demo.services;

import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
public class Scheduler {

    @Scheduled(fixedDelay = 10000)
    public void getData() {
        System.out.println("Get Data Called");
    }
}
