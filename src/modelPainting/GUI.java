package modelPainting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;

import javax.swing.JFrame;
import javax.swing.JPanel;

import model.Camera;
import model.Illumination;
import model.Object;
import model.Point;
import model.Triangle;

public class GUI extends JPanel implements Canvas4ModelPainting {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int WIDTH = 640;
	final int HEIGHT = 480;
	private BufferedImage canvas;
	
	private Camera camera;
	private Object object;
	private Illumination illumination;
	TriangleFilling tf;
	
	
	public GUI(){
		this.canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		
		this.camera = new Camera("inputs/camera.cfg", WIDTH, HEIGHT);
		this.object = new Object("inputs/objeto.byu", camera);
		this.illumination = new Illumination("inputs/iluminacao.txt", camera);
		this.tf = new TriangleFilling(this, WIDTH, HEIGHT, object, illumination);
		this.drawTriangles();
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
	
	public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.drawImage(canvas, null, null);
    }
	
	public void drawTriangles(){
		tf.drawMany(object.screenCoordTriangles);
		repaint();
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
