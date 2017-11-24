package model;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

import util.FileHandler;

public class Illumination {
	public double ka;
	public Vector Ia;
	public double kd;
	public Vector Od;
	public double ks;
	public double n;
	public ArrayList<Light> lights;
	
	public Illumination(String filepath){
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
				double[] Il = FileHandler.getDoubles(br.readLine());
				lights.add(new Light(Pl, Il));
			}
			
			br.close();
		} catch(IOException e) {
			e.printStackTrace();
		}

	}
}
