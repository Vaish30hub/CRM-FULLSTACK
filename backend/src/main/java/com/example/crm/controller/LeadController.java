package com.example.crm.controller;

import com.example.crm.model.Lead;
import com.example.crm.repository.LeadRepository;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/leads")
public class LeadController {

    private final LeadRepository leadRepository;

    public LeadController(LeadRepository leadRepository) {
        this.leadRepository = leadRepository;
    }

    // CREATE
    @PostMapping
    public Lead createLead(@RequestBody Lead lead) {
        return leadRepository.save(lead);
    }

    // READ ALL
    @GetMapping
    public List<Lead> getAllLeads() {
        return leadRepository.findAll();
    }

    // UPDATE
    @PutMapping("/{id}")
    public Lead updateLead(@PathVariable Long id, @RequestBody Lead updated) {
        Lead lead = leadRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Lead not found"));

        lead.setName(updated.getName());
        lead.setContactInfo(updated.getContactInfo());
        lead.setSource(updated.getSource());
        lead.setStatus(updated.getStatus());
        lead.setAssignedSalesRep(updated.getAssignedSalesRep());

        return leadRepository.save(lead);
    }

    // DELETE
    @DeleteMapping("/{id}")
    public String deleteLead(@PathVariable Long id) {
        leadRepository.deleteById(id);
        return "Lead deleted successfully";
    }
}