package modelPainting;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;

import model.Camera;
import model.Illumination;
import model.Object;

public class GUI extends JPanel implements Canvas4ModelPainting {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	final int WIDTH = 740;
	final int HEIGHT = 580;
	private BufferedImage canvas;
	
	private Camera camera;
	private Object object;
	private Illumination illumination;
	TriangleFilling tf;
	
	
	public GUI(){
		this.canvas = new BufferedImage(WIDTH, HEIGHT, BufferedImage.TYPE_INT_ARGB);
		this.userInput();
	}
	
	public void userInput(){
		Dimension dimension = new Dimension(125, 25);
		
		JLabel camLabel = new JLabel("Camera");
		this.add(camLabel);
		
		final JTextField cameraTxt = new JTextField("inputs/camera.cfg");
		cameraTxt.setPreferredSize(dimension);
	    this.add(cameraTxt);
	    
	    JLabel objLabel = new JLabel("Objeto");
		this.add(objLabel);
		
	    final JTextField objectTxt = new JTextField("inputs/objeto.byu");
	    objectTxt.setPreferredSize(dimension);
	    this.add(objectTxt);
	    
	    JLabel illumLabel = new JLabel("Iluminação");
		this.add(illumLabel);
		
	    final JTextField illuminationTxt = new JTextField("inputs/iluminacao.txt");
	    illuminationTxt.setPreferredSize(dimension);
	    this.add(illuminationTxt);
		
		JButton button = new JButton("Renderizar");
		this.add(button);
		button.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				repaint();
				setup(cameraTxt.getText(), objectTxt.getText(), illuminationTxt.getText());
			}	
		});
	}
	
	public void setup(String camera, String object, String illumination){
		System.out.println("Loading camera");
		this.camera = new Camera(camera, WIDTH, HEIGHT);
		System.out.println("done");
		
		System.out.println("Loading object");
		this.object = new Object(object, this.camera);
		System.out.println("done");
		
		System.out.println("Loading illumination");
		this.illumination = new Illumination(illumination, this.camera);
		System.out.println("done");
		
		this.tf = new TriangleFilling(this, WIDTH, HEIGHT, this.object, this.illumination);
		this.clearCanvas();
		
		System.out.println("Rendering");
		this.drawTriangles();
		System.out.println("done");
	}
	
	public Dimension getPreferredSize() {
        return new Dimension(canvas.getWidth(), canvas.getHeight());
    }
	
	public void clearCanvas(){
		for(int i = 0; i < canvas.getWidth(); i++){
			for(int j = 0; j < canvas.getHeight(); j++){
				canvas.setRGB(i, j, new Color(237, 237, 237).getRGB());
			}
		}
		repaint();
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
