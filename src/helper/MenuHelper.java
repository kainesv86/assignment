/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package helper;

/**
 *
 * @author Kaine
 */
public class MenuHelper {
	//Content & Size	
	private String[] choices;
	private int length;
	// Init menu
	private int n = 0;
	private static ScannerCus sc = new ScannerCus();

	public MenuHelper(int size) {
		if (size < 1) {
			size = 10;
		}
		this.length = size;
		choices = new String[size];
	}

	public void add(String choice) {
		if (n < choices.length) {
			choices[n++] = choice;
		}
	}	

	public int getChoice() {
		int choice = 0;
		if (n > 0) {
			for (int i = 0; i < n; i++) {
				System.out.println((i+1) + " - " + choices[i]);
			}
			choice = sc.getInt(Integer.MIN_VALUE, n, "Invalid value, Try again","Please select your option from " + 1 + " to " + n +": ");
		}
		return choice;
	}

}
