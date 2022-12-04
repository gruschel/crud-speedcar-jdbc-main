package br.com.speedcar.model;

import java.time.LocalDate;

public class History {

    private Long id;
    private String customerFullName;
    private String phoneNumber;
    private String emailAddress;
    private String occurrence;

    public History(Long id, String customerFullName, String phoneNumber, String emailAddress, String occurrence) {
        this.id = id;
        this.customerFullName = customerFullName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.occurrence = occurrence;
    }

    public History(String customerFullName, String phoneNumber, String emailAddress, String occurrence) {
        this(null, customerFullName, phoneNumber, emailAddress, occurrence);
    }

    public Long getId() {
        return id;
    }

    public String getCustomerFullName() {
        return customerFullName;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public String getOccurrence() {
        return occurrence;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setCustomerFullName(String customerFullName) {
        this.customerFullName = customerFullName;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public void setOccurrence(String occurrence) {
        this.occurrence = occurrence;
    }


}
