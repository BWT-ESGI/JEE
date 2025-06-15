package jee.spring.esgi.entity;

import jakarta.persistence.*;
import jee.spring.esgi.model.PropertyType;
import jakarta.validation.constraints.*;

@Entity
@Table(name = "rental_property")
public class RentalProperty {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank
    private String address;

    @NotNull
    private Double area;

    @NotBlank
    private String description;

    @NotNull
    @Enumerated(EnumType.STRING)
    private PropertyType propertyType;

    @NotNull
    private Double rentAmount;

    @NotNull
    private Double securityDepositAmount;

    @NotNull
    private String town;

    private Integer numberOfBedrooms;

    private Integer floorNumber;

    private Integer numberOfFloors;

    private Integer constructionYear;

    private String energyClassification;

    private Boolean hasElevator;

    private Boolean hasIntercom;

    private Boolean hasBalcony;

    private Boolean hasParkingSpace;

    public Integer getNumberOfBedrooms() {
        return numberOfBedrooms;
    }

    public void setNumberOfBedrooms(Integer numberOfBedrooms) {
        this.numberOfBedrooms = numberOfBedrooms;
    }

    public Integer getFloorNumber() {
        return floorNumber;
    }

    public void setFloorNumber(Integer floorNumber) {
        this.floorNumber = floorNumber;
    }

    public Integer getNumberOfFloors() {
        return numberOfFloors;
    }

    public void setNumberOfFloors(Integer numberOfFloors) {
        this.numberOfFloors = numberOfFloors;
    }

    public Integer getConstructionYear() {
        return constructionYear;
    }

    public void setConstructionYear(Integer constructionYear) {
        this.constructionYear = constructionYear;
    }

    public String getEnergyClassification() {
        return energyClassification;
    }

    public void setEnergyClassification(String energyClassification) {
        this.energyClassification = energyClassification;
    }

    public Boolean getHasElevator() {
        return hasElevator;
    }

    public void setHasElevator(Boolean hasElevator) {
        this.hasElevator = hasElevator;
    }

    public Boolean getHasIntercom() {
        return hasIntercom;
    }

    public void setHasIntercom(Boolean hasIntercom) {
        this.hasIntercom = hasIntercom;
    }

    public Boolean getHasBalcony() {
        return hasBalcony;
    }

    public void setHasBalcony(Boolean hasBalcony) {
        this.hasBalcony = hasBalcony;
    }

    public Boolean getHasParkingSpace() {
        return hasParkingSpace;
    }

    public void setHasParkingSpace(Boolean hasParkingSpace) {
        this.hasParkingSpace = hasParkingSpace;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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