package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.FileHandler;

public class Object {
	public ArrayList<Point> points;
	public ArrayList<Triangle> triangles;
	
	public Object(String filepath){
		this.points = new ArrayList<Point>();
		this.triangles = new ArrayList<Triangle>();
		
		try {
			FileReader fr = new FileReader(new File(filepath));
			BufferedReader br = new BufferedReader(fr);
			
			int[] n = FileHandler.getInts(br.readLine());
			int nbPoints = n[0];
			int nbTriangles = n[1];
			
			for(int i = 0; i < nbPoints; i++){
				Point p = FileHandler.getPoint(br.readLine());
				points.add(p);
			}
			
			for(int i = 0; i < nbTriangles; i++){
				int[] pts = FileHandler.getInts(br.readLine());
				
				Point p1 = points.get(pts[0]-1);
				Point p2 = points.get(pts[1]-1);
				Point p3 = points.get(pts[2]-1);
				
				Triangle t = new Triangle(p1, p2, p3);
				triangles.add(t);
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
}
