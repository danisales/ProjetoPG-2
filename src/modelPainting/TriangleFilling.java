package modelPainting;

import java.awt.Color;

import model.Point;
import model.Triangle;

public class TriangleFilling {
	
	Canvas4ModelPainting canvas;
	int width;
	int height;
	
	public static void main(String[] args) {
		
	}
	
	public TriangleFilling(Canvas4ModelPainting c, int width, int height){
		this.canvas = c;
	}
	public void setCanvas(Canvas4ModelPainting c){
		canvas = c;
	}
	public void drawMany(Triangle[] ts) throws Exception{
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
			Triangle t1 = Triangle.sortPointsOfTriangle(ts[0]);
			Triangle t2 = Triangle.sortPointsOfTriangle(ts[1]);
			bottomFlat(t2);
			topFlat(t1);
		}
	}
	
	private void bottomFlat(Triangle t){
		double invAlfa1 = 1 / Triangle.getAlfa(t.p1, t.p2);
		double invAlfa2 = 1 / Triangle.getAlfa(t.p1, t.p3);
		double xMin = (int)t.p1.x;
		double xMax = (int)t.p1.x;
		for(int i = (int)t.p1.y; i <= (int)t.p2.y; i++){
			for(int j = (int)xMin; j <= xMax; j++){	
				if(i >= 0 && i < width && j >= 0 && j < height) {
					canvas.drawPixel((int)j, (int)i, new Color(0,0,0));
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
		for(int i = (int)t.p3.y; i > (int)t.p1.y; i++){
			for(int j = (int)xMin; j < xMax; j++){
				if(i >= 0 && i < width && j >= 0 && j < height) {
					canvas.drawPixel((int)j, (int)i, new Color(0,0,0));
				}
			}
			xMin -= invAlfa1;
			xMax -= invAlfa2;
		}
	}
}
