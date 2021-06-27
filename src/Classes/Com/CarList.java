/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

import helper.*;
import java.io.*;
import java.util.ArrayList;
import java.util.StringTokenizer;

/**
 *
 * @author Kaine
 */
public class CarList {
	private BrandList brandList = new BrandList();
	private ArrayList<Car> list = new ArrayList<Car>(1000);
	public CarList(BrandList blist, int size) {
		this.brandList = blist;
	}

	public boolean loadFromFile(String fileName) throws FileNotFoundException, IOException {
		File f = new File(fileName);
		if (!f.exists()) return false;
		FileReader fr = new FileReader(f);
		BufferedReader bf = new BufferedReader(fr);
		String details;
		Brand brand = null;
		while ((details = bf.readLine()) != null) {
				boolean checkAdding = true;
				StringTokenizer stk = new StringTokenizer(details);
				String carID = stk.nextToken(",").trim();
				String  brandID= stk.nextToken(",").trim();
				int index =this.brandList.searchID(brandID);
				if (index != -1) brand = this.brandList.getBrandByIndex(index);
				else {
					checkAdding = false;
					brand = new Brand();
				}
				String color = stk.nextToken(",").trim();
				String frameID = stk.nextToken(",").trim();
				String engineID = stk.nextToken().trim();
				Car e = new Car(carID, brand, color, frameID, engineID);
				if (checkAdding) this.list.add(e);
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

	private void listSort() {
		int n = this.list.size();
		for (int i = 0; i < n - 1; i++) {
			for (int j = 0; j < n-i-1; j++) {
				if (this.list.get(i).comparedTo(this.list.get(j))== 1) {
					Car temp = this.list.get(i);
					this.list.set(i, this.list.get(j));
					this.list.set(j, temp);
				}
			}
		}
	}


	public int searchID(String ID) {
		for (int i = 0; i < this.list.size(); i++) {
			if (ID == null ? this.list.get(i).getCarID() == null : ID.equals(this.list.get(i).getCarID()))
				return i;
		}
		return -1;
	}
	
	public int searchFrame(String fID) {
		for (int i = 0; i < this.list.size(); i++) {
			if (fID == null ? this.list.get(i).getFrameID() == null : fID.equals(this.list.get(i).getFrameID()))
				return i;
		}
		return -1;
	}
	
	public int searchEngine(String eID) {
		for (int i = 0; i < this.list.size(); i++) {
			if (eID == null ? this.list.get(i).getFrameID() == null : eID.equals(this.list.get(i).getFrameID()))
				return i;
		}
		return -1;
	}

	public void addCar() {
		ScannerCus sc = new ScannerCus();
		
		Car e = null;
		String carID = "";
		do {
			boolean check = false;
			carID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter carID to add: ");
			for (int i= 0; i < this.list.size(); i++) {
				if (!(this.list.get(i).getCarID()== null ? carID == null : this.list.get(i).getCarID().equals(carID))) {
				} else {
					check = true;
					System.out.println(carID + " is already exist, try another one.");
					break;
				}
			}
			if (!check) break; 
		} while (true);

		Brand brand = this.brandList.getUserChoice();
		String color = sc.getString(Validator.StringType.STRING, 1, 256, "Enter color to add: ");

		String frameID;
		String engineID;
		
		do {
			do {
				frameID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter frameID to add (must be F00000): ");
			} while (!frameID.matches("^F*[0-9]{5}"));
			if (this.searchFrame(frameID) == -1) break;
			System.out.println(frameID + " is already, try another one.");
		} while (true);
		
		do {
			do {
				engineID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter engineID to add (must be E00000): ");
			} while (!engineID.matches("^E*[0-9]{5}"));
			if (this.searchEngine(engineID) == -1) break;
			System.out.println(engineID + "is already, try another one.");
		} while (true);
			
		e = new Car(carID, brand, color, frameID, engineID);
		
		if (!this.list.contains(e)) {
			list.add(e);
		} 
	}

	public void printBasedBrandName() {
		ScannerCus sc = new ScannerCus();
		int n = this.list.size();
		int count = 0;
		String brandName = sc.getString(Validator.StringType.STRING, 1, 256, "Enter brand name to find car(s): ");
		for (int i = 0; i < n; i++){
			String tempName =  this.list.get(i).getBrand().getBrandName();
			if (tempName.contains(brandName)) {
				System.out.println(this.list.get(i).toString());
				count++;
			};	
		}
		if (count == 0) {
			System.out.println("No car is detected");
		}
	}


	public boolean removeCar() {
		ScannerCus sc = new ScannerCus();
		String carID = sc.getString(Validator.StringType.STRING, 0, 256, "Enter Car Id to remove: ");
		int index = this.searchID(carID);
		if (index != -1) {
			this.list.remove(index);
			return true;
		}	
		return false;
	}
	public boolean updateCar() {
		ScannerCus sc = new ScannerCus();
		String carID = sc.getString(Validator.StringType.STRING, 0, 256, "Enter Car Id to update: ");
		int index = this.searchID(carID);
		if (index != -1) {
			Brand brand = this.brandList.getUserChoice();
			String color = sc.getString(Validator.StringType.STRING, 1, 256, "Enter color to add: ");

			String frameID;
			String engineID;
		
			do {
				do {
					frameID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter frameID to add (must be F00000): ");
				} while (!frameID.matches("^F*[0-9]{5}"));
				if (this.searchFrame(frameID) == -1) break;
				System.out.println(frameID + " is already, try another one.");
			} while (true);
		
			do {
				do {
					engineID = sc.getString(Validator.StringType.STRING, 1, 256, "Enter engineID to add (must be E00000): ");
				} while (!engineID.matches("^E*[0-9]{5}"));
				if (this.searchEngine(engineID) == -1) break;
				System.out.println(engineID + "is already, try another one.");
			} while (true);
			Car e = new Car(carID, brand, color, frameID, engineID);	
			this.list.set(index, e);
			return true;
		}	
		return false;
	}	

	public void listCars() {
		this.listSort();
		System.out.println("==============================");
		this.list.forEach(item -> System.out.println(item.toString()));
		System.out.println("==============================");
	} 
	
}
