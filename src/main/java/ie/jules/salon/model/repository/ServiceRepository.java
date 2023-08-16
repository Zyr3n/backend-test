package ie.jules.salon.model.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import ie.jules.salon.model.entity.Service;

public interface ServiceRepository extends JpaRepository<Service, String> {
	Optional<Service> findById(String id);

	int deleteByAppointmentId(String id);

	int deleteServiceById(String id);
}