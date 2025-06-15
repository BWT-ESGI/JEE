package jee.spring.esgi.config;

import jee.spring.esgi.model.PropertyType;
import jee.spring.esgi.entity.RentalProperty;
import jee.spring.esgi.repository.RentalPropertyRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner seedDatabase(RentalPropertyRepository repository) {
        return args -> {

            if (repository.count() == 0) {
                RentalProperty property1 = new RentalProperty();
                property1.setAddress("77 Rue des roses");
                property1.setArea(37.48);
                property1.setDescription("Appartement spacieux avec vue sur l'ESGI");
                property1.setPropertyType(PropertyType.FLAT);
                property1.setRentAmount(750.9);
                property1.setSecurityDepositAmount(1200.9);
                property1.setTown("Paris");

                RentalProperty property2 = new RentalProperty();
                property2.setAddress("12 rue de la Pyramide");
                property2.setArea(62.5);
                property2.setDescription("Maison à louer dans banlieue calme et proche du RER");
                property2.setPropertyType(PropertyType.HOUSE);
                property2.setRentAmount(1050.9);
                property2.setSecurityDepositAmount(1400.9);
                property2.setTown("Champs-sur-Marne");

                RentalProperty property3 = new RentalProperty();
                property3.setAddress("12 rue de la pierre");
                property3.setArea(62.5);
                property3.setDescription("Maison à louer dans banlieue calme et proche du RER");
                property3.setPropertyType(PropertyType.HOUSE);
                property3.setRentAmount(1050.9);
                property3.setSecurityDepositAmount(1400.9);
                property3.setTown("Vincennes");

                repository.save(property1);
                repository.save(property2);
                repository.save(property3);

                System.out.println("🏠 Données de test insérées.");
            } else {
                System.out.println("📦 Données déjà présentes, rien à faire.");
            }
        };
    }
}