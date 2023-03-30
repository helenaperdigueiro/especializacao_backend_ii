package com.msbills.controller;

import com.msbills.models.Bill;
import com.msbills.service.BillService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bills")
@RequiredArgsConstructor
public class BillController {

    private final BillService service;

    @GetMapping("/all")
    @PreAuthorize("hasRole('PROVIDERS')")
    public ResponseEntity<List<Bill>> getAll() {
        return ResponseEntity.ok(service.getAllBill());
    }

    @PostMapping()
    @PreAuthorize("hasRole('PROVIDERS')")
    public ResponseEntity<Bill> saveBill(@RequestBody Bill bill) {
        return ResponseEntity.status(201).body(service.saveBill(bill));
    }

    @GetMapping("{id}")
    public ResponseEntity<List<Bill>> findBillByCustomerId(@PathVariable String id) {
        return ResponseEntity.ok(service.findBillByCustomerId(id));
    }

}
