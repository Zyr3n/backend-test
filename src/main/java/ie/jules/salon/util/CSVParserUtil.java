package ie.jules.salon.util;

import java.io.IOException;
import java.io.StringReader;
import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import com.opencsv.CSVReaderHeaderAware;
import com.opencsv.exceptions.CsvValidationException;

import ie.jules.salon.model.entity.Appointment;
import ie.jules.salon.model.entity.Client;
import ie.jules.salon.model.entity.Purchase;
import ie.jules.salon.model.entity.Service;
import ie.jules.salon.model.entitydummies.AppointmentDummy;
import ie.jules.salon.model.entitydummies.ClientDummy;
import ie.jules.salon.model.entitydummies.PurchaseDummy;
import ie.jules.salon.model.entitydummies.ServiceDummy;

public class CSVParserUtil {
	private static final DateTimeFormatter DATE_FORMATTER =
			DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss Z");

	public static List<Client> parseClients(String csvData)
			throws IOException, CsvValidationException {
		try (StringReader reader = new StringReader(csvData);
				CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)) {

			List<ClientDummy> dummies = new ArrayList<>();
			Map<String, String> line;
			while ((line = csvReader.readMap()) != null) {
				ClientDummy dummy = new ClientDummy();

				dummy.setId(line.get("id"));
				dummy.setFirst_name(line.get("first_name"));
				dummy.setLast_name(line.get("last_name"));
				dummy.setEmail(line.get("email"));
				dummy.setPhone(line.get("phone"));
				dummy.setGender(line.get("gender"));
				dummy.setBanned(Boolean.parseBoolean(line.get("banned")));

				dummies.add(dummy);
			}

			return dummies.stream().map(ClientDummy::clientDummy2Client).collect(Collectors.toList());
		}
	}

	public static List<Appointment> parseAppointments(String csvData)
			throws CsvValidationException, IOException {

		try (StringReader reader = new StringReader(csvData);
				CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)) {

			List<AppointmentDummy> dummies = new ArrayList<>();
			Map<String, String> line;

			while ((line = csvReader.readMap()) != null) {
				AppointmentDummy dummy = new AppointmentDummy();

				dummy.setId(line.get("id"));
				dummy.setStart_time(OffsetDateTime.parse(line.get("start_time"), DATE_FORMATTER));
				dummy.setEnd_time(OffsetDateTime.parse(line.get("end_time"), DATE_FORMATTER));
				dummy.setClient_id(line.get("client_id"));

				dummies.add(dummy);
			}

			return dummies.stream().map(AppointmentDummy::appointmentDummy2Appointment)
					.collect(Collectors.toList());
		}
	}

	public static List<Purchase> parsePurchases(String csvData)
			throws IOException, CsvValidationException {
		try (StringReader reader = new StringReader(csvData);
				CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)) {

			List<PurchaseDummy> dummies = new ArrayList<>();
			Map<String, String> line;
			while ((line = csvReader.readMap()) != null) {
				PurchaseDummy dummy = new PurchaseDummy();

				dummy.setId(line.get("id"));
				dummy.setName(line.get("name"));
				dummy.setPrice(Float.parseFloat(line.get("price")));
				dummy.setLoyalty_points(Long.parseLong(line.get("loyalty_points")));
				dummy.setAppointment_id(line.get("appointment_id"));

				dummies.add(dummy);
			}

			return dummies.stream().map(PurchaseDummy::purchaseDummy2Purchase).collect(Collectors.toList());
		}
	}

	public static List<Service> parseServices(String csvData)
			throws IOException, CsvValidationException {
		try (StringReader reader = new StringReader(csvData);
				CSVReaderHeaderAware csvReader = new CSVReaderHeaderAware(reader)) {

			List<ServiceDummy> dummies = new ArrayList<>();
			Map<String, String> line;
			while ((line = csvReader.readMap()) != null) {
				ServiceDummy dummy = new ServiceDummy();

				dummy.setId(line.get("id"));
				dummy.setAppointment_id(line.get("appointment_id"));
				dummy.setName(line.get("name"));
				dummy.setPrice(Float.parseFloat(line.get("price")));
				dummy.setLoyalty_points(Long.parseLong(line.get("loyalty_points")));

				dummies.add(dummy);
			}

			return dummies.stream().map(ServiceDummy::serviceDummy2Service).collect(Collectors.toList());
		}
	}
}
