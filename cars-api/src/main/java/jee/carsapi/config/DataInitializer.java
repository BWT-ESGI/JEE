package jee.carsapi.config;


import jee.carsapi.entity.RentalCar;
import jee.carsapi.repository.RentalCarRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedDatabase(RentalCarRepository repository) {
        return args -> {
            if (repository.count() == 0) {
                RentalCar car1 = new RentalCar();
                car1.setBrand("BMW");
                car1.setModel("Serie 1");
                car1.setRentAmount(790.9);
                car1.setSecurityDepositAmount(1550.9);
                car1.setNumberOfSeats(5);
                car1.setNumberOfDoors(4);
                car1.setHasAirConditioning(true);

                RentalCar car2 = new RentalCar();
                car2.setBrand("Mercedes");
                car2.setModel("Classe C Hybride");
                car2.setRentAmount(990.9);
                car2.setSecurityDepositAmount(2400.9);
                car2.setNumberOfSeats(5);
                car2.setNumberOfDoors(4);
                car2.setHasAirConditioning(true);

                RentalCar car3 = new RentalCar();
                car3.setBrand("Peugeot");
                car3.setModel("208");
                car3.setRentAmount(590.9);
                car3.setSecurityDepositAmount(950.9);
                car3.setNumberOfSeats(5);
                car3.setNumberOfDoors(5);
                car3.setHasAirConditioning(false);

                repository.save(car1);
                repository.save(car2);
                repository.save(car3);

                System.out.println("🚗 Données de test (cars) insérées.");
            } else {
                System.out.println("📦 Données déjà présentes (cars), rien à faire.");
            }
        };
    }
}
