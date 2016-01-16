package standard.eclipse.muenzspiel;

import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.swing.SwingConstants;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.io.OutputStream;

import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.w3c.dom.Document;
import org.w3c.dom.Element;


public class StartGame extends JPanel implements ActionListener{
	

	BufferedImage background_img;
	public int type;
	Dimension d;
	JButton gameStart;
	JPanel muenzPanel_1;
	ActionListener bPressed;
	
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		
		super.setBackground(Color.black);
	}

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public StartGame() {
		// TODO Auto-generated constructor stub
		
		try{	
			//muenze = Toolkit.getDefaultToolkit().createImage("Muenze.jpg");
			background_img = ImageIO.read(new File("background_gamestart.jpg"));
			type = background_img.getType()==0? BufferedImage.TYPE_INT_ARGB:background_img.getType();
		}catch(IOException e){
			System.out.println(e.getMessage());
		}
		
		d = new Dimension(1000,1000);
		this.setSize(d);
		
		gameStart = new JButton("Start New Game!");
		this.add(gameStart);
		gameStart.addActionListener(this);

		
		
		
	}

	@Override
	public void updateUI() {
		// TODO Auto-generated method stub
		super.updateUI();
	}

	@Override
	public void paintComponent(Graphics g) {
		// TODO Auto-generated method stub
		
		super.paintComponent(g);
		
		Graphics2D startGUI = (Graphics2D)g;			
		BufferedImage startGUI2 = new BufferedImage(200,200,type);	
		
	    startGUI.drawImage(startGUI2, 0,0,200,200,null); 
	    g.drawImage(background_img, 0, 0, null);  
		startGUI.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		startGUI.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		
		

	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	if (e.getSource() == this.gameStart){
		System.out.println("Game started");
		startGame();
		
	}
	
		
		
	}
	
	
	public void startGame(){
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setAutoscrolls(true);
		toolBar.setBounds(480, 6, 286, 254);
		this.getRootPane().add(toolBar);
		
		//JPanel muenzPanel = new JPanel();	
		muenzPanel_1 = new Muenzen();
		muenzPanel_1.setLocation(0, 0);
		//muenzPanel.setBounds(10,6,341,254);
		muenzPanel_1.setSize(468,500);
		muenzPanel_1.setAlignmentX(1000);
		muenzPanel_1.setAlignmentY(1000);
		
		//Fill up ToolBar with elements from MuenzPanel	
		toolBar.add(((Muenzen) muenzPanel_1).btnIch);
		toolBar.add(((Muenzen) muenzPanel_1).btnComputer);
		toolBar.add(((Muenzen) muenzPanel_1).XYValLabel1);
		toolBar.add(((Muenzen) muenzPanel_1).XYValLabel2);
		toolBar.add(((Muenzen) muenzPanel_1).XYValLabel3);
		toolBar.add(((Muenzen) muenzPanel_1).XYValLabel4);
		toolBar.add(((Muenzen) muenzPanel_1).textLbl);
		toolBar.add(((Muenzen) muenzPanel_1).brain.status);
		toolBar.add(((Muenzen) muenzPanel_1).roundLbl);
		toolBar.add(((Muenzen) muenzPanel_1).confirm);
		
		
		
		this.getRootPane().add(muenzPanel_1);
	
	}
	
	
	
	

}
