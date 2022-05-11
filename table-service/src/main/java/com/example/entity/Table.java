package com.example.entity;

import com.example.request.CreateTableRequest;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@javax.persistence.Table(name="tables")
@javax.persistence.Entity
@NoArgsConstructor
@Getter
@Setter

public class Table {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name="NUMBER_OF_SEATS")
    private int seats;
    @Column(name="LOCATION")
    private String location;
    @Column(name="RESERVATION_HOUR")
    private int reservationHour;

    public Table (CreateTableRequest createTableRequest){
        this.seats = createTableRequest.getNumber_of_seats();
        this.location = createTableRequest.getLocation();
    }

    public Table(int id, int seats, String location, int reservationHour) {
        this.id = id;
        this.seats = seats;
        this.location = location;
        this.reservationHour = reservationHour;
    }
}