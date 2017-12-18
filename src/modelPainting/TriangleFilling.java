package modelPainting;

import java.awt.Color;
import java.util.ArrayList;

import model.Triangle;

public class TriangleFilling {
	
	Canvas4ModelPainting canvas;
	int width;
	int height;
	
	public static void main(String[] args) {
		
	}
	
	public TriangleFilling(Canvas4ModelPainting c, int width, int height){
		this.canvas = c;
		this.width = width;
		this.height = height;
	}
	public void setCanvas(Canvas4ModelPainting c){
		canvas = c;
	}
	public void drawMany(ArrayList<Triangle> ts) throws Exception{
		for(Triangle t : ts)
			drawOne(t);
	}
	public void drawOne(Triangle t) throws Exception{
		if(canvas == null)
			throw new Exception("Faltou chamar setCanvas, fella");
		t = Triangle.sortPointsOfTriangle(t);
		if(t.p2.y == t.p3.y)
			bottomFlat(t);
		else if(t.p1.y == t.p2.y)
			topFlat(t);
		else {
			Triangle[] ts = Triangle.splitTriangle(t);
			Triangle t1 = ts[0];
			Triangle t2 = ts[1];
			
			System.out.println("Triangle 1");
			System.out.println(t1.p1 + " " + t1.p2 + " " + t1.p3);
			System.out.println("Triangle 2");
			System.out.println(t2.p1 + " " + t2.p2 + " " + t2.p3);
			
			bottomFlat(t1);
			topFlat(t2);
		}
	}
	
	private void bottomFlat(Triangle t){
		double invAlfa1 = 1 / Triangle.getAlfa(t.p1, t.p2);
		double invAlfa2 = 1 / Triangle.getAlfa(t.p1, t.p3);
		
		System.out.println("Bottom flat, invalfa 1 " + invAlfa1);
		System.out.println("Bottom flat, invalfa 2 " + invAlfa2);
				
		System.out.println(t.p1);
		System.out.println(t.p2);
		System.out.println(t.p3);
		
		double xMin = (int)t.p1.x;
		double xMax = (int)t.p1.x;
		
		for(int y = (int)t.p1.y; y <= (int)t.p2.y; y++){
			if(xMin > xMax){
				double temp = xMax;
				xMax = xMin;
				xMin = temp;
			}
			for(int x = (int)xMin; x <= xMax; x++){	
				if(insideScreen(x, y)) {
					canvas.drawPixel((int)x, (int)y, new Color(0,0,0));
				}
			}
			xMin += invAlfa1;
			xMax += invAlfa2;
		}
	}
	
	private void topFlat(Triangle t){
		double invAlfa1 = (1 / Triangle.getAlfa(t.p1, t.p3));
		double invAlfa2 = (1 / Triangle.getAlfa(t.p3, t.p2));
		double xMin = (int)t.p3.x;
		double xMax = (int)t.p3.x;
		
		System.out.println("Top flat, invalfa 1 " + invAlfa1);
		System.out.println("Top flat, invalfa 2 " + invAlfa2);
		
		for(int y = (int)t.p3.y; y >= (int)t.p1.y; y--){
			if(xMin > xMax){
				double temp = xMax;
				xMax = xMin;
				xMin = temp;
			}
			for(int x = (int)xMin; x <= xMax; x++){
				if(insideScreen(x, y)) {
					canvas.drawPixel((int)x, (int)y, new Color(0,0,0));
				}
			}
			xMin -= invAlfa1;
			xMax -= invAlfa2;
		}
	}
	
	private boolean insideScreen(int x, int y){
		return y >= 0 && y <= height && x >= 0 && x <= width;
	}
}
