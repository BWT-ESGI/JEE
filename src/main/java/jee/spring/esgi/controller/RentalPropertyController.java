package jee.spring.esgi.controller;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.service.RentalPropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent-properties-api/rental-properties")
public class RentalPropertyController {

    private final RentalPropertyService service;

    public RentalPropertyController(RentalPropertyService service) {
        this.service = service;
    }

    @GetMapping
    public ResponseEntity<List<RentalPropertyDto>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalPropertyDto> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }
}