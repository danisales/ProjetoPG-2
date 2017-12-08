package modelPainting;

import model.Point;
import model.Triangle;

public class TriangleFilling {
	
	Canvas4ModelPainting canvas;
	public static void main(String[] args) {
		
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
		
		
	}
	private void bottomFlat(Triangle t){
		
		Point p1 = t.p1;
		Point p2 = t.p2;
		Point p3 = t.p3;
		
	}
	private void topFlat(Triangle t){
		
	}


}
