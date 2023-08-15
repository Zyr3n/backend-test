package ie.jules.salon.model.entity;

import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "services")
public class Service implements CsvImport {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	private String name;
	private float price;
	@Column(name = "loyalty_points")
	private Long loyaltyPoints;
	@Column(name = "appointment_id")
	private String appointmentId;

	public String getAppointmentId() {
		return appointmentId;
	}

	public void setAppointmentId(String appointmentId) {
		this.appointmentId = appointmentId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public float getPrice() {
		return price;
	}

	public void setPrice(float price) {
		this.price = price;
	}

	public Long getLoyaltyPoints() {
		return loyaltyPoints;
	}

	public void setLoyaltyPoints(Long loyaltyPoints) {
		this.loyaltyPoints = loyaltyPoints;
	}

	@PrePersist
	public void generateId() {
		if (this.id == null || this.id.isEmpty()) {
			this.id = UUID.randomUUID().toString();
		}
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
