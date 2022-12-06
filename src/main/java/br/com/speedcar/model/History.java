package br.com.speedcar.model;

public class History {

    private Long id;

    public void setVehicle(String vehicle) {
        this.vehicle = vehicle;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    private String customerFullName;
    private String phoneNumber;
    private String emailAddress;
    private String occurrence;

    public String getVehicle() {
        return vehicle;
    }

    public boolean isActive() {
        return active;
    }

    private String vehicle;
    private boolean active;

    public History(Long id, String customerFullName, String phoneNumber, String emailAddress, String occurrence, String vehicle, boolean active) {
        this.id = id;
        this.customerFullName = customerFullName;
        this.phoneNumber = phoneNumber;
        this.emailAddress = emailAddress;
        this.occurrence = occurrence;
        this.vehicle = vehicle;
        this.active = true;
    }

    public History(String customerFullName, String phoneNumber, String emailAddress, String occurrence,String vehicle, boolean active) {
        this(null, customerFullName, phoneNumber, emailAddress, occurrence,vehicle, active);
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
