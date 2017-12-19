package modelPainting;

import java.awt.Color;
import java.util.ArrayList;

import model.Triangle;
import model.Vector;
import model.Object;
import model.Point;
import model.Illumination;

import util.BasicOperations;

public class TriangleFilling {
	
	Canvas4ModelPainting canvas;
	int width;
	int height;
	double[][] zBuffer;
	Object o;
	Illumination i;
	public static void main(String[] args) {
		
	}
	
	public TriangleFilling(Canvas4ModelPainting c, int width, int height, Object o, Illumination i){
		this.canvas = c;
		this.width = width;
		this.height = height;
		this.zBuffer = this.initZBuffer();
		this.o = o;
		this.i = i;
	}
	public void setCanvas(Canvas4ModelPainting c){
		canvas = c;
	}
	private double[][] initZBuffer(){
		double[][] z = new double[this.width][this.height];
		for(int i = 0; i < this.width; i++){
			for(int j = 0; j < this.height; j++)
				z[i][j] = Double.POSITIVE_INFINITY;
		}
		return z;
	}
	public void drawMany(ArrayList<Triangle> ts) {
		for(int i = 0; i < ts.size(); i++)
			drawOne(ts.get(i), i);
	}
	public void drawOne(Triangle t, int index) {
		t = Triangle.sortPointsOfTriangle(t);
		if(t.p2.y == t.p3.y)
			bottomFlat(t, index);
		else if(t.p1.y == t.p2.y)
			topFlat(t, index);
		else {
			Triangle[] ts = Triangle.splitTriangle(t);
			Triangle t1 = ts[0];
			Triangle t2 = ts[1];
			
			System.out.println("Triangle 1");
			System.out.println(t1.p1 + " " + t1.p2 + " " + t1.p3);
			System.out.println("Triangle 2");
			System.out.println(t2.p1 + " " + t2.p2 + " " + t2.p3);
			
			bottomFlat(t1, index);
			topFlat(t2, index);
		}
	}
	
	private void bottomFlat(Triangle t, int index){
		double invAlfa1 = 1 / Triangle.getAlfa(t.p1, t.p2);
		double invAlfa2 = 1 / Triangle.getAlfa(t.p1, t.p3);
		
		System.out.println("Bottom flat, invalfa 1 " + invAlfa1);
		System.out.println("Bottom flat, invalfa 2 " + invAlfa2);
				
		System.out.println(t.p1);
		System.out.println(t.p2);
		System.out.println(t.p3);
		
		double xMin = (int)t.p1.x;
		double xMax = (int)t.p1.x;
		
		for(int y = (int)t.p1.y; y <= t.p2.y; y++){
			if(xMin > xMax){
				double temp = xMax;
				xMax = xMin;
				xMin = temp;
			}
			drawLine(xMin, xMax, y, index);
			xMin += invAlfa1;
			xMax += invAlfa2;
		}
	}
	
	private void topFlat(Triangle t, int index){
		double invAlfa1 = (1 / Triangle.getAlfa(t.p1, t.p3));
		double invAlfa2 = (1 / Triangle.getAlfa(t.p3, t.p2));
		double xMin = (int)t.p3.x;
		double xMax = (int)t.p3.x;
		
		System.out.println("Top flat, invalfa 1 " + invAlfa1);
		System.out.println("Top flat, invalfa 2 " + invAlfa2);
		
		for(int y = (int)t.p3.y; y >= t.p1.y; y--){
			if(xMin > xMax){
				double temp = xMax;
				xMax = xMin;
				xMin = temp;
			}
			drawLine(xMin, xMax, y, index);
			xMin -= invAlfa1;
			xMax -= invAlfa2;
		}
	}
	
	private void checkPoint(int x, int y, int index){
		Point p = new Point(x, y, 0);
		double[] baryCoord = o.screenCoordTriangles.get(index).getBarycentricCoordinates(p);
		Triangle t = o.viewCoordTriangles.get(index);
		
		Point newP1 = BasicOperations.pointByConstant(t.p1, baryCoord[0]);
		Point newP2 = BasicOperations.pointByConstant(t.p2, baryCoord[1]);
		Point newP3 = BasicOperations.pointByConstant(t.p3, baryCoord[2]);
		Point newP = BasicOperations.sumPoints(newP1, BasicOperations.sumPoints(newP2, newP3));
		
		Vector newV1 = BasicOperations.multiplyByConstant(t.p1.N, baryCoord[0]);
		Vector newV2 = BasicOperations.multiplyByConstant(t.p2.N, baryCoord[1]);
		Vector newV3 = BasicOperations.multiplyByConstant(t.p3.N, baryCoord[2]);
		Vector N = BasicOperations.sumVector(newV1, BasicOperations.sumVector(newV2, newV3));
		
		if(newP.z < zBuffer[x][y]){
			zBuffer[x][y] = newP.z;
			canvas.drawPixel((int)x, (int)y, i.getColor(newP, N));
		}
	}
	
	private void drawLine(double xMin, double xMax, int y, int index){
		for(int x = (int)xMin; x <= xMax; x++){
			if(insideScreen(x, y)) {
				this.checkPoint(x, y, index);
			}
		}
	}
	
	private boolean insideScreen(int x, int y){
		return y >= 0 && y < height && x >= 0 && x < width;
	}
}
