package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.controller.BillController;
import com.unibuc.ex1curs11.dto.BillDTO;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class BillControllerTest {

    @Mock
    private BillService billService;

    @InjectMocks
    private BillController billController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBill() {
        Long userId = 1L;
        BillDTO billDTO = new BillDTO();
        billDTO.setBillName("Electricity");
        billDTO.setAmount(BigDecimal.valueOf(100));
        billDTO.setNextDueDate(LocalDate.now());
        billDTO.setDescription("Monthly electricity bill");

        BillDTO createdBill = new BillDTO();
        createdBill.setId(1L);
        createdBill.setBillName("Electricity");
        createdBill.setAmount(BigDecimal.valueOf(100));
        createdBill.setNextDueDate(LocalDate.now());
        createdBill.setDescription("Monthly electricity bill");

        when(billService.createBill(userId, billDTO)).thenReturn(createdBill);

        ResponseEntity<BillDTO> response = billController.createBill(userId, billDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(createdBill, response.getBody());
        verify(billService, times(1)).createBill(userId, billDTO);
    }

    @Test
    void testGetUserBills() {
        Long userId = 1L;
        BillDTO bill1 = new BillDTO();
        bill1.setId(1L);
        bill1.setBillName("Electricity");
        bill1.setAmount(BigDecimal.valueOf(100));
        bill1.setNextDueDate(LocalDate.now());
        bill1.setDescription("Monthly electricity bill");

        BillDTO bill2 = new BillDTO();
        bill2.setId(2L);
        bill2.setBillName("Water");
        bill2.setAmount(BigDecimal.valueOf(50));
        bill2.setNextDueDate(LocalDate.now());
        bill2.setDescription("Monthly water bill");

        List<BillDTO> bills = Arrays.asList(bill1, bill2);
        when(billService.getUserBills(userId)).thenReturn(bills);

        ResponseEntity<List<BillDTO>> response = billController.getUserBills(userId);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(bills, response.getBody());
        verify(billService, times(1)).getUserBills(userId);
    }

    @Test
    void testUpdateBill() {
        Long billId = 1L;
        BillDTO billDTO = new BillDTO();
        billDTO.setBillName("Updated Electricity Bill");
        billDTO.setAmount(BigDecimal.valueOf(120));
        billDTO.setNextDueDate(LocalDate.now());
        billDTO.setDescription("Updated monthly electricity bill");

        BillDTO updatedBill = new BillDTO();
        updatedBill.setId(billId);
        updatedBill.setBillName("Updated Electricity Bill");
        updatedBill.setAmount(BigDecimal.valueOf(120));
        updatedBill.setNextDueDate(LocalDate.now());
        updatedBill.setDescription("Updated monthly electricity bill");

        when(billService.updateBill(billId, billDTO)).thenReturn(updatedBill);

        ResponseEntity<BillDTO> response = billController.updateBill(billId, billDTO);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals(updatedBill, response.getBody());
        verify(billService, times(1)).updateBill(billId, billDTO);
    }

    @Test
    void testDeleteBill() {
        Long billId = 1L;

        doNothing().when(billService).deleteBill(billId);

        ResponseEntity<Void> response = billController.deleteBill(billId);

        assertEquals(HttpStatus.NO_CONTENT, response.getStatusCode());
        verify(billService, times(1)).deleteBill(billId);
    }
}

