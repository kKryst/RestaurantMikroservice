package com.example.service;

import com.example.entity.Table;
import com.example.repository.TableRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class Scheduler {

    @Autowired
    TableRepository repository;

    Logger logger = LoggerFactory.getLogger(Scheduler.class);
    @Scheduled(cron = "0 0 0/1 1/1 * ?")
    //zadaniem metody jest zerowanie godziny rezerwacji, jesli minelo dwie godziny od momentu zarezerwowania stolika
    public void clearReservationHour(){

        //zgarniamy obecna godzine
        int currentHour = LocalDateTime.now().getHour();

        List<Table> tablesFromDatabase =  repository.getAllByReservationHourNotNull();
        tablesFromDatabase.forEach(table -> {
            //jezeli minelo 2 godziny od godziny rezerwacji, wyzeruj godzine rezerwacji.
            if ((currentHour - table.getReservationHour()) >= 2){
                table.setReservationHour(0);
                logger.info("table " + table.getId() + " zeroed successfully");
                repository.save(table);
            }
        });
    }
}
