package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Input {

	public int validateNum(String num, String inputName) {
		String regex = "^-?\\d+(\\.\\d+)?$";
		if (num.length() == 0 || num == null || !num.matches(regex))
			throw new ValidationException(inputName + " is Invalid");

		return Integer.parseInt(num);

	}

	public String validateStr(String input, String inputName) {

		if (input == null || input.trim().isEmpty()) {
			throw new ValidationException("The " + inputName + " is null or empty");
		}

		if (!input.matches("[a-zA-Z ]+")) {
			throw new ValidationException("The " + inputName + " contains invalid characters");
		}

		return input;
	}

	public LocalDate getLocalDate(String inputDate, boolean canBeAboveNow, String inputName) {
		DateTimeFormatter primaryFormatter = DateTimeFormatter.ofPattern("MM/dd/yyyy");
		DateTimeFormatter fallbackFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate now = LocalDate.now();

		inputDate = inputDate.trim();

		LocalDate date;

		try {
			date = LocalDate.parse(inputDate, primaryFormatter);
		} catch (DateTimeParseException e1) {
			try {
				date = LocalDate.parse(inputDate, fallbackFormatter);
			} catch (DateTimeParseException e2) {
				throw new ValidationException(
						"Invalid " + inputName + " date format. Please use MM/DD/YYYY or YYYY-MM-DD.");
			}
		}

		if (canBeAboveNow && date.isBefore(now)) {
			throw new ValidationException(inputName + " date must be today or a future date.");
		}

		return date;
	}

	public String getPhone(String inputPhone) {
		String sanitizedInput = inputPhone.replaceAll("[\\s\\-]", "");

		if (sanitizedInput.matches("\\+[0-9]{10,14}")) {
			return sanitizedInput;
		} else {
			throw new ValidationException(
					"Phone number must be like +XXXXXXXXXX (10 to 14 numbers, can contain spaces or dashes).");
		}
	}

}