package com.unibuc.ex1curs11.controller;

import com.unibuc.ex1curs11.dto.BillDTO;
import com.unibuc.ex1curs11.service.BillService;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/bills")
public class BillController {

    @Autowired
    private BillService billService;

    @PostMapping("/{userId}")
    @ApiOperation("Create a new bill for a user")
    public ResponseEntity<BillDTO> createBill(@PathVariable Long userId, @RequestBody BillDTO billDTO) {
        BillDTO created = billService.createBill(userId, billDTO);
        return ResponseEntity.ok(created);
    }

    @GetMapping("/{userId}")
    @ApiOperation("Get all bills for a specific user")
    public ResponseEntity<List<BillDTO>> getUserBills(@PathVariable Long userId) {
        List<BillDTO> bills = billService.getUserBills(userId);
        return ResponseEntity.ok(bills);
    }
}
