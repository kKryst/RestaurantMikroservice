package com.example.response;

import com.example.resources.Location;
import com.example.resources.Status;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Date;

@Getter
@Setter
@NoArgsConstructor
public class TableResponse {
    private int id;
    private int number_of_seats;
    private String location;
    private int reservation_hour;

}
