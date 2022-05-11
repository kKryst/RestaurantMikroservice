package com.example.response;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class ReservationResponse {
    private String name;
    private int number_of_people;
    private String location;
    private int hour;

}
