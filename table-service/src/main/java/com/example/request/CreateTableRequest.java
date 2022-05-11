package com.example.request;

import com.example.resources.Location;
import com.example.resources.Status;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class CreateTableRequest {
    private int number_of_seats;
    private String location;
}
