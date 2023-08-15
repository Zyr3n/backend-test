package ie.jules.salon.model.entity;

import java.time.OffsetDateTime;
import java.util.UUID;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;

@Entity
@Table(name = "appointments")
public class Appointment implements CsvImport {
	@Id
	@Column(name = "id", updatable = false, nullable = false)
	private String id;
	@Column(name = "start_time")
	private OffsetDateTime startTime;
	@Column(name = "end_time")
	private OffsetDateTime endTime;
	@Column(name = "client_id")
	private String clientId;

	public String getClientId() {
		return clientId;
	}

	public void setClientId(String clientId) {
		this.clientId = clientId;
	}

	public OffsetDateTime getStartTime() {
		return startTime;
	}

	public void setStartTime(OffsetDateTime startTime) {
		this.startTime = startTime;
	}

	public OffsetDateTime getEndTime() {
		return endTime;
	}

	public void setEndTime(OffsetDateTime endTime) {
		this.endTime = endTime;
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
