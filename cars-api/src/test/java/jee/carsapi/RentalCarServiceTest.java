package jee.carsapi;

import jee.carsapi.dto.RentalCarDTO;
import jee.carsapi.entity.RentalCar;
import jee.carsapi.repository.RentalCarRepository;
import jee.carsapi.service.RentalCarService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import java.util.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class RentalCarServiceTest {
    @Mock
    private RentalCarRepository repository;

    @InjectMocks
    private RentalCarService service;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testGetAll() {
        RentalCar car = new RentalCar();
        car.setId(1L);
        car.setBrand("Renault");
        car.setModel("Clio");
        car.setRentAmount(100.0);
        car.setSecurityDepositAmount(500.0);
        car.setNumberOfSeats(5);
        car.setNumberOfDoors(4);
        car.setHasAirConditioning(true);
        when(repository.findAll()).thenReturn(List.of(car));
        List<RentalCarDTO> result = service.getAll();
        assertEquals(1, result.size());
        assertEquals("Renault", result.get(0).getBrand());
    }

    @Test
    void testGetById() {
        RentalCar car = new RentalCar();
        car.setId(2L);
        car.setBrand("Peugeot");
        when(repository.findById(2L)).thenReturn(Optional.of(car));
        Optional<RentalCarDTO> result = service.getById(2L);
        assertTrue(result.isPresent());
        assertEquals("Peugeot", result.get().getBrand());
    }

    @Test
    void testCreate() {
        RentalCarDTO dto = new RentalCarDTO(null, "Fiat", "500", 80.0, 400.0, 4, 3, false);
        RentalCar entity = new RentalCar();
        entity.setId(3L);
        entity.setBrand("Fiat");
        entity.setModel("500");
        entity.setRentAmount(80.0);
        entity.setSecurityDepositAmount(400.0);
        entity.setNumberOfSeats(4);
        entity.setNumberOfDoors(3);
        entity.setHasAirConditioning(false);
        when(repository.save(any(RentalCar.class))).thenReturn(entity);
        RentalCarDTO result = service.create(dto);
        assertEquals("Fiat", result.getBrand());
        assertEquals(3L, result.getId());
    }

    @Test
    void testUpdate() {
        RentalCar existing = new RentalCar();
        existing.setId(4L);
        existing.setBrand("Toyota");
        RentalCarDTO dto = new RentalCarDTO(4L, "Toyota", "Yaris", 90.0, 450.0, 5, 5, true);
        RentalCar updated = new RentalCar();
        updated.setId(4L);
        updated.setBrand("Toyota");
        updated.setModel("Yaris");
        updated.setRentAmount(90.0);
        updated.setSecurityDepositAmount(450.0);
        updated.setNumberOfSeats(5);
        updated.setNumberOfDoors(5);
        updated.setHasAirConditioning(true);
        when(repository.findById(4L)).thenReturn(Optional.of(existing));
        when(repository.save(any(RentalCar.class))).thenReturn(updated);
        Optional<RentalCarDTO> result = service.update(4L, dto);
        assertTrue(result.isPresent());
        assertEquals("Yaris", result.get().getModel());
    }

    @Test
    void testDelete() {
        doNothing().when(repository).deleteById(6L);
        service.delete(6L);
        verify(repository, times(1)).deleteById(6L);
    }
}
