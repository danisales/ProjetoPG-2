package model;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

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
	
	public static ArrayList <Point> sortPointsOfTriangle(Triangle v) {
		ArrayList <Point> points = new ArrayList<Point>();
		points.add(v.p1);
		points.add(v.p2);
		points.add(v.p3);
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return p1.y != p2.y? ((p1.y < p2.y)? 1:0) : ((p1.x < p2.x)?1:0);
			}
		});
		return points;
	}
	
	
	public static double getAlfa(Point p1, Point p2) {
		if(p1.x==p2.x)return -1; //Pontos no mesmo X
		if(p1.y==p2.y)return -2; // Pontos no mesmo Y
		
		return (p2.y - p1.y)/(p2.x - p1.x);
	}
	
	//Usa o alfa de P1 e P2
	public static double getXMin(double currentX, double alfa) {
		return currentX+(1/alfa);
	}
	
	//esta exatamente igual ao Xmin, mas separei pra voce entender. A diferenca e que usaremos o alfa de p2 e p3
	public static double getXMax(double currentX, double alfa) {
		return currentX+(1/alfa);
	}
	
	public static ArrayList<Triangle> splitTriangle(Triangle t){
		//TODO
		
		return null;
	}
	
	public static void main(String args[]) {
		Triangle t = new Triangle(new Point(10,20,30), new Point(40,20,60), new Point(80,90,100));
		ArrayList <Point> points = new ArrayList<Point>();
		points.add(t.p2);
		points.add(t.p3);
		points.add(t.p1);
		points = sortPointsOfTriangle(t);
		
		for(Point p : points) {
			System.out.println("X: "+p.x+" Y: "+p.y);
		}
	}
}
