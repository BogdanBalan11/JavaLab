package com.unibuc.ex1curs11.service;

import com.unibuc.ex1curs11.dto.BillDTO;
import com.unibuc.ex1curs11.exception.ItemNotFound;
import com.unibuc.ex1curs11.model.Bill;
import com.unibuc.ex1curs11.model.User;
import com.unibuc.ex1curs11.repository.BillRepository;
import com.unibuc.ex1curs11.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BillService {

    @Autowired
    private BillRepository billRepository;

    @Autowired
    private UserRepository userRepository;

    public BillDTO createBill(Long userId, BillDTO billDTO) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new ItemNotFound("User"));

        Bill bill = new Bill();
        bill.setBillName(billDTO.getBillName());
        bill.setAmount(billDTO.getAmount());
        bill.setNextDueDate(billDTO.getNextDueDate());
        bill.setDescription(billDTO.getDescription());
        bill.setUser(user);

        billRepository.save(bill);
        return convertToDTO(bill);
    }

    public List<BillDTO> getUserBills(Long userId) {
        return billRepository.findByUserId(userId).stream()
                .map(this::convertToDTO)
                .collect(Collectors.toList());
    }

    public BillDTO updateBill(Long billId, BillDTO billDTO) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ItemNotFound("Bill"));

        bill.setBillName(billDTO.getBillName());
        bill.setAmount(billDTO.getAmount());
        bill.setNextDueDate(billDTO.getNextDueDate());
        bill.setDescription(billDTO.getDescription());

        billRepository.save(bill);

        return convertToDTO(bill);
    }

    public void deleteBill(Long billId) {
        Bill bill = billRepository.findById(billId)
                .orElseThrow(() -> new ItemNotFound("Bill"));
        billRepository.delete(bill);
    }

    private BillDTO convertToDTO(Bill bill) {
        BillDTO dto = new BillDTO();
        dto.setId(bill.getId());
        dto.setBillName(bill.getBillName());
        dto.setAmount(bill.getAmount());
        dto.setNextDueDate(bill.getNextDueDate());
        dto.setDescription(bill.getDescription());
        dto.setUserId(bill.getUser().getId());
        return dto;
    }
}
