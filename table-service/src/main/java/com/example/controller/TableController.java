package com.example.controller;

import com.example.entity.Table;
import com.example.request.CreateTableRequest;
import com.example.request.IdAndHourRequest;
import com.example.request.LocationAndSeatsRequest;
import com.example.response.TableResponse;
import com.example.service.TableService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.EntityNotFoundException;


@RestController
@RequestMapping("/api/table/")
public class TableController {

    @Autowired
    private TableService tableService;

    Logger logger = LoggerFactory.getLogger(TableController.class);

    @PostMapping("create")
    public TableResponse createTable (@RequestBody CreateTableRequest createTableRequest){
        return tableService.createTable(createTableRequest);
    }
    @DeleteMapping("delete/{id}")
    public String deleteTable(@PathVariable long id){
        return tableService.deleteTable(id);
    }
    @PostMapping("getTablesWithEnoughSeatsAndLocation")
    public TableResponse getTableWithEnoughSeatsAndLocation(@RequestBody LocationAndSeatsRequest locationAndSeatsRequest){
        try {
            // w momencie w ktorym metoda nie znajdzie miejscu przepisywania danych do klasy TableResponse
            // wyskakuje NullPointerException
            return new TableResponse(tableService.getTableByLocationAndSeats(locationAndSeatsRequest));
        }
        catch (NullPointerException exc){
          logger.info("no table that meets the requirements");
          throw new EntityNotFoundException();
        }
    }
    @PutMapping("update")
    public Integer setHour(@RequestBody IdAndHourRequest request){
        return tableService.updateHour(request.getId(), request.getHour());
    }
}
