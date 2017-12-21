package model;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.BasicOperations;
import util.FileHandler;

public class Illumination {
	public double ka;
	public Vector Ia;
	public double kd;
	public Vector Od;
	public double ks;
	public double n;
	public ArrayList<Light> lights;
	
	public Illumination(String filepath, Camera c){
		this.lights = new ArrayList<Light>();
		
		try {
			FileReader fr = new FileReader(new File(filepath));
			BufferedReader br = new BufferedReader(fr);
			
			this.ka = FileHandler.getDoubles(br.readLine())[0];
			this.Ia = FileHandler.getVector(br.readLine());
			this.kd = FileHandler.getDoubles(br.readLine())[0];
			this.Od = FileHandler.getVector(br.readLine());
			this.ks = FileHandler.getDoubles(br.readLine())[0];
			this.n = FileHandler.getInts(br.readLine())[0];
			
			int nbLights = FileHandler.getInts(br.readLine())[0];
			for(int i = 0; i < nbLights; i++){
				Point Pl = FileHandler.getPoint(br.readLine());
				Vector Il = FileHandler.getVector(br.readLine());
				lights.add(new Light(c.worldToView(Pl), Il));
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}
	}
	
	public Color getColorMultiSources(Point P, Vector N){
		N = BasicOperations.normalize(N);
		Vector V = BasicOperations.multiplyByConstant(new Vector(P.x, P.y, P.z), -1);
		V = BasicOperations.normalize(V);
		
		if(BasicOperations.scalarProduct(V, N) < 0){
			N = BasicOperations.multiplyByConstant(N, -1);
		}
		
		Vector a = BasicOperations.multiplyByConstant(Ia, ka); // ambiental
		Vector d = new Vector(0, 0, 0); // difusa
		Vector e = new Vector(0, 0, 0); // especular
		
		for(Light light : this.lights){
			Point Pl = light.Pl;
			Vector Il = light.Il;
			
			Vector L = BasicOperations.subPointToVector(Pl, P);
			L = BasicOperations.normalize(L);
			
			if(BasicOperations.scalarProduct(N, L) >= 0){
				Vector R = BasicOperations.subVector(
						BasicOperations.multiplyByConstant(N, 2*BasicOperations.scalarProduct(N, L)),
						L);
				R = BasicOperations.normalize(R);
				
				Vector multOdIl = new Vector(Od.x*Il.x, Od.y*Il.y, Od.z*Il.z);
				d = BasicOperations.sumVector(d, BasicOperations.multiplyByConstant(multOdIl,
						kd*BasicOperations.scalarProduct(N, L)));
				if(BasicOperations.scalarProduct(R, V) >= 0){
					e = BasicOperations.sumVector(e, BasicOperations.multiplyByConstant(Il,
							ks*Math.pow(BasicOperations.scalarProduct(V, R), n)));
				}
			}
		}
		Vector c = BasicOperations.sumVector(a, BasicOperations.sumVector(d, e));
		return this.adjustColor(c);
	}
	
	public Color getColor(Point P, Vector N){
		N = BasicOperations.normalize(N);
		Vector V = BasicOperations.multiplyByConstant(new Vector(P.x, P.y, P.z), -1);
		V = BasicOperations.normalize(V);
		Point Pl = this.lights.get(0).Pl;
		Vector Il = this.lights.get(0).Il;
		Vector L = BasicOperations.subPointToVector(Pl, P);
		L = BasicOperations.normalize(L);
		
		if(BasicOperations.scalarProduct(V, N) < 0){
			N = BasicOperations.multiplyByConstant(N, -1);
		}
		
		if(BasicOperations.scalarProduct(N, L) < 0){
			Vector c = BasicOperations.multiplyByConstant(Ia, ka);
			return this.adjustColor(c);
		}
		
		Vector R = BasicOperations.subVector(
				BasicOperations.multiplyByConstant(N, 2*BasicOperations.scalarProduct(N, L)),
				L);
		R = BasicOperations.normalize(R);
		
		Vector a = BasicOperations.multiplyByConstant(Ia, ka); // ambiental
		Vector multOdIl = new Vector(Od.x*Il.x, Od.y*Il.y, Od.z*Il.z);
		Vector d = BasicOperations.multiplyByConstant(multOdIl,
				kd*BasicOperations.scalarProduct(N, L)); // difusa
		Vector e = BasicOperations.multiplyByConstant(Il,
				ks*Math.pow(BasicOperations.scalarProduct(V, R), n)); // especular
		
		if(BasicOperations.scalarProduct(R, V) < 0){
			return this.adjustColor(BasicOperations.sumVector(a, d));
		}
		return this.adjustColor(BasicOperations.sumVector(a, BasicOperations.sumVector(d, e)));
	}
	
	private Color adjustColor(Vector c){
		return new Color(Math.min((int)c.x, 255), Math.min((int)c.y, 255), Math.min((int)c.z, 255)); 
	}
}
