package jee.spring.esgi.dto;

import jee.spring.esgi.model.PropertyType;

public class RentalPropertyDto {

    private String address;
    private Double area;
    private String description;
    private PropertyType propertyType;
    private Double rentAmount;
    private Double securityDepositAmount;
    private String town;

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public Double getArea() {
        return area;
    }

    public void setArea(Double area) {
        this.area = area;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public PropertyType getPropertyType() {
        return propertyType;
    }

    public void setPropertyType(PropertyType propertyType) {
        this.propertyType = propertyType;
    }

    public Double getRentAmount() {
        return rentAmount;
    }

    public void setRentAmount(Double rentAmount) {
        this.rentAmount = rentAmount;
    }

    public Double getSecurityDepositAmount() {
        return securityDepositAmount;
    }

    public void setSecurityDepositAmount(Double securityDepositAmount) {
        this.securityDepositAmount = securityDepositAmount;
    }

    public String getTown() {
        return town;
    }

    public void setTown(String town) {
        this.town = town;
    }
}