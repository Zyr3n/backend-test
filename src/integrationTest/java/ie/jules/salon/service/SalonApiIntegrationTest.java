package ie.jules.salon.service;

import java.nio.file.Files;
import java.nio.file.Path;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.fasterxml.jackson.databind.ObjectMapper;

import ie.jules.salon.model.ImportedCsvJson;
import jakarta.persistence.EntityManager;

@SpringBootTest
@AutoConfigureMockMvc
@ActiveProfiles("integrationTest")
public class SalonApiIntegrationTest {

	private static final String CSV_IMPORT_PATH = "/salon/importCsv";
	private static final String PATH_TO_APPOINTMENTS_IMPORT =
			"src/test/resources/appointmentsImport.json";
	private static final String PATH_TO_CLIENTS_IMPORT = "src/test/resources/clientsImport.json";
	private static final String PATH_TO_SERVICES_IMPORT = "src/test/resources/servicesImport.json";
	private static final String PATH_TO_PURCHASES_IMPORT = "src/test/resources/purchasesImport.json";
	private final ObjectMapper objectMapper = new ObjectMapper();
	@Autowired
	private MockMvc mockMvc;
	@Autowired
	private EntityManager entityManager;

	@Test
	public void testImportCsvClients() throws Exception {
		ImportedCsvJson importClients = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_CLIENTS_IMPORT)), ImportedCsvJson.class);

		mockMvc
				.perform(MockMvcRequestBuilders.post(CSV_IMPORT_PATH).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(importClients)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("Import successful"));

		mockMvc.perform(MockMvcRequestBuilders.get("/salon/clients/e0b8ebfc-6e57-4661-9546-328c644a3764"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testImportCsvAppointments() throws Exception {
		ImportedCsvJson importAppointments = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_APPOINTMENTS_IMPORT)), ImportedCsvJson.class);

		mockMvc
				.perform(MockMvcRequestBuilders.post(CSV_IMPORT_PATH).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(importAppointments)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("Import successful"));

		mockMvc
				.perform(MockMvcRequestBuilders.get("/salon/appointments/7416ebc3-12ce-4000-87fb-82973722ebf4"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testImportCsvServices() throws Exception {
		ImportedCsvJson importServices = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_SERVICES_IMPORT)), ImportedCsvJson.class);

		mockMvc
				.perform(MockMvcRequestBuilders.post(CSV_IMPORT_PATH).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(importServices)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("Import successful"));

		mockMvc
				.perform(MockMvcRequestBuilders.get("/salon/services/f1fc7009-0c44-4f89-ac98-5de9ce58095c"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}

	@Test
	public void testImportCsvPurchases() throws Exception {
		ImportedCsvJson importPurchases = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_PURCHASES_IMPORT)), ImportedCsvJson.class);

		mockMvc
				.perform(MockMvcRequestBuilders.post(CSV_IMPORT_PATH).contentType(MediaType.APPLICATION_JSON)
						.content(objectMapper.writeValueAsString(importPurchases)))
				.andExpect(MockMvcResultMatchers.status().isOk())
				.andExpect(MockMvcResultMatchers.jsonPath("$.response").value("Import successful"));

		mockMvc
				.perform(MockMvcRequestBuilders.get("/salon/purchases/d2d3b92d-f9b5-48c5-bf31-88c28e3b73ac"))
				.andExpect(MockMvcResultMatchers.status().isOk());
	}
}
