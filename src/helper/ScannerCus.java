/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

import java.util.*;
import java.util.regex.Pattern;
import java.util.regex.Matcher;

/**
 *
 * @author Kaine
 */
public class ScannerCus {
	public int getInt(int min, int max, String errorMsg, String msg) {
		int input;
		System.out.print(msg);
		do {
			Scanner sc = new Scanner(System.in);	
			// Wrong type
			if (!sc.hasNextInt()) {
				sc.next();
				System.out.println(errorMsg);
				System.out.print(msg);
			} else {
				input = sc.nextInt();
			//Wrong min max
				if (!Validator.maxMinInt(input, min, max)) {
					System.out.println(errorMsg);
					System.out.print(msg);
				} else {
					break;
				}
			} 
		} while (true);
		
		return input;
	}	

	public boolean getBoolean() {
		MenuHelper menu = new MenuHelper(2);
		menu.add("Yes");
		menu.add("No");
		boolean value = false;
		int choice;
		System.out.println("Please select your command: ");
		do {
			choice = menu.getChoice();
			switch (choice) {
				case 1:
					value = true;
					break;
				case 2:
					value = true;
					break;
			}
		} while (choice != 1 && choice !=2);
		return value;

	}
	
	public double getDouble(double min, double max, String errorMsg, String msg) {
		double input;
		Scanner sc = new Scanner(System.in);
		System.out.print(msg);
		do {
			//Wrong type
			if (!sc.hasNextDouble()) {
				sc.next();
				System.out.println(errorMsg);
				System.out.print(msg);
			} else {
				input = sc.nextDouble();
			//Wrong min max
				if (!Validator.maxMinDouble(input, min, max)) {
					System.out.println(errorMsg);
					System.out.print(msg);
				} else {
					break;
				}
			}
		} while (true);
		return input;
	}

	public  String getString(Validator.StringType type, int min, int max, String msg) {
		String str;
		Scanner sc = new Scanner(System.in);
		System.out.print(msg);
		do {
			str = sc.nextLine().trim();
			
			//check length
			if (str.isEmpty()) {
				System.out.println("Invalid value");
				System.out.print(msg);
			} else 
			if (str.length() > max || str.length() < min) {
				System.out.print(msg);
				
			} else {
		
			//checking string follow pattern
				if (type.equals(Validator.StringType.ALPHABET)) {
					if (!Validator.isAlplabet(str)) {
						System.out.print(msg);
					} else {
						break;
					}
				}
				
				if (type.equals(Validator.StringType.ALPHANUM)) {
					if (!Validator.isAlplaNum(str)) {
						System.out.print(msg);
					} else {
						break;
					}
				}
				if (type.equals(Validator.StringType.ALPHANUMWITHSPACE)) {
					if (!Validator.isAlplaNumWithSpace(str)) {
						System.out.print(msg);
					} else {
						break;
					}
				}
				
				if (type.equals(Validator.StringType.STRING)) {
					if (!Validator.isString(str)) {
						System.out.print(msg);
					} else {
						break;
					}
				}
			} 
		
				
				
		} while (true); 
		
		return str;
	}
}
