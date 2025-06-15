package jee.spring.esgi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.dto.RentalPropertySearchRequest;
import jee.spring.esgi.service.RentalPropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.mockito.ArgumentMatchers.*;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RentalPropertyControllerTP4.class)
class RentalPropertyControllerTP4Test {

    @Autowired
    private MockMvc mockMvc;

    @MockBean
    private RentalPropertyService service;

    @Autowired
    private ObjectMapper objectMapper;

    @Test
    void search_withNearVelibStationsAndTowns_shouldReturnList() throws Exception {
        RentalPropertyDto dto = new RentalPropertyDto();
        dto.setId(42L);
        dto.setAddress("90 rue de la Victoire");
        dto.setTown("Neuilly-sur-Seine");

        RentalPropertySearchRequest req = new RentalPropertySearchRequest();
        req.setNearVelibStations(true);
        req.setTowns(List.of("Neuilly-sur-Seine"));

        Mockito.when(service.search(eq(true), eq(List.of("Neuilly-sur-Seine"))))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/rental-properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(42))
                .andExpect(jsonPath("$[0].address").value("90 rue de la Victoire"))
                .andExpect(jsonPath("$[0].town").value("Neuilly-sur-Seine"));
    }

    @Test
    void search_withoutTowns_shouldDefaultToEmptyList() throws Exception {
        RentalPropertyDto dto = new RentalPropertyDto();
        dto.setId(100L);
        dto.setAddress("1 place de la République");
        dto.setTown("Paris");

        RentalPropertySearchRequest req = new RentalPropertySearchRequest();
        req.setNearVelibStations(false);

        Mockito.when(service.search(eq(false), eq(List.of())))
                .thenReturn(List.of(dto));

        mockMvc.perform(get("/api/rental-properties")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(req)))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$[0].id").value(100))
                .andExpect(jsonPath("$[0].town").value("Paris"));
    }
}