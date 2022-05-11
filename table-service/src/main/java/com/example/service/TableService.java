package com.example.service;


import com.example.entity.Table;
import com.example.repository.TableRepository;
import com.example.request.CreateTableRequest;
import com.example.request.LocationAndSeatsRequest;
import com.example.response.TableResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Service;

@Service
@EnableAutoConfiguration
public class TableService {

    @Autowired
    TableRepository tableRepository;

    Logger logger = LoggerFactory.getLogger(TableService.class);

    public TableResponse createTable(CreateTableRequest createTableRequest) {
        Table table = new Table(createTableRequest);
        tableRepository.save(table);
        return new TableResponse(table);
    }

    public String deleteTable(long id) {
        tableRepository.deleteById((int) id);
        return "table " + id + " has been removed";
    }

    public Table getTableByLocationAndSeats(LocationAndSeatsRequest locationAndSeatsRequest) {
        // pobieram wpis z bazy danych na podstawie liczby osob oraz lokalizacji
            Table table = tableRepository.getFirstBySeatsGreaterThanEqualAndLocationEquals(locationAndSeatsRequest.getNumber_of_people(),
                    locationAndSeatsRequest.getLocation());
            //sprawdzam, czy wpis ma juz ustalona godzine rezerwacji.
            if (table.getReservationHour() != 0) {
                System.out.println("stoik o id " + table.getId() + "  ma juz wpisana godzine, wybieram nowy stolik");
                // pobieram wpis, ktory nie ma podanej godziny rezerwacji i zwracam go.
                return tableRepository.getFirstBySeatsGreaterThanEqualAndLocationEqualsAndIdIsNotAndReservationHourEquals(locationAndSeatsRequest.getNumber_of_people(),
                            locationAndSeatsRequest.getLocation(),
                            table.getId(),
                        0);
            } else {
                // jesli nie, zwracam pobrany wczesniej wpis.
                return table;
            }
    }

    public Integer updateHour(Integer id, Integer hour) {
        Table table = tableRepository.getById(id);
        // validacja danych, sprwadzanie czy zarowno hour jak i wpis w bazie danych maja odpowiednie wartosci
        if (hour != null && table.getReservationHour() == 0){
            table.setReservationHour(hour);
            tableRepository.save(table);
            return table.getReservationHour();
        } else {
            return 0;
        }
    }
}
