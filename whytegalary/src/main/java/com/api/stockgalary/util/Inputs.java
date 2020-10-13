package com.api.stockgalary.util;

import java.math.BigDecimal;

public class Inputs {
	
	/**
	 * method that converts a string to TitleCase, 
	 * that is, each of the words in the string 
	 * will have the first capital letter 
	 * and all other lower case letters.
	 * @param input string you want to convert
	 * @return this same string capitalized only the first letters of each word
	 */
	public static String toTitleCase(String input) {
		StringBuilder titleCase = new StringBuilder();
		for (Character c : input.trim().toCharArray()) {
			c = (titleCase.length() == 0 
					|| titleCase.charAt(titleCase.length() - 1) <= 0x0022 //" ! and all before space
					|| titleCase.charAt(titleCase.length() - 1) == 0x0027 //'
					|| titleCase.charAt(titleCase.length() - 1) == 0x002D //-
					|| titleCase.charAt(titleCase.length() - 1) == 0x002E //.
					|| titleCase.charAt(titleCase.length() - 1) == 0x003F) //? 
				? Character.toUpperCase(c)
				: Character.toLowerCase(c);
			titleCase.append(c);
		}
		return titleCase.toString();
	}
	
	/**
	 * Converts a string to a BigDecimal value.
	 * remove all commas and validate with regex 
	 * "\\d+\\.?\\d*"
	 * @param input string
	 * @return
	 * Value >=0 if the string is only numeric.
	 * Value -1 if the string is not just numeric.
	 */
	public static BigDecimal toBigDecimal(String input) {
		input = input.replaceAll(",", "");
		if (!input.matches("\\d+\\.?\\d*")) {
			return new BigDecimal(-1);
		}
		return new BigDecimal(input);
	}

}
