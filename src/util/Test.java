package util;

import java.util.ArrayList;

import model.Camera;
import model.Illumination;
import model.Light;
import model.Matrix;
import model.Object;
import model.Point;
import model.Triangle;
import model.Vector;

public class Test {
	public static void main(String[] args) throws Exception {
		Camera c = new Camera("inputs/camera.cfg");
		System.out.println("Camera\n");
		
		System.out.println(c.C.toString());
		System.out.println(c.N.toString());
		System.out.println(c.V.toString());
		
		System.out.println(c.d);
		System.out.println(c.hx);
		System.out.println(c.hy);
		
		System.out.println();
		
		//Entrega 1 - Ortogonalizar o vetor V (o cálculo é V = V - proj(V)(N), onde proj(V)(N) é a projeção de V sobre N)
		Vector v = BasicOperations.subVector(c.V, BasicOperations.projVinU(c.V, c.N));
		c.V = v;
		
		//Entrega 1 - Fazer o produto vetorial entre eles para calcular o terceiro vetor do sistema de vista ( vetor U )
		Vector u = BasicOperations.vetorialProduct(c.V, c.N);
		
		Object o = new Object("inputs/objeto.byu");
		System.out.println("Object\n");
		
		System.out.println("Points");
		for(Point e : o.points){
			System.out.println(e.toString());
		}
		
		System.out.println("Triangles");
		
		// Lista de normais dos triangulos
		ArrayList<Vector> triangleNormals = new ArrayList<Vector>(); 
		
		for(Triangle e : o.triangles){
			System.out.println("P1: " + e.p1.toString());
			System.out.println("P2: " + e.p2.toString());
			System.out.println("P3: " + e.p3.toString());
			System.out.println();
			
			//Estando os triangulos do objeto carregados, calcular a normal do triângulo e dos vérties (normalizar todos essas normais)
			//Normal do triangulo A, B C é produto vetorial dos vetores B-A e C-A
			
			Vector BA = new Vector(e.p2.x - e.p1.x,e.p2.y - e.p1.y,e.p2.z - e.p1.z);
			Vector CA = new Vector(e.p3.x - e.p1.x,e.p3.y - e.p1.y,e.p3.z - e.p1.z);
			triangleNormals.add(BasicOperations.vetorialProduct(BA, CA));
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

		
		int [][] m1 = new int[3][4];
		int [][] m2 = new int[4][8];
		
		System.out.println();
		System.out.println("Matrix 1:");
		
		for(int m = 0;m< 3;m++) {
			for(int n = 0;n<4;n++) {
				m1[m][n] = (int) ((int)1+ Math.random()*10);
				System.out.print(m1[m][n]+" ");
			}
			System.out.println();
		}
		
		System.out.println("Matrix 2:");
		
		for(int m = 0;m<4;m++) {
			for(int n = 0;n<8;n++) {
				m2[m][n] = (int) ((int)1+ Math.random()*10);
				System.out.print(m2[m][n]+" ");
			}
			System.out.println();
		}
		System.out.println("Result: ");
		Matrix.toString(Matrix.multiply(m1, m2));
		
	}
}
