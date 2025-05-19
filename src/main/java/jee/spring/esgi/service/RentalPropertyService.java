package jee.spring.esgi.service;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.exception.ResourceNotFoundException;
import jee.spring.esgi.mapper.RentalPropertyMapper;
import jee.spring.esgi.entity.RentalProperty;
import jee.spring.esgi.repository.RentalPropertyRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RentalPropertyService {

    private final RentalPropertyRepository repository;

    public RentalPropertyService(RentalPropertyRepository repository) {
        this.repository = repository;
    }

    public List<RentalPropertyDto> findAll() {
        return repository.findAll()
                .stream()
                .map(RentalPropertyMapper::toDto)
                .collect(Collectors.toList());
    }

    public RentalPropertyDto findById(Long id) {
        RentalProperty property = repository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Property not found with id: " + id));
        return RentalPropertyMapper.toDto(property);
    }
}