package jee.spring.esgi.service;

import jee.spring.esgi.entity.RentalProperty;
import jee.spring.esgi.repository.RentalPropertyRepository;
import jee.spring.esgi.client.VelibStationApiClient;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.Import;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

class RentalPropertyServiceTest {

    private final RentalPropertyRepository repository = Mockito.mock(RentalPropertyRepository.class);
    private final VelibStationApiClient apiClient = Mockito.mock(VelibStationApiClient.class);

    private final RentalPropertyService service = new RentalPropertyService(repository, apiClient);

    @Test
    void findById_shouldReturnDto() {
        RentalProperty property = new RentalProperty();
        property.setId(7L);
        property.setTown("Nogent-sur-Marne");
        Mockito.when(repository.findById(7L)).thenReturn(Optional.of(property));

        var dto = service.findById(7L);
        assertThat(dto.getId()).isEqualTo(7L);
        assertThat(dto.getTown()).isEqualTo("Nogent-sur-Marne");
    }
}