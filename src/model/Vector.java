package model;

public class Vector {
	private double x;
	private double y;
	private double z;
	
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z; 
	}
	
	public String toString(){
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
