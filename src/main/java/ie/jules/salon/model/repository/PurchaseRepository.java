package ie.jules.salon.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.jules.salon.model.entity.Purchase;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
	Optional<Purchase> findById(String id);

	int deleteByAppointmentId(String id);

	int deletePurchaseById(String id);
}