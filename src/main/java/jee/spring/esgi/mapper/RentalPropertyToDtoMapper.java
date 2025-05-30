package jee.spring.esgi.mapper;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.entity.RentalProperty;

public class RentalPropertyToDtoMapper {

    public static RentalPropertyDto toDto(RentalProperty entity) {
        RentalPropertyDto dto = new RentalPropertyDto();
        dto.setId(entity.getId());
        dto.setAddress(entity.getAddress());
        dto.setArea(entity.getArea());
        dto.setDescription(entity.getDescription());
        dto.setPropertyType(entity.getPropertyType());
        dto.setRentAmount(entity.getRentAmount());
        dto.setSecurityDepositAmount(entity.getSecurityDepositAmount());
        dto.setTown(entity.getTown());
        dto.setNumberOfBedrooms(entity.getNumberOfBedrooms());
        dto.setFloorNumber(entity.getFloorNumber());
        dto.setNumberOfFloors(entity.getNumberOfFloors());
        dto.setConstructionYear(entity.getConstructionYear());
        dto.setEnergyClassification(entity.getEnergyClassification());
        dto.setHasElevator(entity.getHasElevator());
        dto.setHasIntercom(entity.getHasIntercom());
        dto.setHasBalcony(entity.getHasBalcony());
        dto.setHasParkingSpace(entity.getHasParkingSpace());
        return dto;
    }
}