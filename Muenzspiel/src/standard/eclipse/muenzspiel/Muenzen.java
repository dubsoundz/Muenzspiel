package standard.eclipse.muenzspiel;
import java.awt.Color;

import java.awt.Graphics;
import java.awt.Graphics2D;

import java.awt.Rectangle;
import java.awt.RenderingHints;

import java.awt.event.*;
import java.awt.geom.Ellipse2D;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.*;

public class Muenzen extends JPanel implements ActionListener, KeyListener, MouseListener, MouseMotionListener{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public double x0=0,x1=0,x2=0,x3=0, y0=0, y1=0, y2=0, y3=0, velx = 0, vely = 0;
	int i;
	public double XVal[]={x0,x1,x2,x3};
	public double YVal[]={y0,y1,y2,x3};
	
	public int type;
	public int round;
	
	Timer t = new Timer(10, this);
	
	public JLabel XYValLabel1,XYValLabel2,XYValLabel3,XYValLabel4, textLbl,roundLbl;
	public JButton btnIch, btnComputer;
	
	JCheckBox confirm;
	
	BufferedImage muenze;
	public Ellipse2D circle1;
	public Ellipse2D circle2;
	public Ellipse2D circle3;
	public Ellipse2D circle4;
	
	int preX, preY;
	int PosX, PosY;
	Computer brain;
	
	boolean cRound = false;
	
	public double getXVal(){
		return this.XVal[0];
		
	}
	public double getYVal(){
		return this.YVal[0];
		
	}
	


//Konstruktor    
	public Muenzen(){
			
		initialize();
		
		XYValLabel1 = new JLabel("blablabla");
		XYValLabel1.setFocusable(true);
		XYValLabel1.setSize(300, 800);
		XYValLabel2 = new JLabel("blablabla");
		XYValLabel2.setFocusable(true);
		XYValLabel2.setSize(300, 800);
		XYValLabel3 = new JLabel("blablabla");
		XYValLabel3.setFocusable(true);
		XYValLabel3.setSize(300, 800);
		XYValLabel4 = new JLabel("blablabla");
		XYValLabel4.setFocusable(true);
		XYValLabel4.setSize(300, 800);
		confirm = new JCheckBox("Ready?");
		confirm.addActionListener(this);
		
		
		

		
		btnIch = new JButton("Ich");
		btnComputer = new JButton("Computer");
		
		btnIch.addActionListener(this);
		btnComputer.addActionListener(this);	
		
		textLbl = new JLabel("Text");
		roundLbl = new JLabel("Runde: " + round);
		
		t.start();
		addKeyListener(this);
		addMouseListener(this);
		addMouseMotionListener(this);
		setFocusable(true);
		setFocusTraversalKeysEnabled(false);	
	}
	
	public void initialize(){
		
		brain = new Computer(this);
		//Thread secondThread = new Thread ( brain, "second");
		//secondThread.start();
	try{	
		//muenze = Toolkit.getDefaultToolkit().createImage("Muenze.jpg");
		muenze = ImageIO.read(new File("Muenze.jpg"));
		type = muenze.getType()==0? BufferedImage.TYPE_INT_ARGB:muenze.getType();
	}catch(IOException e){
		System.out.println(e.getMessage());
	}

		XVal[0]=0;
		XVal[1]=20;
		XVal[2]=40;
		XVal[3]=60;
		
		circle1 = new Ellipse2D.Double(XVal[0],YVal[0],40,40);
		circle2 = new Ellipse2D.Double(XVal[1],YVal[1],40,40);
		circle3 = new Ellipse2D.Double(XVal[2],YVal[2],40,40);
		circle4 = new Ellipse2D.Double(XVal[3],YVal[3],40,40);

	}
	
	public void update() {
		circle1.setFrame(XVal[0], YVal[0], 40, 40);
		circle2.setFrame(XVal[1], YVal[1], 40, 40);
		circle3.setFrame(XVal[2], YVal[2], 40, 40);
		circle4.setFrame(XVal[3], YVal[3], 40, 40);
		brain.setValues(this);
		brain.update();
	}
	
	public void collisionDet(){
		Rectangle rec1 = circle1.getBounds();
		Rectangle rec2 = circle2.getBounds();
		Rectangle rec3 = circle3.getBounds();
		Rectangle rec4 = circle4.getBounds();
		
		if (rec1.intersects(rec2)==true){
			stop();
		}
		if (rec1.intersects(rec3)==true){
			stop();
		}
		if (rec1.intersects(rec4)==true){
			stop();
		}
		if (rec2.intersects(rec3)==true){
			stop();
		}
		if (rec2.intersects(rec4)==true){
			stop();
		}
		if (rec3.intersects(rec4)==true){
			stop();
		}
		
	}
	
	@Override
	public void setBackground(Color bg) {
		// TODO Auto-generated method stub
		super.setBackground(Color.white);
	}
	
	public void startNewRound(){
		JOptionPane.showConfirmDialog(this, "OK, nächste Konstellation");
		confirm.setSelected(false);
	}
	

