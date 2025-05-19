package jee.spring.esgi.mapper;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.entity.RentalProperty;

public class RentalPropertyMapper {

    public static RentalPropertyDto toDto(RentalProperty entity) {
        RentalPropertyDto dto = new RentalPropertyDto();
        dto.setAddress(entity.getAddress());
        dto.setArea(entity.getArea());
        dto.setDescription(entity.getDescription());
        dto.setPropertyType(entity.getPropertyType());
        dto.setRentAmount(entity.getRentAmount());
        dto.setSecurityDepositAmount(entity.getSecurityDepositAmount());
        dto.setTown(entity.getTown());
        return dto;
    }
}