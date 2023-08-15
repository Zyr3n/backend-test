package ie.jules.salon.model.repository;

import java.time.OffsetDateTime;
import java.util.ArrayList;
import java.util.List;

import ie.jules.salon.model.ClientLoyaltyDto;
import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;

public class ClientRepositoryImpl implements ClientRepositoryLoyalty {

	@PersistenceContext
	private EntityManager entityManager;

	@Override
	public List<ClientLoyaltyDto> findTopClientsWithLoyaltyPoints(OffsetDateTime startDate,
			int limit) {
		String sql = "SELECT clients.id, clients.first_name, clients.last_name, clients.email, "
				+ "clients.phone, clients.gender, "
				+ "SUM(services.loyalty_points + purchases.loyalty_points) AS TotalLoyaltyPoints "
				+ "FROM clients " + "LEFT JOIN appointments ON appointments.client_id = clients.id "
				+ "LEFT JOIN services ON services.appointment_id = appointments.id "
				+ "LEFT JOIN purchases ON purchases.appointment_id = appointments.id "
				+ "WHERE clients.banned = false "
				+ "AND appointments.end_time BETWEEN ?1 AND CURRENT_TIMESTAMP "
				+ "GROUP BY clients.id, clients.first_name, clients.last_name, clients.email, clients.phone, clients.gender "
				+ "ORDER BY TotalLoyaltyPoints DESC " + "LIMIT ?2";

		List<Object[]> results = entityManager.createNativeQuery(sql).setParameter(1, startDate)
				.setParameter(2, limit).getResultList();

		List<ClientLoyaltyDto> dtos = new ArrayList<>();

		for (Object[] result : results) {
			ClientLoyaltyDto dto = new ClientLoyaltyDto();
			dto.setId((String) result[0]);
			dto.setFirstName((String) result[1]);
			dto.setLastName((String) result[2]);
			dto.setEmail((String) result[3]);
			dto.setPhone((String) result[4]);
			dto.setGender((String) result[5]);
			dto.setTotalLoyaltyPoints(((Number) result[6]).intValue());
			dtos.add(dto);
		}

		return dtos;
	}
}