	public void paintComponent(Graphics g){
		super.paintComponent(g);
		Graphics2D muenzen = (Graphics2D)g;	
		update();
		collisionDet();
		
		BufferedImage muenze2 = new BufferedImage(200,200,type);
		
	    muenzen.drawImage(muenze2, 0,0,200,200,null);
	    
	   
	    g.drawImage(muenze, 0, 0, null);
	    g.drawLine((int)circle1.getCenterX(), (int)circle1.getCenterY(), (int)circle2.getCenterX(),(int) circle2.getCenterY());
	    g.drawLine((int)circle1.getCenterX(), (int)circle1.getCenterY(), (int)circle3.getCenterX(),(int) circle3.getCenterY());
	    g.drawLine((int)circle1.getCenterX(), (int)circle1.getCenterY(), (int)circle4.getCenterX(),(int) circle4.getCenterY());
	    g.drawLine((int)circle2.getCenterX(), (int)circle2.getCenterY(), (int)circle3.getCenterX(),(int) circle3.getCenterY());
	    g.drawLine((int)circle2.getCenterX(), (int)circle2.getCenterY(), (int)circle4.getCenterX(),(int) circle4.getCenterY());
	    g.drawLine((int)circle3.getCenterX(), (int)circle3.getCenterY(), (int)circle4.getCenterX(),(int) circle4.getCenterY());
	    
		muenzen.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
		muenzen.setRenderingHint(RenderingHints.KEY_TEXT_ANTIALIASING, RenderingHints.VALUE_TEXT_ANTIALIAS_ON);
		
		
		muenzen.fill(circle1);
		muenzen.setPaint(Color.blue);

		muenzen.fill(circle2);
		muenzen.setPaint(Color.green);

		muenzen.fill(circle3);
		muenzen.setPaint(Color.magenta);

		muenzen.fill(circle4);
		muenzen.setPaint(Color.cyan);	
		
		
		
	}
	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	

	@Override
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		int code = e.getKeyCode();
		if (code == KeyEvent.VK_UP){
			up();
		}
		else if (code == KeyEvent.VK_DOWN){
			down();
		}
		else if (code == KeyEvent.VK_LEFT){
			left();
		}
		else if (code == KeyEvent.VK_RIGHT){
			right();
		}else if (code == KeyEvent.VK_SHIFT){
			select();
		}else{
			stop();
		}
		
		
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
		if(e.getSource() == this.btnComputer){
			textLbl.setText("Computer ist dran!");
			setFocusable(true);
			requestFocus();
			cRound = true;
			
		}
		else if(e.getSource() == this.btnIch){
			textLbl.setText("Ich bin dran!");
			setFocusable(true);
			requestFocus();
			cRound = false;
			stop();
		}
		else if(e.getSource()==this.confirm){
			
			if(confirm.isSelected()){
				System.out.println("Du hast soeben diesen Zug bestätigt");
				brain.saveState();
				startNewRound();
			}
			
		}
		else{
			
			repaint();
			XVal[i] += velx;
			YVal[i] += vely;
				
			XYValLabel1.setText("X1: "+ Double.toString(XVal[0]) +"\n"+ "Y1: " + Double.toString(YVal[0]));
			XYValLabel2.setText("X2: "+ Double.toString(XVal[1]) +"\n"+ "Y2: " + Double.toString(YVal[1]));
			XYValLabel3.setText("X3: "+ Double.toString(XVal[2]) +"\n"+ "Y3: " + Double.toString(YVal[2]));
			XYValLabel4.setText("X4: "+ Double.toString(XVal[3]) +"\n"+ "Y4: " + Double.toString(YVal[3]));
		
		}		
	}
	public int select(){
		i++;
		if(i==4){
			i=0;
		}
		
		
		return i;
		
	}
	public void stop(){
		vely=0;
		velx=0;
	}
	public void up(){
		vely = -1.5;
		velx = 0;
	}
	public void down(){
		velx = 0;
		vely = 1.5;
	}
	public void left(){
		velx = -1.5;
		vely = 0;
	}
	public void right(){
		velx = 1.5;
		vely = 0;
	}
	@Override
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
		
		
		int dx = e.getX() - preX;
		int dy = e.getY() - preY;
		
	//	System.out.println(Integer.toString(dx));
	//	System.out.println(Integer.toString(dx));
		
		if (circle1.getBounds2D().contains(preX, preY)){
			
			XVal[0]+=dx;
			YVal[0]+=dy;
			
			repaint();
			
		}else if(circle2.getBounds2D().contains(preX, preY)){
			XVal[1]+=dx;
			YVal[1]+=dy;
			repaint();
		}else if(circle3.getBounds2D().contains(preX,preY)){
			XVal[2]+=dx;
			YVal[2]+=dy;
			repaint();
		}else if(circle4.getBounds2D().contains(preX,preY)){
			XVal[3]+=dx;
			YVal[3]+=dy;
			repaint();
		}
		preX +=dx;
		preY += dy;
		
		
		
	}
	@Override
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		  preX = e.getX();
		    preY = e.getY();

		//  System.out.println(Integer.toString(preX));
	//	  System.out.println(Integer.toString(preY));
		
		
	}
	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
		round+=1;
		roundLbl.setText(Integer.toString(round));
		
		if ( round == 3){
			round = 0;
		}
		
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
}
