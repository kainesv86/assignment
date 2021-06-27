/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes;

import Classes.Com.BrandList;
import Classes.Com.CarList;
import helper.*;
import java.io.IOException;

/**
 *
 * @author Kaine
 */
public class CarManager {

	/**
	 * @param args the command line arguments
	 */
	public static void main(String[] args) throws IOException {
		MenuHelper menu = new MenuHelper(11);
		menu.add("List all brands");
		menu.add("Add a new brand");
		menu.add("Search a brand based on its ID");
		menu.add("Update a brand");
		menu.add("Save brands to the file");
		menu.add("List all cars in ascending order of brand names");
		menu.add("List cars based on a part of an input brand name");
		menu.add("Add a car");
		menu.add("Remove a car based on its ID");
		menu.add("Update a car based on its ID");
		menu.add("Save cars to file");

		BrandList blist = new BrandList();

		CarList clist = new CarList(blist, 1000);
		
		if (!blist.loadFromFile("./brands.txt")){
			System.out.println("Load brands.txt failed");
		}

		if (!clist.loadFromFile("./cars.txt")){
			System.out.println("Load cars.txt failed");
		}
			
		int choice = 1;
		ScannerCus sc = new ScannerCus();
		String id = "";
		int index;
		do {
			System.out.println("=================== Menu ===================");
			choice = menu.getChoice();
			System.out.println("============================================");
			switch (choice) {
				case 1:
					blist.listBrands();
					break;
				case 2:
					blist.addBrand();
					break;
				case 3:
					id = sc.getString(Validator.StringType.STRING, 1, 256, "Enter Brand Id to searh: ");
					index = blist.searchID(id);
					if (index != -1) {
						blist.printBrandByIndex(index);
					} else {
						System.out.println("Not Found");
					}
					break;
				case 4:
					blist.updateBrand();
					break;
				case 5:
					if (blist.saveToFile("./brands.txt")) {
						System.out.println("Save to file brands.txt successful.");
					} else {
						System.out.println("Save to file brands.txt failed.");
					} 
					break;
				case 6:
					clist.listCars();
					break;
				case 7:
					clist.printBasedBrandName();
					break;
				case 8:
					clist.addCar();
					break;
				case 9:
					if (clist.removeCar()) {
						System.out.println("Remove succesfull");
					} else {
						System.out.println("Remove failed, car not found");
					};
					break;
				case 10:
					if (clist.updateCar()) {
						System.out.println("Update succesfull");
					} else {
						System.out.println("Update failed, car not found");
					};
					break;
				case 11:
					if (clist.saveToFile("./cars.txt")) {
						System.out.println("Save to file cars.txt successful.");
					} else {
						System.out.println("Save to file cars.txt failed.");
					} 
					break;
				default:
					break;
			}
		} while (choice != 0);
		
	}
	
}
