package jee.spring.esgi.mapper;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.entity.RentalProperty;

public class RentalPropertyToDtoMapper {

    public static RentalPropertyDto toDto(RentalProperty entity) {
        RentalPropertyDto dto = new RentalPropertyDto();
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

    public static RentalProperty toEntity(RentalPropertyDto dto) {
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