package util;
import model.Vector;
import model.Point;

public class FileHandler {
	
	public static Vector getVector(String line){
		String[] vectorStr = line.split(" ");
		double vX = Double.parseDouble(vectorStr[0]);
		double vY = Double.parseDouble(vectorStr[1]);
		double vZ = Double.parseDouble(vectorStr[2]);
		return new Vector(vX, vY, vZ);
	}
	
	public static Point getPoint(String line){
		String[] pointStr = line.split(" ");
		double pX = Double.parseDouble(pointStr[0]);
		double pY = Double.parseDouble(pointStr[1]);
		double pZ = Double.parseDouble(pointStr[2]);
		return new Point(pX, pY, pZ);
	}

	public static double[] getNumbers(String line){
		String[] numStr = line.split(" ");
		double[] numbers = new double[numStr.length];
		for(int i = 0; i < numStr.length; i++){
			numbers[i] = Double.parseDouble(numStr[i]);
		}
		return numbers;
	}
	
	public static int[] getInts(String line){
		String[] numStr = line.split(" ");
		int[] numbers = new int[numStr.length];
		for(int i = 0; i < numStr.length; i++){
			numbers[i] = Integer.parseInt(numStr[i]);
		}
		return numbers;
	}
}
