package jee.carsapi.controller;


import jee.carsapi.dto.RentalCarDTO;
import jee.carsapi.service.RentalCarService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/rent-cars-api/rental-cars")
public class RentalCarController {

    @Autowired
    private RentalCarService service;

    @GetMapping
    public List<RentalCarDTO> getAll() {
        return service.getAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<RentalCarDTO> getById(@PathVariable Long id) {
        return service.getById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody RentalCarDTO dto) {
        service.create(dto);
        return ResponseEntity.status(201).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable Long id, @Valid @RequestBody RentalCarDTO dto) {
        return service.update(id, dto).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> partialUpdate(@PathVariable Long id, @RequestBody RentalCarDTO dto) {
        return service.partialUpdate(id, dto).isPresent()
                ? ResponseEntity.ok().build()
                : ResponseEntity.badRequest().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}

