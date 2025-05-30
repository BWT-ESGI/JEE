package jee.spring.esgi.service;

import jee.spring.esgi.client.VelibStationApiClient;
import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.dto.RentalPropertyPatchDto;
import jee.spring.esgi.exception.ResourceNotFoundException;
import jee.spring.esgi.mapper.RentalPropertyToDtoMapper;
import jee.spring.esgi.mapper.RentalPropertyToEntityMapper;
import jee.spring.esgi.entity.RentalProperty;
import jee.spring.esgi.repository.RentalPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class RentalPropertyService {
    private final VelibStationApiClient velibStationApiClient;
    private final RentalPropertyRepository repository;

    public RentalPropertyService(
            RentalPropertyRepository repository,
            VelibStationApiClient velibStationApiClient
    ) {
        this.velibStationApiClient = velibStationApiClient;
        this.repository = repository;
    }

    public List<RentalPropertyDto> findAll() {
        return repository.findAll()
                .stream()
                .map(RentalPropertyToDtoMapper::toDto)
                .collect(Collectors.toList());
    }

    public RentalPropertyDto findById(Long id) {
        RentalProperty property = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        return RentalPropertyToDtoMapper.toDto(property);
    }

    public void create(RentalPropertyDto dto) {
        RentalProperty property = RentalPropertyToEntityMapper.map(dto);
        repository.save(property);
    }

    public void upsert(Long id, RentalPropertyDto dto) {
        RentalProperty property = repository.findById(id)
                .map(existing -> {
                    RentalProperty updated = RentalPropertyToEntityMapper.map(dto);
                    updated.setId(existing.getId());
                    return updated;
                })
                .orElseGet(() -> {
                    RentalProperty newProp = RentalPropertyToEntityMapper.map(dto);
                    newProp.setId(id);
                    return newProp;
                });

        repository.save(property);
    }

    public void patchById(Long id, RentalPropertyPatchDto patchDto) {
        RentalProperty property = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));

        if (patchDto.getAddress() != null) property.setAddress(patchDto.getAddress());
        if (patchDto.getArea() != null) property.setArea(patchDto.getArea());
        if (patchDto.getDescription() != null) property.setDescription(patchDto.getDescription());
        if (patchDto.getPropertyType() != null) property.setPropertyType(patchDto.getPropertyType());
        if (patchDto.getRentAmount() != null) property.setRentAmount(patchDto.getRentAmount());
        if (patchDto.getSecurityDepositAmount() != null) property.setSecurityDepositAmount(patchDto.getSecurityDepositAmount());
        if (patchDto.getTown() != null) property.setTown(patchDto.getTown());
        if (patchDto.getNumberOfBedrooms() != null) property.setNumberOfBedrooms(patchDto.getNumberOfBedrooms());
        if (patchDto.getFloorNumber() != null) property.setFloorNumber(patchDto.getFloorNumber());
        if (patchDto.getNumberOfFloors() != null) property.setNumberOfFloors(patchDto.getNumberOfFloors());
        if (patchDto.getConstructionYear() != null) property.setConstructionYear(patchDto.getConstructionYear());
        if (patchDto.getEnergyClassification() != null) property.setEnergyClassification(patchDto.getEnergyClassification());
        if (patchDto.getHasElevator() != null) property.setHasElevator(patchDto.getHasElevator());
        if (patchDto.getHasIntercom() != null) property.setHasIntercom(patchDto.getHasIntercom());
        if (patchDto.getHasBalcony() != null) property.setHasBalcony(patchDto.getHasBalcony());
        if (patchDto.getHasParkingSpace() != null) property.setHasParkingSpace(patchDto.getHasParkingSpace());

        repository.save(property);
    }

    public void deleteById(Long id) {
        if (!repository.existsById(id)) {
            throw new ResourceNotFoundException("Property not found with id: " + id);
        }
        repository.deleteById(id);
    }

    public List<RentalPropertyDto> search(Boolean nearVelibStations, List<String> towns) {
        if (Boolean.TRUE.equals(nearVelibStations)) {
            Set<String> townsWithStations = velibStationApiClient.getTownsWithStations(towns);
            return repository.findAll().stream()
                    .filter(rp -> townsWithStations.contains(rp.getTown()))
                    .map(RentalPropertyToDtoMapper::toDto)
                    .toList();
        } else {
            return findAll();
        }
    }
}