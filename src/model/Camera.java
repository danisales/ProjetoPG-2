package model;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;

import util.BasicOperations;
import util.FileHandler;

public class Camera {
	public double d, hx, hy;
	public Vector V, N, U;
	public Point C;
	
	public int width;
	public int height;
	
	public Camera(String filepath, int width, int height){
		try {
			FileReader fr = new FileReader(new File(filepath));
			BufferedReader br = new BufferedReader(fr);
			
			this.C = FileHandler.getPoint(br.readLine());
			this.N = FileHandler.getVector(br.readLine());
			this.V = FileHandler.getVector(br.readLine());
			
			double[] nums = FileHandler.getDoubles(br.readLine());
			
			this.d = nums[0];
			this.hx = nums[1];
			this.hy = nums[2];
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
		
		this.N = BasicOperations.normalize(this.N);
		this.V = BasicOperations.normalize(BasicOperations.gramSchmidt(V, N));
		this.U = BasicOperations.vectorProduct(N, V);
		this.width = width;
		this.height = height;
	}
	
	public Point worldToView(Point p){
		return BasicOperations.subPoints(p, this.C);
	}
	
	public Point viewToScreen(Point p){
		Point pAux = new Point((d/hx)*(p.x/p.z), (d/hy)*(p.y/p.z),0);
		int x = ((int)((pAux.x + 1) * (this.width/2)));
		int y = ((int)((1 - pAux.y) * (this.height/2)));
		return new Point(x, y, 0);
	}
}
