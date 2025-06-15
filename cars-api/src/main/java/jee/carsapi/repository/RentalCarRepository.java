package jee.carsapi.repository;


import jee.carsapi.entity.RentalCar;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalCarRepository extends JpaRepository<RentalCar, Long> {
}

