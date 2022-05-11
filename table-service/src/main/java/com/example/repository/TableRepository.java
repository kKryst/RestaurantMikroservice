package com.example.repository;

import com.example.entity.Table;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TableRepository extends JpaRepository<Table, Integer> {
    Table getFirstBySeatsGreaterThanEqualAndLocationEquals(int number_of_seats, String location);
    Table getFirstBySeatsGreaterThanEqualAndLocationEqualsAndIdIsNotAndReservationHourEquals(int number_of_seats, String location, int id, int hour);
    List<Table> getAllByReservationHourNotNull();
}
