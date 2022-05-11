package com.example.response;

import com.example.entity.Table;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class TableResponse {
    private int id;
    private int number_of_seats;
    private String location;
    private int reservation_hour;

    public TableResponse (Table table){
        this.id =   table.getId();
        this.number_of_seats = table.getSeats();
        this.location = table.getLocation();
        this.reservation_hour = table.getReservationHour();
    }

    public TableResponse(int id, int number_of_seats, String location, int reservation_hour) {
        this.id = id;
        this.number_of_seats = number_of_seats;
        this.location = location;
        this.reservation_hour = reservation_hour;
    }
}
