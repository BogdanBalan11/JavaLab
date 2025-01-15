package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.dto.BillDTO;
import com.unibuc.ex1curs11.exception.ItemNotFound;
import com.unibuc.ex1curs11.model.Bill;
import com.unibuc.ex1curs11.model.User;
import com.unibuc.ex1curs11.repository.BillRepository;
import com.unibuc.ex1curs11.repository.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class BillServiceTest {

    @Mock
    private BillRepository billRepository;

    @Mock
    private UserRepository userRepository;

    @InjectMocks
    private BillService billService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testCreateBill_Success() {
        Long userId = 1L;
        BillDTO billDTO = new BillDTO();
        billDTO.setBillName("Electricity");
        billDTO.setAmount(BigDecimal.valueOf(100));
        billDTO.setNextDueDate(LocalDate.now());
        billDTO.setDescription("Monthly electricity bill");

        User user = new User();
        user.setId(userId);

        Bill bill = new Bill();
        bill.setId(1L);
        bill.setBillName("Electricity");
        bill.setAmount(BigDecimal.valueOf(100));
        bill.setNextDueDate(LocalDate.now());
        bill.setDescription("Monthly electricity bill");
        bill.setUser(user);

        when(userRepository.findById(userId)).thenReturn(Optional.of(user));
        when(billRepository.save(any(Bill.class))).thenReturn(bill);

        BillDTO createdBill = billService.createBill(userId, billDTO);

        assertNotNull(createdBill);
        assertEquals("Electricity", createdBill.getBillName());
        assertEquals(BigDecimal.valueOf(100), createdBill.getAmount());
        verify(userRepository, times(1)).findById(userId);
        verify(billRepository, times(1)).save(any(Bill.class));
    }

    @Test
    void testCreateBill_UserNotFound() {
        Long userId = 1L;
        BillDTO billDTO = new BillDTO();
        billDTO.setBillName("Electricity");
        billDTO.setAmount(BigDecimal.valueOf(100));
        billDTO.setNextDueDate(LocalDate.now());
        billDTO.setDescription("Monthly electricity bill");

        when(userRepository.findById(userId)).thenReturn(Optional.empty());

        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> billService.createBill(userId, billDTO));
        assertEquals("User not found", exception.getMessage());
        verify(userRepository, times(1)).findById(userId);
        verify(billRepository, never()).save(any(Bill.class));
    }

    @Test
    void testGetUserBills_Success() {
        Long userId = 1L;

        User user = new User();
        user.setId(userId);

        Bill bill1 = new Bill();
        bill1.setId(1L);
        bill1.setBillName("Electricity");
        bill1.setAmount(BigDecimal.valueOf(100));
        bill1.setNextDueDate(LocalDate.now());
        bill1.setDescription("Monthly electricity bill");
        bill1.setUser(user);

        Bill bill2 = new Bill();
        bill2.setId(2L);
        bill2.setBillName("Water");
        bill2.setAmount(BigDecimal.valueOf(50));
        bill2.setNextDueDate(LocalDate.now());
        bill2.setDescription("Monthly water bill");
        bill2.setUser(user);

        List<Bill> bills = Arrays.asList(bill1, bill2);

        when(billRepository.findByUserId(userId)).thenReturn(bills);

        List<BillDTO> billDTOs = billService.getUserBills(userId);

        assertEquals(2, billDTOs.size());
        assertEquals("Electricity", billDTOs.get(0).getBillName());
        assertEquals("Water", billDTOs.get(1).getBillName());
        verify(billRepository, times(1)).findByUserId(userId);
    }

    @Test
    void testUpdateBill_Success() {
        Long billId = 1L;
        Long userId = 1L;

        BillDTO billDTO = new BillDTO();
        billDTO.setBillName("Updated Electricity Bill");
        billDTO.setAmount(BigDecimal.valueOf(120));
        billDTO.setNextDueDate(LocalDate.now());
        billDTO.setDescription("Updated monthly electricity bill");

        User user = new User();
        user.setId(userId);
        user.setEmail("user@example.com");
        user.setName("John Doe");

        Bill existingBill = new Bill();
        existingBill.setId(billId);
        existingBill.setBillName("Electricity");
        existingBill.setAmount(BigDecimal.valueOf(100));
        existingBill.setNextDueDate(LocalDate.now().minusDays(1));
        existingBill.setDescription("Monthly electricity bill");
        existingBill.setUser(user);

        when(billRepository.findById(billId)).thenReturn(Optional.of(existingBill));
        when(billRepository.save(any(Bill.class))).thenReturn(existingBill);

        BillDTO updatedBill = billService.updateBill(billId, billDTO);

        assertNotNull(updatedBill);
        assertEquals("Updated Electricity Bill", updatedBill.getBillName());
        assertEquals(BigDecimal.valueOf(120), updatedBill.getAmount());
        assertEquals(userId, updatedBill.getUserId());
        verify(billRepository, times(1)).findById(billId);
        verify(billRepository, times(1)).save(existingBill);
    }


    @Test
    void testUpdateBill_BillNotFound() {
        Long billId = 1L;
        BillDTO billDTO = new BillDTO();
        billDTO.setBillName("Electricity");
        billDTO.setAmount(BigDecimal.valueOf(100));
        billDTO.setNextDueDate(LocalDate.now());
        billDTO.setDescription("Monthly electricity bill");

        when(billRepository.findById(billId)).thenReturn(Optional.empty());

        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> billService.updateBill(billId, billDTO));
        assertEquals("Bill not found", exception.getMessage());
        verify(billRepository, times(1)).findById(billId);
        verify(billRepository, never()).save(any(Bill.class));
    }

    @Test
    void testDeleteBill_Success() {
        Long billId = 1L;

        Bill bill = new Bill();
        bill.setId(billId);

        when(billRepository.findById(billId)).thenReturn(Optional.of(bill));
        doNothing().when(billRepository).delete(bill);

        assertDoesNotThrow(() -> billService.deleteBill(billId));
        verify(billRepository, times(1)).findById(billId);
        verify(billRepository, times(1)).delete(bill);
    }

    @Test
    void testDeleteBill_BillNotFound() {
        Long billId = 1L;

        when(billRepository.findById(billId)).thenReturn(Optional.empty());

        ItemNotFound exception = assertThrows(ItemNotFound.class, () -> billService.deleteBill(billId));
        assertEquals("Bill not found", exception.getMessage());
        verify(billRepository, times(1)).findById(billId);
        verify(billRepository, never()).delete(any(Bill.class));
    }
}

