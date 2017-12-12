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
	
	public ArrayList<Point> viewCoordPoints;
	public ArrayList<Triangle> viewCoordTriangles;
	
	public ArrayList<Point> screenCoordPoints;
	public ArrayList<Triangle> screenCoordTriangles;
	
	public Object(String filepath, Camera c){
		this.points = new ArrayList<Point>();
		this.triangles = new ArrayList<Triangle>();
		this.viewCoordPoints = new ArrayList<Point>();
		this.viewCoordTriangles = new ArrayList<Triangle>();
		this.screenCoordPoints = new ArrayList<Point>();
		this.screenCoordTriangles = new ArrayList<Triangle>();

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
				screenCoordPoints.add(c.viewToScreen(p));
			}
			
			for(int i = 0; i < nbTriangles; i++){
				int[] vertices = FileHandler.getInts(br.readLine());
				triangles.add(this.getTriangle(vertices, points));
				viewCoordTriangles.add(this.getTriangle(vertices, viewCoordPoints));
				screenCoordTriangles.add(this.getTriangle(vertices, screenCoordPoints));
			}
			
			for(int i = 0; i < nbPoints; i++){
				Point worldP = points.get(i);
				Point viewP = viewCoordPoints.get(i);
				Point screenP = screenCoordPoints.get(i);
				worldP.N = BasicOperations.vertexNormalVector(worldP, triangles);
				viewP.N = BasicOperations.vertexNormalVector(viewP, viewCoordTriangles);
				screenP.N = BasicOperations.vertexNormalVector(screenP, screenCoordTriangles);
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	private Triangle getTriangle(int[] vertices, ArrayList<Point> points){
		Point p1 = points.get(vertices[0]-1);
		Point p2 = points.get(vertices[1]-1);
		Point p3 = points.get(vertices[2]-1);
		
		return new Triangle(p1, p2, p3);
	}
}
