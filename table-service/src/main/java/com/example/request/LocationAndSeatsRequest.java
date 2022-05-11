package com.example.request;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class LocationAndSeatsRequest {
    private int number_of_people;
    private String location;
}
