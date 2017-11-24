package util;

import model.*;

public class BasicOperations {
	
	public static Point pointByConstant(Point p, double c) {
		return new Point((p.x)*c, (p.y)*c, (p.z)*c);
	}

	public static Point sumPoints(Point p1, Point p2) {
		return new Point(p1.x+p2.x, p1.y+p2.y, p1.z+p2.z);
	}
	
	public static double vectorNorm(Vector v) {
		double sum = 0;
		sum+=v.x*v.x;
		sum+=v.y*v.y;
		sum+=v.z*v.z;
		return Math.sqrt(sum);
	}
	
	
	public static void productVetorial(Vector u, Vector v) {
		
	}
}
