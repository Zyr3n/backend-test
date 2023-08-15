package ie.jules.salon.model.entitydummies;

import java.time.OffsetDateTime;

import ie.jules.salon.model.entity.Appointment;

public class AppointmentDummy {
	private String id;
	private OffsetDateTime start_time;
	private OffsetDateTime end_time;
	private String client_id;

	public Appointment appointmentDummy2Appointment() {
		Appointment appointment = new Appointment();
		appointment.setId(this.id);
		appointment.setStartTime(this.start_time);
		appointment.setEndTime(this.end_time);
		appointment.setClientId(this.client_id);
		return appointment;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public OffsetDateTime getStart_time() {
		return start_time;
	}

	public void setStart_time(OffsetDateTime start_time) {
		this.start_time = start_time;
	}

	public OffsetDateTime getEnd_time() {
		return end_time;
	}

	public void setEnd_time(OffsetDateTime end_time) {
		this.end_time = end_time;
	}

	public String getClient_id() {
		return client_id;
	}

	public void setClient_id(String client_id) {
		this.client_id = client_id;
	}
}
