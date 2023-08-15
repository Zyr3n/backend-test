package ie.jules.salon.model.repository;

import java.time.OffsetDateTime;
import java.util.List;

import ie.jules.salon.model.ClientLoyaltyDto;

public interface ClientRepositoryLoyalty {
	List<ClientLoyaltyDto> findTopClientsWithLoyaltyPoints(OffsetDateTime startDate, int limit);
}
