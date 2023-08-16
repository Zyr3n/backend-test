package ie.jules.salon.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.ArrayList;
import java.util.Optional;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.opencsv.exceptions.CsvValidationException;

import ie.jules.salon.model.ImportedCsvJson;
import ie.jules.salon.model.entity.Appointment;
import ie.jules.salon.model.entity.Client;
import ie.jules.salon.model.entity.Purchase;
import ie.jules.salon.model.entity.Service;
import ie.jules.salon.model.repository.AppointmentRepository;
import ie.jules.salon.model.repository.ClientRepository;
import ie.jules.salon.model.repository.PurchaseRepository;
import ie.jules.salon.model.repository.ServiceRepository;

class SalonServiceTest {
	private static final String PATH_TO_APPOINTMENTS_IMPORT =
			"src/test/resources/appointmentsImport.json";
	private static final String PATH_TO_CLIENTS_IMPORT = "src/test/resources/clientsImport.json";
	private static final String PATH_TO_SERVICES_IMPORT = "src/test/resources/servicesImport.json";
	private static final String PATH_TO_PURCHASES_IMPORT = "src/test/resources/purchasesImport.json";
	private static final String clientId = "3rt0jq-230tgbni0";
	private static final String TABLE_NAME_CLIENTS = "CLIENTS";
	private static final String COULD_NOT_READ_CSV_ERROR =
			"{\"response\":\"Could not read entities from CSV\"}";
	private final AppointmentRepository appointmentRepository = mock(AppointmentRepository.class);
	private final ClientRepository clientRepository = mock(ClientRepository.class);
	private final PurchaseRepository purchaseRepository = mock(PurchaseRepository.class);
	private final ServiceRepository serviceRepository = mock(ServiceRepository.class);
	private final PlatformTransactionManager transactionManager =
			mock(PlatformTransactionManager.class);
	private final TransactionStatus status = mock(TransactionStatus.class);
	private final SalonService salonService = new SalonService(appointmentRepository, clientRepository,
			purchaseRepository, serviceRepository, transactionManager);
	private final ObjectMapper objectMapper = new ObjectMapper();

	@Test
	void testGetServiceById(){
		when(serviceRepository.findById(anyString())).thenReturn(Optional.of(new Service()));
		Service service = salonService.getServiceById(clientId);
		assertNotNull(service);
	}

	@Test
	void testImportCsvDataPositiveClients() throws IOException, CsvValidationException {
		ImportedCsvJson importClients = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_CLIENTS_IMPORT)), ImportedCsvJson.class);

		when(clientRepository.existsById(anyString())).thenReturn(false);
		when(clientRepository.save(any(Client.class))).thenReturn(new Client());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importClients);

		verify(clientRepository, times(2)).save(any(Client.class));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testImportCsvDataPositiveAppointments() throws IOException, CsvValidationException {
		ImportedCsvJson importAppointments = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_APPOINTMENTS_IMPORT)), ImportedCsvJson.class);

		when(appointmentRepository.existsById(anyString())).thenReturn(false);
		when(appointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importAppointments);

		verify(appointmentRepository, times(2)).save(any(Appointment.class));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testImportCsvDataPositivePurchases() throws IOException, CsvValidationException {
		ImportedCsvJson importPurchases = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_PURCHASES_IMPORT)), ImportedCsvJson.class);

		when(purchaseRepository.existsById(anyString())).thenReturn(false);
		when(purchaseRepository.save(any(Purchase.class))).thenReturn(new Purchase());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importPurchases);

		verify(purchaseRepository, times(2)).save(any(Purchase.class));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testImportCsvDataPositiveService() throws IOException, CsvValidationException {
		ImportedCsvJson importServices = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_SERVICES_IMPORT)), ImportedCsvJson.class);

		when(serviceRepository.existsById(anyString())).thenReturn(false);
		when(serviceRepository.save(any(Service.class))).thenReturn(new Service());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importServices);

		verify(serviceRepository, times(2)).save(any(Service.class));
		assertEquals(HttpStatus.OK, response.getStatusCode());
	}

	@Test
	void testImportCsvDataNegativeService() throws IOException, CsvValidationException {
		ImportedCsvJson importedServices = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_SERVICES_IMPORT)), ImportedCsvJson.class);
		importedServices.setTableName(TABLE_NAME_CLIENTS);

		when(serviceRepository.existsById(anyString())).thenReturn(false);
		when(serviceRepository.save(any(Service.class))).thenReturn(new Service());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importedServices);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(COULD_NOT_READ_CSV_ERROR, response.getBody());
	}

	@Test
	void testImportCsvDataNegativeClients() throws IOException, CsvValidationException {
		ImportedCsvJson importClients = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_CLIENTS_IMPORT)), ImportedCsvJson.class);
		importClients.setTableName("APPOINTMENTS");

		when(clientRepository.existsById(anyString())).thenReturn(false);
		when(clientRepository.save(any(Client.class))).thenReturn(new Client());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importClients);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(COULD_NOT_READ_CSV_ERROR, response.getBody());
	}

	@Test
	void testImportCsvDataNegativeAppointments() throws IOException, CsvValidationException {
		ImportedCsvJson importAppointments = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_APPOINTMENTS_IMPORT)), ImportedCsvJson.class);
		importAppointments.setTableName(TABLE_NAME_CLIENTS);

		when(appointmentRepository.existsById(anyString())).thenReturn(false);
		when(appointmentRepository.save(any(Appointment.class))).thenReturn(new Appointment());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importAppointments);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(COULD_NOT_READ_CSV_ERROR, response.getBody());
	}

	@Test
	void testImportCsvDataNegativePurchases() throws IOException, CsvValidationException {
		ImportedCsvJson importPurchases = objectMapper
				.readValue(Files.readAllBytes(Path.of(PATH_TO_PURCHASES_IMPORT)), ImportedCsvJson.class);
		importPurchases.setTableName(TABLE_NAME_CLIENTS);

		when(purchaseRepository.existsById(anyString())).thenReturn(false);
		when(purchaseRepository.save(any(Purchase.class))).thenReturn(new Purchase());
		when(transactionManager.getTransaction(any(DefaultTransactionDefinition.class)))
				.thenReturn(status);
		doNothing().when(transactionManager).commit(any(TransactionStatus.class));

		ResponseEntity<String> response = salonService.importCsvData(importPurchases);

		assertEquals(HttpStatus.BAD_REQUEST, response.getStatusCode());
		assertEquals(COULD_NOT_READ_CSV_ERROR, response.getBody());
	}

	@Test
	void testFindMostLoyalClients() {
		LocalDate date = LocalDate.now();
		LocalDateTime localDateTime = date.atStartOfDay();
		int limit = 25;
		OffsetDateTime offsetDateTime = localDateTime.atOffset(ZoneOffset.UTC);
		when(clientRepository.findTopClientsWithLoyaltyPoints(any(OffsetDateTime.class), any(int.class)))
				.thenReturn(new ArrayList<>());

		salonService.findMostLoyalClients(date, limit);
		verify(clientRepository, times(1)).findTopClientsWithLoyaltyPoints(offsetDateTime, limit);
	}
}
