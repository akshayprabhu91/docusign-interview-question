package com.akshay.java;

/**
 * @author Akshay Prabhu
 *
 */
public class TestClass {

	public static void main(String[] args) {
		Duties duty = new Duties();

		/* Test Cases */
		String inputString = "HOT 8, 6, 4, 2, 1, 7";
		// String inputString = "COLD 8, 6, 3, 4, 2, 5, 1, 7";
		// String inputString = "HOT 8, 6, 6";
		// String inputString = "HOT 8, 6, 3";
		// String inputString = "COLD 8, 6, 3, 4, 2, 5, 7";
		// String inputString = "COLD 6";

		if (!duty.validateInput(inputString)) {
			System.out.println("fail. **Check Input");
		} else {
			if (!duty.validateFirstCmd()) {
				System.out.println("fail");
			} else {
				duty.validationForUniqueness();
				duty.validationForShoes();
				duty.validationForShirt();
				duty.validateAllItemsPresent();

				if ("HOT".equalsIgnoreCase(duty.getCurrentTemp())) {
					duty.validationForSocksAndJacket();
				}

				duty.buildOutputString();
			}
		}
	}
}
