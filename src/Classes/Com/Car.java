/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Classes.Com;

/**
 *
 * @author Kaine
 */
public class Car {
	private String carID;	
	private Brand brand;	
	private String color;
	private String frameID;
	private String engineID;

	public Car() {
	}

	public Car(String carID, Brand brand, String color, String frameID, String engineID) {
		this.carID = carID;
		this.brand = brand;
		this.color = color;
		this.frameID = frameID;
		this.engineID = engineID;
	}

	public String getCarID() {
		return carID;
	}

	public void setCarID(String carID) {
		this.carID = carID;
	}

	public Brand getBrand() {
		return brand;
	}

	public void setBrand(Brand brand) {
		this.brand = brand;
	}

	public String getColor() {
		return color;
	}

	public void setColor(String color) {
		this.color = color;
	}

	public String getFrameID() {
		return frameID;
	}

	public void setFrameID(String frameID) {
		this.frameID = frameID;
	}

	public String getEngineID() {
		return engineID;
	}

	public void setEngineID(String engineID) {
		this.engineID = engineID;
	}

	public int comparedTo(Car c) {
		int result = this.brand.getBrandName().compareTo(c.brand.getBrandName());
		if (result != 0) return result;
		return this.carID.compareTo(c.getCarID());
	}
	
	@Override
	public String toString() {
		return (this.carID + ", " + this.brand.getBrandID() + ", " + this.color + ", " + this.frameID + ", " + this.engineID); 
	}

	public String screenString() {
		return (this.brand + ",\n" + this.carID + ", " + this.color + ", " + this.frameID + ", " + this.engineID);	
	}

	Object carID() {
		throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
	}
}
