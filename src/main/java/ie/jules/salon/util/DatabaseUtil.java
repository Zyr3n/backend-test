package ie.jules.salon.util;

import java.util.List;

import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.TransactionStatus;
import org.springframework.transaction.support.DefaultTransactionDefinition;

import ie.jules.salon.model.entity.Appointment;
import ie.jules.salon.model.entity.Client;
import ie.jules.salon.model.entity.Purchase;
import ie.jules.salon.model.entity.Service;
import ie.jules.salon.model.repository.AppointmentRepository;
import ie.jules.salon.model.repository.ClientRepository;
import ie.jules.salon.model.repository.PurchaseRepository;
import ie.jules.salon.model.repository.ServiceRepository;

public class DatabaseUtil {
	public static void saveOrUpdateClients(List<Client> clients, ClientRepository clientRepository,
			PlatformTransactionManager transactionManager) {
		for (Client client : clients) {
			TransactionStatus status = null;
			try {
				status = transactionManager.getTransaction(new DefaultTransactionDefinition());
				if (client.getId() != null && clientRepository.existsById(client.getId())) {
					clientRepository.saveAndFlush(client);
				} else {
					clientRepository.save(client);
				}
				transactionManager.commit(status);

			} catch (Exception e) {
				if (status != null) {
					transactionManager.rollback(status);
				}
			}
		}
	}

	public static void saveOrUpdateAppointments(List<Appointment> appointments,
			AppointmentRepository appointmentRepository, PlatformTransactionManager transactionManager) {
		for (Appointment appointment : appointments) {
			TransactionStatus status = null;
			try {
				status = transactionManager.getTransaction(new DefaultTransactionDefinition());
				if (appointment.getId() != null && appointmentRepository.existsById(appointment.getId())) {
					appointmentRepository.saveAndFlush(appointment);
				} else {
					appointmentRepository.save(appointment);
				}
				transactionManager.commit(status);

			} catch (Exception e) {
				if (status != null) {
					transactionManager.rollback(status);
				}
			}
		}
	}

	public static void saveOrUpdatePurchases(List<Purchase> purchases,
			PurchaseRepository purchaseRepository, PlatformTransactionManager transactionManager) {
		for (Purchase purchase : purchases) {
			TransactionStatus status = null;
			try {
				status = transactionManager.getTransaction(new DefaultTransactionDefinition());
				if (purchase.getId() != null && purchaseRepository.existsById(purchase.getId())) {
					purchaseRepository.saveAndFlush(purchase);
				} else {
					purchaseRepository.save(purchase);
				}
				transactionManager.commit(status);

			} catch (Exception e) {
				if (status != null) {
					transactionManager.rollback(status);
				}
			}
		}
	}

	public static void saveOrUpdateServices(List<Service> services,
			ServiceRepository serviceRepository, PlatformTransactionManager transactionManager) {
		for (Service service : services) {
			TransactionStatus status = null;
			try {
				status = transactionManager.getTransaction(new DefaultTransactionDefinition());
				if (service.getId() != null && serviceRepository.existsById(service.getId())) {
					serviceRepository.saveAndFlush(service);
				} else {
					serviceRepository.save(service);
				}
				transactionManager.commit(status);

			} catch (Exception e) {
				if (status != null) {
					transactionManager.rollback(status);
				}
			}
		}
	}
}
