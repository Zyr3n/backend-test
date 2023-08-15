package ie.jules.salon.service;

import ie.jules.salon.model.entity.Service;
import ie.jules.salon.model.repository.AppointmentRepository;
import ie.jules.salon.model.repository.ClientRepository;
import ie.jules.salon.model.repository.PurchaseRepository;
import ie.jules.salon.model.repository.ServiceRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.transaction.PlatformTransactionManager;

import java.util.Optional;

import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class SalonServiceTest {
	private static final String clientId="3rt0jq-230tgbni0";
	private final AppointmentRepository appointmentRepository = mock(AppointmentRepository.class);
	private final ClientRepository clientRepository = mock(ClientRepository.class);
	private final PurchaseRepository purchaseRepository = mock(PurchaseRepository.class);
	private final ServiceRepository serviceRepository = mock(ServiceRepository.class);
	private final PlatformTransactionManager transactionManager = mock(PlatformTransactionManager.class);
	private final SalonService salonService = new SalonService( appointmentRepository,  clientRepository,
			 purchaseRepository,  serviceRepository,
			 transactionManager);
	@Test
	void testGetServiceById(){
		when(serviceRepository.findById(anyString())).thenReturn(Optional.of(new Service()));
		Service service = salonService.getServiceById(clientId);
		Assertions.assertNotNull(service);
	}
}
