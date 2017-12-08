package model;

public class Point {
	public double x;
	public double y;
	public double z;
	public Vector N; // Normal vector
	
	public Point(double x, double y, double z){
		this.x = x;
		this.y = y;
		this.z = z;
	}
	
	public boolean equals(Point p){
		return (this.x == p.x) && (this.y == p.y) && (this.z == p.z);
	}
	
	public String toString(){
		return "(" + this.x + ", " + this.y + ", " + this.z + ")";
	}	
	
	// pro caso das coisas serem invertidas, mudar aqui
	public boolean xGreaterThan(Point p){
		return x > p.x;
	}
	public boolean yGreaterThan(Point p){
		return y > p.y;
	}
}
