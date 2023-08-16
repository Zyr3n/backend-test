package ie.jules.salon.util;

import java.lang.reflect.Field;
import java.util.Map;

import ie.jules.salon.model.entitydummies.AppointmentDummy;
import ie.jules.salon.model.entitydummies.ClientDummy;
import ie.jules.salon.model.entitydummies.PurchaseDummy;
import ie.jules.salon.model.entitydummies.ServiceDummy;

public class VerificationUtil {
	private VerificationUtil() {}

	public static boolean verifyClientImport(Map<String, String> csvLine) {
		if (ClientDummy.class.getDeclaredFields().length != csvLine.keySet().size()) {
			return false;
		}
		for (Field field : ClientDummy.class.getDeclaredFields()) {
			if (csvLine.get(field.getName()) == null) {
				return false;
			}
		}
		return true;
	}

	public static boolean verifyAppointmentImport(Map<String, String> csvLine) {
		if (AppointmentDummy.class.getDeclaredFields().length != csvLine.keySet().size()) {
			return false;
		}
		for (Field field : AppointmentDummy.class.getDeclaredFields()) {
			if (csvLine.get(field.getName()) == null) {
				return false;
			}
		}
		return true;
	}

	public static boolean verifyPurchaseImport(Map<String, String> csvLine) {
		if (PurchaseDummy.class.getDeclaredFields().length != csvLine.keySet().size()) {
			return false;
		}
		for (Field field : PurchaseDummy.class.getDeclaredFields()) {
			if (csvLine.get(field.getName()) == null) {
				return false;
			}
		}
		return true;
	}

	public static boolean verifyServiceImport(Map<String, String> csvLine) {
		if (ServiceDummy.class.getDeclaredFields().length != csvLine.keySet().size()) {
			return false;
		}
		for (Field field : ServiceDummy.class.getDeclaredFields()) {
			if (csvLine.get(field.getName()) == null) {
				return false;
			}
		}
		return true;
	}
}
