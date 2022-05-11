package com.example.request;

import com.example.entity.Reservation;
import com.example.resources.RegularCustomer;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class CreateReservation {
    private String last_name;
    private String phone_number;
    private int number_of_people;
    private String location;
    private int hour;


}
