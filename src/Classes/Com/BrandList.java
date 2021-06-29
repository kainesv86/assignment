/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

import helper.MenuHelper;
import helper.ScannerCus;
import helper.Validator;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Kaine
 */
public class BrandList {
	private ArrayList<Brand> list = new ArrayList<Brand>();
	
	public BrandList(int size) {
		this.list = new ArrayList<Brand>();
	}
	
	public BrandList() {
		this.list = new ArrayList<Brand>(1000);
	}

	public boolean loadFromFile(String fileName) throws FileNotFoundException, IOException {
		File f = new File(fileName);
		if (!f.exists()) return false;
		FileReader fr = new FileReader(f);
		BufferedReader bf = new BufferedReader(fr);
		String details;
		while ((details = bf.readLine()) != null) {
				StringTokenizer stk = new StringTokenizer(details);
				String brandID = stk.nextToken(",").trim();
				String brandName= stk.nextToken(",").trim();
				String soundBrand= stk.nextToken(":").trim();
				soundBrand = soundBrand.substring(2);
				double price = Double.parseDouble((stk.nextToken()));
//				System.out.println("===========================");
//				System.out.println("brand ID: " + brandID);
//				System.out.println("brand name: " + brandName);
//				System.out.println("brand sound: " + soundBrand);
//				System.out.println("Price: " + price);
//				System.out.println("===========================");
				Brand e = new Brand(brandID, brandName, soundBrand, price);
				this.list.add(e);
			}
		return true;
	}
	public boolean saveToFile(String fileName) throws FileNotFoundException, IOException {
		File f = new File(fileName);
		if (!f.exists()) return false;
		try (PrintWriter out = new PrintWriter(f)) {
			this.list.forEach(item -> out.println(item.toString()));
		}
		return true;
	}

	public int searchID(String id) {
		int n = list.size();
		for (int i = 0; i < n; i++ ) {
			if (list.get(i).getBrandID() == null ? id == null : list.get(i).getBrandID().equals(id))
				return i;
		}
		return -1;
	}

	public Brand getUserChoice() {
		int n = this.list.size();
		MenuHelper menu = new MenuHelper(n - 1);
		this.list.forEach(item -> menu.add(item.toString()));
		int choice = 0;
		Brand e = null;
		do {
			System.out.println("========== Brand choice Menu =======");
			choice = menu.getChoice();
			System.out.println("====================================");
			if (choice >= 1 && choice <= n - 1) {
				e = this.list.get(choice - 1);
				break;		  
			}
		} while (true);
		return e;
	}

	public void addBrand() {
		ScannerCus sc = new ScannerCus();
		
		Brand e = null;
		String brandID = "";
		do {
			boolean check = false;
			brandID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter brandID to add: ");
			for (int i= 0; i < this.list.size(); i++) {
				if (!(this.list.get(i).getBrandID() == null ? brandID == null : this.list.get(i).getBrandID().equals(brandID))) {
				} else {
					check = true;
					System.out.println(brandID + " is areadly exist, try another one.");
					break;
				}
			}
			if (!check) break; 
		} while (true);
		String brandName = sc.getString(Validator.StringType.STRING, 1, 256, "Enter brandName to add: ");
		String soundBrand = sc.getString(Validator.StringType.STRING, 1, 256, "Enter soundBrand to add: ");
		double price = sc.getDouble(0, Double.MAX_VALUE, "Invalid Value", "Enter price to add: ");
			
		e = new Brand(brandID, brandName, soundBrand, price);
		
		if (!this.list.contains(e)) {
			list.add(e);
		} 
	}

	public void updateBrand() {
		
		ScannerCus sc = new ScannerCus();
		
		String id = sc.getString(Validator.StringType.STRING, 1, 256, "Enter brandID to find: ");
		int index  = this.searchID(id);
		if (index != -1) {
			String brandID = "";
			do {
				boolean check = false;
				brandID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter brandID to Update: ");
				for (int i= 0; i < this.list.size(); i++) {
					if (index != i) {
						if (!(this.list.get(i).getBrandID() == null ? brandID == null : this.list.get(i).getBrandID().equals(brandID))) {
						} else {
							check = true;
							System.out.println(brandID + " is areadly exist, try another one.");
							break;
						}
					}
				}
				if (!check) break; 
			} while (true);
			String brandName = sc.getString(Validator.StringType.STRING, 1, 256, "Enter brandName to Update: ");
			String soundBrand = sc.getString(Validator.StringType.STRING, 1, 256, "Enter soundBrand to Update: ");
			double price = sc.getDouble(0, Double.MAX_VALUE, "Invalid Value", "Enter price to Update: ");
			this.list.set(index, new Brand(brandID, brandName, soundBrand, price));
		}
	}

	public void listBrands() {
			System.out.println("========================================");
		this.list.forEach(action -> {
			System.out.println(action.toString());
		});
			System.out.println("========================================");
	}

	public void printBrandByIndex(int index) {
		if (index != 1) {
			System.out.println("Found: " + this.list.get(index).toString());
			return;
		}
		System.out.println("Not Found");
	}
	public Brand getBrandByIndex(int index) {
		return this.list.get(index);
	}
		
}
