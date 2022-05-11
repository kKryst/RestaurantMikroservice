package com.example.controller;

import com.example.request.CreateReservation;
import com.example.response.ReservationResponse;
import com.example.service.ReservationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping("/api/reservation/")
public class ReservationController {

    @Autowired
    private ReservationService reservationService;

    @PostMapping("create")
    public ReservationResponse createReservation(@RequestBody CreateReservation createReservation){
        return  reservationService.createReservation(createReservation);
    }



}
