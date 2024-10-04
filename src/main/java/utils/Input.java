package utils;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class Input {

	public int validateNum(String num) {
		String regex = "^-?\\d+(\\.\\d+)?$";
		if (num.length() == 0 || num == null || !num.matches(regex))
			throw new ValidationException("Number is Invalid");

		return Integer.parseInt(num);

	}

	public String validateStr(String input) {

		if (input == null || input.trim().isEmpty()) {
			throw new ValidationException("The input is null or empty");
		}

		if (!input.matches("[a-zA-Z ]+")) {
			throw new ValidationException("The input contains invalid characters");
		}

		return input;
	}

	public LocalDate getLocalDate(String inputDate, boolean canBeAboveNow) {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy");
		LocalDate now = LocalDate.now();

		try {
			LocalDate date = LocalDate.parse(inputDate, formatter);

			if (canBeAboveNow || (!canBeAboveNow && date.isBefore(now))) {
				return date;
			} else {
				throw new ValidationException("Date must be before " + now);
			}
		} catch (DateTimeParseException e) {
			throw new ValidationException("Invalid date format. Please use DD/MM/YYYY.");
		}
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