package util;

import model.Camera;
import model.Illumination;
import model.Light;
import model.Object;
import model.Point;
import model.Triangle;

public class Test {
	public static void main(String[] args) {
		Camera c = new Camera("inputs/camera.cfg");
		System.out.println("Camera\n");
		
		System.out.println(c.C.toString());
		System.out.println(c.N.toString());
		System.out.println(c.V.toString());
		
		System.out.println(c.d);
		System.out.println(c.hx);
		System.out.println(c.hy);
		
		System.out.println();
		
		Object o = new Object("inputs/objeto.byu");
		System.out.println("Object\n");
		
		System.out.println("Points");
		for(Point e : o.points){
			System.out.println(e.toString());
		}
		
		System.out.println("Triangles");
		for(Triangle e : o.triangles){
			System.out.println("P1: " + e.p1.toString());
			System.out.println("P2: " + e.p2.toString());
			System.out.println("P3: " + e.p3.toString());
			System.out.println();
		}
		
		System.out.println();
		
		Illumination i = new Illumination("inputs/iluminacao.txt");
		System.out.println("Illumination\n");
		
		System.out.println(i.ka);
		System.out.println(i.Ia.toString());
		System.out.println(i.kd);
		System.out.println(i.Od.toString());
		System.out.println(i.ks);
		System.out.println(i.n);
		
		System.out.println("Lights");
		for(Light l : i.lights){
			System.out.println(l.Pl.toString());
			System.out.println(l.Il[0] + " " + l.Il[1] + " " + l.Il[2]);
			System.out.println();
		}
	}
}
