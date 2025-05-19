package jee.spring.esgi.repository;

import jee.spring.esgi.entity.RentalProperty;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RentalPropertyRepository extends JpaRepository<RentalProperty, Long> {
}