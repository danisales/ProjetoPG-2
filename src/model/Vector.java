package model;

public class Vector {
	public double x;
	public double y;
	public double z;
	
	public Vector(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z; 
	}
	
	public String toString(){
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}
}
