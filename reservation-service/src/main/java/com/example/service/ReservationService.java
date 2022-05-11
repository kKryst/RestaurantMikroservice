package com.example.service;

import com.example.entity.Reservation;
import com.example.feignclient.TableFeignClient;
import com.example.repository.ReservationRepository;
import com.example.request.CreateReservation;
import com.example.request.IdAndHourRequest;
import com.example.request.LocationAndSeatsRequest;
import com.example.response.ReservationResponse;
import com.example.response.TableResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@EnableAutoConfiguration

public class ReservationService {

    @Autowired
    ReservationRepository repository;

    @Autowired
    TableFeignClient tableFeignClient;

    public ReservationResponse createReservation(CreateReservation createReservation) {
        LocationAndSeatsRequest locationAndSeatsRequest = new LocationAndSeatsRequest();
        locationAndSeatsRequest.setLocation(createReservation.getLocation());
        locationAndSeatsRequest.setNumber_of_people(createReservation.getNumber_of_people());
        //pytamy tableservice o stoliki z wystarczajaca liczba miejsc oraz lokalizacja

        TableResponse tableResponse = tableFeignClient.getTableWithEnoughSeatsAndLocation(locationAndSeatsRequest);
        //w tableresponse mamy informacje o odpowiednim stoliku

        //tworzymy rezerwacje
        Reservation reservation = new Reservation();
        reservation.setLast_name(createReservation.getLast_name());
        reservation.setNumber_of_people(createReservation.getNumber_of_people());
        reservation.setPhone_number(createReservation.getPhone_number());
        reservation.setTable_id(tableResponse.getId());

        //zapisujemy ja w bazie danych
        repository.save(reservation);

        //tworzymy obiekt ktory przeslemy do metody w tableservice
        IdAndHourRequest idAndHourRequest = new IdAndHourRequest();
        idAndHourRequest.setHour(createReservation.getHour());
        idAndHourRequest.setId(reservation.getTable_id());
        //ustawiamy godzine rezerwacji dopisujac ja do wpisu o okreslonym id
        tableFeignClient.setHour(idAndHourRequest);

        ReservationResponse response = new ReservationResponse();
        response.setLocation(tableResponse.getLocation());
        response.setNumber_of_people(createReservation.getNumber_of_people());
        response.setHour(createReservation.getHour());
        response.setName(createReservation.getLast_name());
        return response;
    }
}
