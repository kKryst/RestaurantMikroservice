package com.example.entity;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@Entity
@javax.persistence.Table(name = "Reservations")
@Getter
@Setter
@NoArgsConstructor
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private int id;
    @Column(name = "LAST_NAME")
    private String last_name;
    @Column(name = "PHONE_NUMBER")
    private String phone_number;
    @Column(name = "NUMBER_OF_PEOPLE")
    private int number_of_people;
    @Column(name = "TABLE_ID")
    private int table_id;

}
