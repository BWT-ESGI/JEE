package jee.spring.esgi.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import jee.spring.esgi.dto.RentalPropertyDto;
import jee.spring.esgi.dto.RentalPropertyPatchDto;
import jee.spring.esgi.dto.RentalPropertySearchRequest;
import jee.spring.esgi.model.PropertyType;
import jee.spring.esgi.service.RentalPropertyService;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@WebMvcTest(RentalPropertyController.class)
class RentalPropertyControllerTest {

	@Autowired
	private MockMvc mockMvc;

	@MockBean
	private RentalPropertyService service;

	@Autowired
	private ObjectMapper objectMapper;

	@Test
	void getAll_shouldReturnList() throws Exception {
		RentalPropertyDto dto = new RentalPropertyDto();
		dto.setId(1L);
		dto.setAddress("1 rue Test");
		dto.setTown("Paris");

		Mockito.when(service.findAll()).thenReturn(List.of(dto));

		mockMvc.perform(get("/rent-properties-api/rental-properties"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].id").value(1L))
				.andExpect(jsonPath("$[0].address").value("1 rue Test"));
	}

	@Test
	void getById_shouldReturnDto() throws Exception {
		RentalPropertyDto dto = new RentalPropertyDto();
		dto.setId(2L);
		dto.setAddress("2 rue Test");
		dto.setTown("Lyon");

		Mockito.when(service.findById(2L)).thenReturn(dto);

		mockMvc.perform(get("/rent-properties-api/rental-properties/2"))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$.id").value(2L))
				.andExpect(jsonPath("$.town").value("Lyon"));
	}

	@Test
	void create_shouldReturn201() throws Exception {
		RentalPropertyDto dto = new RentalPropertyDto();
		dto.setAddress("Nouvelle adresse");
		dto.setArea(45.0); // obligatoire
		dto.setDescription("Superbe appartement test");
		dto.setPropertyType(PropertyType.FLAT);
		dto.setRentAmount(900.0);
		dto.setSecurityDepositAmount(1500.0);
		dto.setTown("Paris");

		mockMvc.perform(post("/rent-properties-api/rental-properties")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isCreated());
	}

	@Test
	void upsert_shouldReturn200() throws Exception {
		RentalPropertyDto dto = new RentalPropertyDto();
		dto.setAddress("Adresse maj");
		dto.setArea(60.0);
		dto.setDescription("Mise à jour test");
		dto.setPropertyType(PropertyType.HOUSE);
		dto.setRentAmount(1200.0);
		dto.setSecurityDepositAmount(1500.0);
		dto.setTown("Lyon");

		mockMvc.perform(put("/rent-properties-api/rental-properties/10")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(dto)))
				.andExpect(status().isOk());
	}

	@Test
	void deleteById_shouldReturn204() throws Exception {
		mockMvc.perform(delete("/rent-properties-api/rental-properties/10"))
				.andExpect(status().isNoContent());
	}

	@Test
	void search_shouldReturnList() throws Exception {
		RentalPropertyDto dto = new RentalPropertyDto();
		dto.setId(3L);
		dto.setTown("Vincennes");

		RentalPropertySearchRequest req = new RentalPropertySearchRequest();
		req.setNearVelibStations(true);
		req.setTowns(List.of("Vincennes"));

		Mockito.when(service.search(true, List.of("Vincennes")))
				.thenReturn(List.of(dto));

		mockMvc.perform(post("/rent-properties-api/rental-properties/search")
						.contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(req)))
				.andExpect(status().isOk())
				.andExpect(jsonPath("$[0].town").value("Vincennes"));
	}
}