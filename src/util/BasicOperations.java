package util;

import java.util.ArrayList;

import model.*;

public class BasicOperations {
	
	public static Point sumPoints(Point p1, Point p2) {
		return new Point(p1.x+p2.x, p1.y+p2.y, p1.z+p2.z);
	}
	
	public static Point subPoints(Point p1, Point p2) {
		//return sumPoints(p1, pointByConstant(p2, -1));
		return new Point(p1.x-p2.x, p1.y-p2.y, p1.z-p2.z);	
	}
	
	public static Point pointByConstant(Point p, double c) {
		return new Point((p.x)*c, (p.y)*c, (p.z)*c);
	}
	
	public static Point dividePointByConstant(Point p, double c) {
		return new Point((p.x)/c, (p.y)/c, (p.z)/c);
	}

	public static Vector sumVector(Vector v1, Vector v2) {
		return new Vector(v1.x+v2.x, v1.y+v2.y, v1.z+v2.z);
	}
	
	public static Vector subVector(Vector v1, Vector v2) {
		return new Vector(v1.x-v2.x, v1.y-v2.y, v1.z-v2.z);	
	}
		
	
	public static Vector multiplyByConstant(Vector v, double c) {
		return new Vector(c*v.x, c*v.y, c*v.z);
	}
	
	public static double vectorNorm(Vector v) {
		double sum = 0;
		sum+=v.x*v.x;
		sum+=v.y*v.y;
		sum+=v.z*v.z;
		return Math.sqrt(sum);
	}
	
	public static Vector divideByConstant(Vector v, double c) {
		return new Vector(v.x/c, v.y/c, v.z/c);
	}
	
	public static Vector normalize(Vector v) {
		double norm = vectorNorm(v);
		return divideByConstant(v, norm);
	}
	//Inner product == scalar product
	public static double scalarProduct(Vector u, Vector v) {
		return (u.x*v.x + u.y*v.y + u.z*u.z);
	}
	
	public static Vector vectorProduct(Vector u, Vector v) {
		double i = (u.y*v.z) - (u.z*v.y);
		double j = (u.z*v.x) - (u.x*v.z);
		double k = (u.x*v.y) - (u.y*v.x);
		return new Vector(i, j, k);
	}
	
	public static Vector projVinU(Vector v, Vector u) {
		double normU = vectorNorm(u);
		double constant = scalarProduct(v, u) / (normU*normU);
		return multiplyByConstant(u, constant);
	}
	
	public static Vector gramSchmidt(Vector v, Vector n){
		return subVector(v, projVinU(v, n));
	}
	
	public static Vector vertexNormalVector(Point p, ArrayList<Triangle> triangles){
		Vector v = new Vector(0,0,0);
		for(Triangle t : triangles){
			if(t.hasPoints(p)){
				v = sumVector(v, t.N);
			}
		}
		return normalize(v);
	}
	
	public static ArrayList<Vector> verticesNormalVectors(ArrayList<Point> points, ArrayList<Triangle> triangles){
		ArrayList<Vector> normalVectors = new ArrayList<Vector>();
		for(Point p : points){
			normalVectors.add(vertexNormalVector(p, triangles));
		}
		return normalVectors;
	}
}
