package com.example.crm.model;

import jakarta.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "sales")
public class Sale {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long customerId;
    private Double amount;

    @Enumerated(EnumType.STRING)
    private SalesStatus status;

    private LocalDate date;
    private String assignedSalesRep;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public Long getCustomerId() { return customerId; }
    public void setCustomerId(Long customerId) { this.customerId = customerId; }

    public Double getAmount() { return amount; }
    public void setAmount(Double amount) { this.amount = amount; }

    public SalesStatus getStatus() { return status; }
    public void setStatus(SalesStatus status) { this.status = status; }

    public LocalDate getDate() { return date; }
    public void setDate(LocalDate date) { this.date = date; }

    public String getAssignedSalesRep() { return assignedSalesRep; }
    public void setAssignedSalesRep(String assignedSalesRep) {
        this.assignedSalesRep = assignedSalesRep;
    }
}