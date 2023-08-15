package ie.jules.salon.model.repository;

import ie.jules.salon.model.entity.Purchase;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface PurchaseRepository extends JpaRepository<Purchase, String> {
    Optional<Purchase> findById(String id);
}