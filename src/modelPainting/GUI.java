package modelPainting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Camera;
import model.Object;
import model.Point;
import model.Triangle;

public class GUI extends JPanel implements Canvas4ModelPainting {
	final int WIDTH = 900;
	final int HEIGHT = 600;
	private BufferedImage canvas;
	
	private Camera camera;
	private Object object;
	TriangleFilling tf;
	
	public GUI(){
		this.canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		this.camera = new Camera("inputs/camera.cfg", WIDTH, HEIGHT);
		this.object = new Object("inputs/objeto.byu", camera);
		this.tf = new TriangleFilling(this, WIDTH, HEIGHT);
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
	
	public void drawTriangle(){
		Triangle t = object.screenCoordTriangles.get(1);
		try {
			tf.drawOne(t);
			repaint();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	@Override
	public void drawPixel(int x, int y, Color c) {
		canvas.setRGB(x, y, c.getRGB());
	}
	
	public static void main(String[] args) {
		JFrame frame = new JFrame();
		GUI panel = new GUI();
		frame.add(panel);
		frame.pack();
		frame.setVisible(true);
		frame.setResizable(false);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
