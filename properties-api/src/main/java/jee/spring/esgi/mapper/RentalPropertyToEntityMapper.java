package jee.spring.esgi.mapper;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.entity.RentalProperty;

public class RentalPropertyToEntityMapper {
    public static RentalProperty map(RentalPropertyDto dto) {
        RentalProperty entity = new RentalProperty();
        entity.setAddress(dto.getAddress());
        entity.setArea(dto.getArea());
        entity.setDescription(dto.getDescription());
        entity.setPropertyType(dto.getPropertyType());
        entity.setRentAmount(dto.getRentAmount());
        entity.setSecurityDepositAmount(dto.getSecurityDepositAmount());
        entity.setTown(dto.getTown());
        entity.setNumberOfBedrooms(dto.getNumberOfBedrooms());
        entity.setFloorNumber(dto.getFloorNumber());
        entity.setNumberOfFloors(dto.getNumberOfFloors());
        entity.setConstructionYear(dto.getConstructionYear());
        entity.setEnergyClassification(dto.getEnergyClassification());
        entity.setHasElevator(dto.getHasElevator());
        entity.setHasIntercom(dto.getHasIntercom());
        entity.setHasBalcony(dto.getHasBalcony());
        entity.setHasParkingSpace(dto.getHasParkingSpace());
        return entity;
    }
}