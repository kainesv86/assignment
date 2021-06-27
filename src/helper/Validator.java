package helper;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author Kaine
 */
public class Validator {

	//type for checking string

	public static enum StringType {
		ALPHABET,
		ALPHANUM,
		ALPHANUMWITHSPACE,
		STRING,
	}
	
	 //Checking string with pattern
	
	public static boolean isAlplabet(String str) {
		return (str.matches("^[a-zA-Z ]*$"));
	}
	
	public static boolean isAlplaNum(String str) {
		return (str.matches("^[a-zA-Z0-9]*$"));
	}
	
	public static boolean isAlplaNumWithSpace(String str) {
		return (str.matches("^[a-zA-Z0-9 ]*$"));
	}

	public static boolean isString(String str) {
		return !str.equals("");
	}
	
	 //Checking number with pattern

	public static boolean maxMinInt(int number, int min, int max) {
		return number <= max && number >= min;
	}
	
	public static boolean maxMinDouble(double number, double min, double max) {
		return number <= max && number >= min;
	}
	
}
