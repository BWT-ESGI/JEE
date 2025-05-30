package jee.spring.esgi.controller;

import jakarta.validation.Valid;
import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.dto.RentalPropertyPatchDto;
import jee.spring.esgi.dto.RentalPropertySearchRequest;
import jee.spring.esgi.service.RentalPropertyService;
import org.springframework.http.HttpStatus;
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

    @PostMapping
    public ResponseEntity<Void> create(@Valid @RequestBody RentalPropertyDto dto) {
        service.create(dto);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> upsert(@PathVariable Long id, @Valid @RequestBody RentalPropertyDto dto) {
        service.upsert(id, dto);
        return ResponseEntity.ok().build();
    }

    @PatchMapping("/{id}")
    public ResponseEntity<Void> patchById(
            @PathVariable Long id,
            @RequestBody RentalPropertyPatchDto patchDto
    ) {
        service.patchById(id, patchDto);
        return ResponseEntity.ok().build(); // 200
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteById(@PathVariable Long id) {
        service.deleteById(id);
        return ResponseEntity.noContent().build(); // 204
    }

    @PostMapping("/search")
    public ResponseEntity<List<RentalPropertyDto>> search(@RequestBody RentalPropertySearchRequest request) {
        return ResponseEntity.ok(
                service.search(
                        request.getNearVelibStations(),
                        request.getTowns() != null ? request.getTowns() : List.of()
                )
        );
    }
}