package ie.jules.salon.model.entitydummies;

import ie.jules.salon.model.entity.Purchase;

public class PurchaseDummy {
	private String id;
	private String name;
	private float price;
	private Long loyalty_points;
	private String appointment_id;

	public Purchase purchaseDummy2Purchase() {
		Purchase purchase = new Purchase();
		purchase.setId(this.id);
		purchase.setName(this.name);
		purchase.setPrice(this.price);
		purchase.setLoyaltyPoints(this.loyalty_points);
		purchase.setAppointmentId(this.appointment_id);
		return purchase;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
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

	public Long getLoyalty_points() {
		return loyalty_points;
	}

	public void setLoyalty_points(Long loyalty_points) {
		this.loyalty_points = loyalty_points;
	}

	public String getAppointment_id() {
		return appointment_id;
	}

	public void setAppointment_id(String appointment_id) {
		this.appointment_id = appointment_id;
	}
}
