package jee.carsapi.service;

import jee.carsapi.dto.RentalCarDTO;
import jee.carsapi.entity.RentalCar;
import jee.carsapi.repository.RentalCarRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RentalCarService {

    @Autowired
    private RentalCarRepository repository;

    public List<RentalCarDTO> getAll() {
        return repository.findAll().stream().map(this::toDto).toList();
    }

    public Optional<RentalCarDTO> getById(Long id) {
        return repository.findById(id).map(this::toDto);
    }

    public RentalCarDTO create(RentalCarDTO dto) {
        RentalCar entity = toEntity(dto);
        return toDto(repository.save(entity));
    }

    public Optional<RentalCarDTO> update(Long id, RentalCarDTO dto) {
        return repository.findById(id).map(existing -> {
            RentalCar entity = toEntity(dto);
            entity.setId(id);
            return toDto(repository.save(entity));
        });
    }

    public Optional<RentalCarDTO> partialUpdate(Long id, RentalCarDTO dto) {
        return repository.findById(id).map(existing -> {
            if (dto.getRentAmount() != null) existing.setRentAmount(dto.getRentAmount());
            // Ajoute d'autres champs au besoin
            return toDto(repository.save(existing));
        });
    }

    public void delete(Long id) {
        repository.deleteById(id);
    }

    // Mapping methods
    private RentalCarDTO toDto(RentalCar entity) {
        RentalCarDTO dto = new RentalCarDTO();
        dto.setId(entity.getId());
        dto.setBrand(entity.getBrand());
        dto.setModel(entity.getModel());
        dto.setRentAmount(entity.getRentAmount());
        dto.setSecurityDepositAmount(entity.getSecurityDepositAmount());
        dto.setNumberOfSeats(entity.getNumberOfSeats());
        dto.setNumberOfDoors(entity.getNumberOfDoors());
        dto.setHasAirConditioning(entity.getHasAirConditioning());
        return dto;
    }

    private RentalCar toEntity(RentalCarDTO dto) {
        RentalCar entity = new RentalCar();
        entity.setId(dto.getId());
        entity.setBrand(dto.getBrand());
        entity.setModel(dto.getModel());
        entity.setRentAmount(dto.getRentAmount());
        entity.setSecurityDepositAmount(dto.getSecurityDepositAmount());
        entity.setNumberOfSeats(dto.getNumberOfSeats());
        entity.setNumberOfDoors(dto.getNumberOfDoors());
        entity.setHasAirConditioning(dto.getHasAirConditioning());
        return entity;
    }
}

