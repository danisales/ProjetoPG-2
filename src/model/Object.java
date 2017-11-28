package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.BasicOperations;
import util.FileHandler;

public class Object {
	public ArrayList<Point> points;
	public ArrayList<Triangle> triangles;
	public ArrayList<Vector> normalVectorsPts;
	
	public ArrayList<Point> viewCoordPoints;
	public ArrayList<Triangle> viewCoordTriangle;
	
	public Object(String filepath, Camera c){
		this.points = new ArrayList<Point>();
		this.triangles = new ArrayList<Triangle>();
		this.viewCoordPoints = new ArrayList<Point>();
		this.viewCoordTriangle = new ArrayList<Triangle>();

		try {
			FileReader fr = new FileReader(new File(filepath));
			BufferedReader br = new BufferedReader(fr);
			
			int[] n = FileHandler.getInts(br.readLine());
			int nbPoints = n[0];
			int nbTriangles = n[1];
			
			for(int i = 0; i < nbPoints; i++){
				Point p = FileHandler.getPoint(br.readLine());
				points.add(p);
				viewCoordPoints.add(c.worldToView(p));
			}
			
			for(int i = 0; i < nbTriangles; i++){
				int[] pts = FileHandler.getInts(br.readLine());
				
				Point p1 = points.get(pts[0]-1);
				Point p2 = points.get(pts[1]-1);
				Point p3 = points.get(pts[2]-1);
				
				Triangle t = new Triangle(p1, p2, p3);
				triangles.add(t);
				
				p1 = viewCoordPoints.get(pts[0]-1);
				p2 = viewCoordPoints.get(pts[1]-1);
				p3 = viewCoordPoints.get(pts[2]-1);
				
				t = new Triangle(p1, p2, p3);
				viewCoordTriangle.add(t);
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.normalVectorsPts = BasicOperations.verticesNormalVectors(points, triangles);
	}
}
