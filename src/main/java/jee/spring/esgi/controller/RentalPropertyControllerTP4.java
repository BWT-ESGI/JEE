package jee.spring.esgi.controller;

import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.dto.RentalPropertySearchRequest;
import jee.spring.esgi.service.RentalPropertyService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/rental-properties")
public class RentalPropertyControllerTP4 {

    private final RentalPropertyService service;

    public RentalPropertyControllerTP4(RentalPropertyService service) {
        this.service = service;
    }

    @GetMapping("")
    public ResponseEntity<List<RentalPropertyDto>> search(@RequestBody RentalPropertySearchRequest request) {
        return ResponseEntity.ok(
                service.search(
                        request.getNearVelibStations(),
                        request.getTowns() != null ? request.getTowns() : List.of()
                )
        );
    }
}