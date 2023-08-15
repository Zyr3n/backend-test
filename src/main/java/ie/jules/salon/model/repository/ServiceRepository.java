package ie.jules.salon.model.repository;

import ie.jules.salon.model.entity.Service;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ServiceRepository extends JpaRepository<Service, String> {
    Optional<Service> findById(String id);
}