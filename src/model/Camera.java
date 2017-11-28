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
	public double[][] changeOfBasisMatrix;
	
	public Camera(String filepath){
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
		this.changeOfBasisMatrix = this.changeOfBasisMatrix();
	}
	
	public Point worldToView(Point p){
		Point subPt = BasicOperations.subPoints(p, this.C);
		double[][] sub = {{subPt.x, subPt.y, subPt.z}};
		Matrix mult;
		double[] point = new double[3];
		try {
			mult = Matrix.multiply(this.changeOfBasisMatrix, sub);
			point = mult.matrix[0];
		} catch (Exception e) {
			e.printStackTrace();
		}
		return new Point(point[0], point[1], point[2]);
	}
	
	private double[][] changeOfBasisMatrix(){
		double[] U = {this.U.x, this.U.y, this.U.z};
		double[] V = {this.V.x, this.V.y, this.V.z};
		double[] N = {this.N.x, this.N.y, this.N.z};
		double[][] M = {U, V, N};
		return M;
	}
}
