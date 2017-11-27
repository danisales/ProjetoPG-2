package model;

import util.BasicOperations;

public class Triangle {
	public Point p1, p2, p3;
	public Vector N;
	
	public Triangle(Point p1, Point p2, Point p3){
		this.p1 = p1;
		this.p2 = p2;
		this.p3 = p3;
		this.N = this.normal();
	}
	
	public boolean hasPoints(Point p){
		return p.equals(this.p1) || p.equals(this.p2) || p.equals(this.p3);
	}
	
	private Vector normal(){
		Vector BA = new Vector(this.p2.x - this.p1.x,this.p2.y - this.p1.y,this.p2.z - this.p1.z);
		Vector CA = new Vector(this.p3.x - this.p1.x,this.p3.y - this.p1.y,this.p3.z - this.p1.z);
		Vector normal = BasicOperations.vectorProduct(BA, CA);
		return BasicOperations.normalize(normal);
	}
}
