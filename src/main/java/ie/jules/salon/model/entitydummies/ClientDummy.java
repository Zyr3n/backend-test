package ie.jules.salon.model.entitydummies;

import ie.jules.salon.model.entity.Client;

public class ClientDummy {
	private String id;
	private boolean banned;
	private String first_name;
	private String last_name;
	private String email;
	private String phone;
	private String gender;

	public ClientDummy() {}

	public Client clientDummy2Client() {
		Client client = new Client();
		client.setId(this.id);
		client.setBanned(this.banned);
		client.setFirstName(this.first_name);
		client.setLastName(this.last_name);
		client.setEmail(this.email);
		client.setPhone(this.phone);
		client.setGender(this.gender);
		return client;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isBanned() {
		return banned;
	}

	public void setBanned(boolean banned) {
		this.banned = banned;
	}

	public String getFirst_name() {
		return first_name;
	}

	public void setFirst_name(String first_name) {
		this.first_name = first_name;
	}

	public String getLast_name() {
		return last_name;
	}

	public void setLast_name(String last_name) {
		this.last_name = last_name;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}
}
