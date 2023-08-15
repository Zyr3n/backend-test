package ie.jules.salon.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.jules.salon.model.entity.Client;

public interface ClientRepository extends JpaRepository<Client, String>, ClientRepositoryLoyalty {
	Optional<Client> findById(String id);
}
