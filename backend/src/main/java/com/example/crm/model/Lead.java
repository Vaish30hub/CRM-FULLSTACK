package com.example.crm.model;

import jakarta.persistence.*;

@Entity
@Table(name = "leads")
public class Lead {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private String contactInfo;
    private String source;

    @Enumerated(EnumType.STRING)
    private LeadStatus status;

    private String assignedSalesRep;

    // getters & setters
    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getContactInfo() { return contactInfo; }
    public void setContactInfo(String contactInfo) { this.contactInfo = contactInfo; }

    public String getSource() { return source; }
    public void setSource(String source) { this.source = source; }

    public LeadStatus getStatus() { return status; }
    public void setStatus(LeadStatus status) { this.status = status; }

    public String getAssignedSalesRep() { return assignedSalesRep; }
    public void setAssignedSalesRep(String assignedSalesRep) {
        this.assignedSalesRep = assignedSalesRep;
    }
}