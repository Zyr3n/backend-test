package ie.jules.salon.api;

import java.io.IOException;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.opencsv.exceptions.CsvValidationException;

import ie.jules.salon.model.ImportedCsvJson;
import ie.jules.salon.model.entity.Appointment;
import ie.jules.salon.model.entity.Client;
import ie.jules.salon.model.entity.Purchase;
import ie.jules.salon.model.entity.Service;
import ie.jules.salon.service.SalonService;

@RestController
@RequestMapping("/salon")
public class SalonApi {
	private final SalonService salonService;

	@Autowired
	public SalonApi(SalonService salonService) {
		this.salonService = salonService;
	}

	@PostMapping(value = "/importCsv", produces = "application/json", consumes = "application/json")
	public ResponseEntity<String> importCsv(@RequestBody ImportedCsvJson importedCsvJson) {
		ResponseEntity<String> response;
		try {
			response = salonService.importCsvData(importedCsvJson);
		} catch (IOException | CsvValidationException e) {
			return new ResponseEntity<>("{\"Errormessage\":\"Error while parsing CSV\"}",
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
		return response;
	}

	@GetMapping("/clients")
	public List<Client> getAllClients() {
		return salonService.getAllClients();
	}

	@GetMapping("/appointments/{id}")
	public ResponseEntity<Appointment> getAppointmentById(@PathVariable String id) {
		Appointment appointment = salonService.getAppointmentById(id);
		return appointment != null ? new ResponseEntity<>(appointment, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/purchases/{id}")
	public ResponseEntity<Purchase> getPurchaseById(@PathVariable String id) {
		Purchase purchase = salonService.getPurchaseById(id);
		return purchase != null ? new ResponseEntity<>(purchase, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/services/{id}")
	public ResponseEntity<Service> getServiceById(@PathVariable String id) {
		Service service = salonService.getServiceById(id);
		return service != null ? new ResponseEntity<>(service, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/clients/{id}")
	public ResponseEntity<Client> getClientById(@PathVariable String id) {
		Client client = salonService.getClientById(id);
		return client != null ? new ResponseEntity<>(client, HttpStatus.OK)
				: new ResponseEntity<>(HttpStatus.NOT_FOUND);
	}

	@GetMapping("/appointments/byCliendId")
	public List<Appointment> getAppointmentsByDate(@RequestParam String cliendId) {
		return salonService.findAppointmentsByClientId(cliendId);
	}
}
