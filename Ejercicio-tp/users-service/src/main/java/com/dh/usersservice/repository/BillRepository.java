package com.dh.usersservice.repository;

import com.dh.usersservice.model.Bill;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.List;

@FeignClient(name = "ms-bill")
public interface BillRepository {

    @GetMapping("/bills/{id}")
    List<Bill> getBillsByUserId(@PathVariable String id);
}
