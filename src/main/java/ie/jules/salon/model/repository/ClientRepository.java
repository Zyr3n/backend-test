package ie.jules.salon.model.repository;

import ie.jules.salon.model.entity.Client;
import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Client, String> {
    Optional<Client> findById(String id);
}