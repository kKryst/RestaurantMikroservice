package com.example.feignclient;

import com.example.request.IdAndHourRequest;
import com.example.request.LocationAndSeatsRequest;
import com.example.response.TableResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@FeignClient(url = "${table.service.url}", value = "table-feign-client",
            path = "/api/table/")

public interface TableFeignClient {

    @PostMapping("getTablesWithEnoughSeatsAndLocation")
    TableResponse getTableWithEnoughSeatsAndLocation(LocationAndSeatsRequest locationAndSeatsRequest);

    @PutMapping("update")
    Integer setHour(IdAndHourRequest request);

}
