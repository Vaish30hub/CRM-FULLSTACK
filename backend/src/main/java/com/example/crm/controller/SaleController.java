package com.example.crm.controller;

import com.example.crm.model.Sale;
import com.example.crm.repository.SaleRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/sales")
public class SaleController {

    private final SaleRepository saleRepository;

    public SaleController(SaleRepository saleRepository) {
        this.saleRepository = saleRepository;
    }

    @PostMapping
    public Sale createSale(@RequestBody Sale sale) {
        return saleRepository.save(sale);
    }

    @GetMapping
    public List<Sale> getAllSales() {
        return saleRepository.findAll();
    }

    @GetMapping("/{id}")
    public Sale getSaleById(@PathVariable Long id) {
        return saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));
    }

    @PutMapping("/{id}")
    public Sale updateSale(@PathVariable Long id, @RequestBody Sale updated) {
        Sale sale = saleRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Sale not found"));

        sale.setCustomerId(updated.getCustomerId());
        sale.setAmount(updated.getAmount());
        sale.setStatus(updated.getStatus());
        sale.setDate(updated.getDate());
        sale.setAssignedSalesRep(updated.getAssignedSalesRep());

        return saleRepository.save(sale);
    }
}