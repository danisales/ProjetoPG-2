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
	
	public static Triangle sortPointsOfTriangle(Triangle v) {
		ArrayList <Point> points = new ArrayList<Point>();
		points.add(v.p1);
		points.add(v.p2);
		points.add(v.p3);
		Collections.sort(points, new Comparator<Point>() {
			public int compare(Point p1, Point p2) {
				return p1.y != p2.y? ((p1.y >= p2.y)? 1:-1) : ((p1.x >= p2.x)?1:-1);
			}
		});
		return new Triangle(points.get(0), points.get(1), points.get(2));
	}
	
	
	public static double getAlfa(Point p1, Point p2) {
		return (p2.y - p1.y)/(p2.x - p1.x);
	}
	
	public static double area(Triangle t){
		Vector BA = BasicOperations.subPointToVector(t.p2, t.p1);
		Vector CA = BasicOperations.subPointToVector(t.p3, t.p1);
		return BasicOperations.vectorNorm(BasicOperations.vectorProduct(BA, CA)) / 2;
	}
	
	public double[] getBarycentricCoordinates(Point p){
		double areaABC = Triangle.area(this);
		Triangle PBC = new Triangle(p, this.p2, this.p3);
		Triangle PCA = new Triangle(p, this.p3, this.p1);
		double alpha = Triangle.area(PBC) / areaABC;
		double beta = Triangle.area(PCA) / areaABC;
		double gamma = 1 - alpha - beta;
		return new double[] {alpha, beta, gamma};
	}
	
	//Usa o alfa de P1 e P2
	public static double getXMin(double currentX, double alfa) {
		return currentX+(1/alfa);
	}
	
	//esta exatamente igual ao Xmin, mas separei pra voce entender. A diferenca e que usaremos o alfa de p2 e p3
	public static double getXMax(double currentX, double alfa) {
		return currentX+(1/alfa);
	}
	
	public static Triangle[] splitTriangle(Triangle t){
		Triangle tAux = Triangle.sortPointsOfTriangle(t);
		double y = t.p2.y;
		double x = tAux.p1.x + ((tAux.p2.y - tAux.p1.y) / (tAux.p3.y - tAux.p1.y) * (tAux.p3.x - tAux.p1.x)); 
		//double x = ((tAux.p2.y - tAux.p1.y) / Triangle.getAlfa(tAux.p1, tAux.p3)) + tAux.p1.x; // y - y1 = alfa * (x - x1)
		Point p = new Point(x, y, 0);
		Triangle t1 = new Triangle(tAux.p1, tAux.p2, p);
		Triangle t2 = new Triangle(tAux.p2, p, tAux.p3);
		return new Triangle[] {t1, t2};
	}
	
	public static void main(String args[]) {
		Triangle t = new Triangle(new Point(2,5,0), new Point(-2,7,0), new Point(0,10,0));
		Triangle t2 = sortPointsOfTriangle(t);
		
		System.out.println(t2.p1.toString());
		System.out.println(t2.p2.toString());
		System.out.println(t2.p3.toString());
		System.out.println();
		
		Triangle[] ts = Triangle.splitTriangle(t2);
		System.out.println(ts[0].p3.toString());
		System.out.println("T1");
		System.out.println(ts[0].p1.toString());
		System.out.println(ts[0].p2.toString());
		System.out.println(ts[0].p3.toString());
		System.out.println("T2");
		System.out.println(ts[1].p1.toString());
		System.out.println(ts[1].p2.toString());
		System.out.println(ts[1].p3.toString());
		
		System.out.println();
		System.out.println("Triangle area");
		Point p1 = new Point(-2, (double)5/2, 0);
		Point p2 = new Point(3, 0, 0);
		Point p3 = new Point(2, 5, 0);
		Triangle t3 = new Triangle(p1, p2, p3);
		System.out.println(Triangle.area(t3));
		
		System.out.println();
		System.out.println("Barycentric Coodinates");
		Point p = new Point((double)1/2, (double) 7/2, 0);
		double[] b = t3.getBarycentricCoordinates(p);
		System.out.println("alpha = " + b[0]);
		System.out.println("beta = " + b[1]);
		System.out.println("gama = " + b[2]);
		
	}
}
