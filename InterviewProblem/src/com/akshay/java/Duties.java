package com.akshay.java;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

/**
 * @author Akshay Prabhu
 *
 */
public class Duties {

	private Map<String, String> hotResponses = new HashMap<>();
	private Map<String, String> coldResponses = new HashMap<>();
	private List<String> userInput = new ArrayList<>();
	private String finalOutputString, currentTemp;

	/**
	 * When "Duties" is instantiated, 
	 * this no-argument constructor 
	 * will set the values for Maps
	 */
	public Duties() {
		hotResponses.put("1", "sandals");
		hotResponses.put("2", "sun visor");
		hotResponses.put("4", "t-shirt");
		hotResponses.put("6", "shorts");
		hotResponses.put("7", "leaving house");
		hotResponses.put("8", "Removing PJs");

		coldResponses.put("1", "boots");
		coldResponses.put("2", "hat");
		coldResponses.put("3", "socks");
		coldResponses.put("4", "shirt");
		coldResponses.put("5", "jacket");
		coldResponses.put("6", "pants");
		coldResponses.put("7", "leaving house");
		coldResponses.put("8", "Removing PJs");
	}

	/**
	 * Validates the input string
	 * 
	 * @param str the string to be validated.
	 * @return true if the input sting is valid, false otherwise.
	 */
	public boolean validateInput(String str) {
		boolean flag = false;
		currentTemp = str.substring(0, str.indexOf(" "));
		String cmds = str.substring(str.indexOf(" ") + 1);

		if ("HOT".equalsIgnoreCase(currentTemp)) {
			storeInput(cmds);
			flag = true;
		} else if ("COLD".equalsIgnoreCase(currentTemp)) {
			storeInput(cmds);
			flag = true;
		}
		return flag;
	}

	/**
	 * Points the global variable userInput 
	 * to the commands entered by the user.
	 * 
	 * @param cmds from the input string.
	 */
	public void storeInput(String cmds) {
		userInput = Arrays.asList(cmds.split(", "));
	}

	/**
	 * Validates "Pajamas must be taken off before anything else can be put on".
	 * 
	 * @return true if the Pajamas are taken off, false otherwise.
	 */
	public boolean validateFirstCmd() {
		// return (userInput.get(0)).equals("8") ? true : false;
		boolean flag = false;
		if ((userInput.get(0)).equals("8")) {
			finalOutputString = "Removing PJs";
			flag = true;
		}
		return flag;
	}

	/**
	 * Validates "You cannot put on socks when it is hot 
	 * You cannot put on a jacket when it is hot".
	 */
	public void validationForSocksAndJacket() {
		String currentDuty;
		for (int i = 1; i < userInput.size(); i++) {
			currentDuty = hotResponses.get(userInput.get(i));
			if (currentDuty == null) {
				finalOutputString += ", fail";
				printFinalOutputString();
			} else {
				finalOutputString += ", " + currentDuty;
			}
		}
		printFinalOutputString();
	}

	/**
	 * Validates "Only 1 piece of each type of clothing may be put on".
	 */
	public void validationForUniqueness() {
		Set<String> se = new HashSet<>(userInput);
		if (se.size() < userInput.size()) {
			buildFailureString(userInput.size() - 1);
		}
	}

	/**
	 * Validates "Socks must be put on before shoes 
	 * Pants must be put on before shoes".
	 */
	public void validationForShoes() {
		int indexOfSocks = userInput.indexOf("3");
		int indexOfPants = userInput.indexOf("6");
		int indexOfShoes = userInput.indexOf("1");

		if (indexOfSocks > 0 && indexOfShoes > 0) {
			if (indexOfShoes < indexOfSocks) {
				buildFailureString(indexOfSocks);
			}
		}

		if (indexOfPants > 0 && indexOfShoes > 0) {
			if (indexOfShoes < indexOfPants) {
				buildFailureString(indexOfPants);
			}
		}
	}

	/**
	 * Validates "You cannot leave the house until all items of clothing are on
	 * (except socks and a jacket when it’s hot)".
	 */
	public void validateAllItemsPresent() {
		if (!userInput.containsAll(getSpecificMap().keySet())) {
			buildFailureString(userInput.size() - 1);
		}
	}

	/**
	 * Validates "The shirt must be put on before the headwear or jacket".
	 */
	public void validationForShirt() {
		int indexOfHeadwear = userInput.indexOf("2");
		int indexOfJacket = userInput.indexOf("5");
		int indexOfShirt = userInput.indexOf("4");

		if (indexOfShirt > 0 && indexOfJacket > 0) {
			if (indexOfJacket < indexOfShirt) {
				buildFailureString(indexOfJacket);
			}
		}

		if (indexOfShirt > 0 && indexOfHeadwear > 0) {
			if (indexOfHeadwear < indexOfShirt) {
				buildFailureString(indexOfHeadwear);
			}
		}
	}

	/**
	 * Gives us the correct associated Map
	 * 
	 * @return the Map associated with the input string
	 */
	public Map<String, String> getSpecificMap() {
		return ("HOT".equalsIgnoreCase(currentTemp)) ? hotResponses : coldResponses;
	}

	/**
	 * Builds the final output string. 
	 * Success string to be specific, 
	 * if nothing fails.
	 */
	public void buildOutputString() {
		String currentDuty;
		for (int i = 1; i < userInput.size(); i++) {
			currentDuty = getSpecificMap().get(userInput.get(i));
			if (currentDuty != null) {
				finalOutputString += ", " + currentDuty;
			}
		}
		printFinalOutputString();
	}

	/**
	 * Builds the failure string. 
	 * Executes whenever a failure situation appears.
	 * 
	 * @param size index of failure occurrence
	 */
	public void buildFailureString(int size) {
		for (int i = 1; i < size; i++) {
			finalOutputString += ", " + getSpecificMap().get(userInput.get(i));
		}
		finalOutputString += ", fail";
		printFinalOutputString();
	}

	/**
	 * Prints the final output and exits.
	 */
	public void printFinalOutputString() {
		System.out.println(finalOutputString);
		System.exit(0);
	}

	/**
	 * @return String the current temperature
	 */
	public String getCurrentTemp() {
		return currentTemp;
	}
}
