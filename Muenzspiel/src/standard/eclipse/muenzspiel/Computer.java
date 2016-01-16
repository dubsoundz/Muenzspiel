package standard.eclipse.muenzspiel;
import java.awt.Rectangle;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.geom.Point2D;
import java.sql.Time;
import java.util.Random;
import java.util.Timer;
import java.util.TimerTask;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.*;





public class Computer extends JPanel implements Runnable, ActionListener {
	
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel status;
	public double XVal[];
	public double YVal[];
	private double angle123, angle234, angle341, angle412, angle142, angle143, angle312, angle231,angle413, angle431, angle241, angle243,
	angle432, angle421,angle423;
	private double dist12,dist13,dist41, dist23,dist34, dist42, midDist;
	private Muenzen m;
	private Point2D p1,p2,p3,p4;
	public JPanel state;
	public StatusBar statusBar;
	TimerTask schedule;
	
	protected JLabel Langle123,Langle234;
	public Timer t = new Timer();
	
	double dy, dx;
	double f = 10;
	double dt =0;
	int x, y;
	int index = 0;

	
	
	public void setValues(Muenzen m){
		
		this.m=m;
		p1 = new Point2D.Double(m.XVal[0],m.YVal[0]);
		p2 = new Point2D.Double(m.XVal[1],m.YVal[1]);
		p3 = new Point2D.Double(m.XVal[2],m.YVal[2]);
		p4 = new Point2D.Double(m.XVal[3],m.YVal[3]);
		
			
	}

	public void move(){
	
		
		schedule = new TimerTask(){
			
			int A = 1;
			
			@Override
			public void run() {
				// TODO Auto-generated method stub
				A+=0.001;
				dt+=0.1;		
				dy = A*Math.sin(dt);		
				dx = A*Math.cos(dt);		
			//	System.out.println(Double.toString(dt)+Double.toString(dy));

				m.XVal[m.i]+=dx;
				m.YVal[m.i]+=dy;
				
				//System.out.println("Index: "+ Integer.toString(m.i));
				
				if (!m.cRound){
					this.cancel();
					//t.cancel();
				}
			}

			@Override
			public boolean cancel() {
				// TODO Auto-generated method stub
				
				A = 1;
				dt = 0.1;
				return super.cancel();
			
				
				
				
				
			}
		};
		
		
		if(m.cRound==true){ // Wenn der Computer an der Reihe ist
				
			t.scheduleAtFixedRate(schedule, 0, 1000); //start moving and do what'S written in schedule
			
				if(!m.cRound){
				//schedule.cancel();	
				t.purge();
				t.cancel();
			}
			
					
		} 
		     
	}
	
	public void saveState(){
		
	}
	
	public void getDistance(){
		
		dist12 = p1.distance(p2);
		dist13 = p1.distance(p3);
		dist41 = p1.distance(p4);
		dist23 = p2.distance(p3);
		dist34 = p3.distance(p4);
		dist42 = p4.distance(p2);
		/*
		System.out.println(Double.toString(dist12));
		System.out.println(Double.toString(dist13));
		System.out.println(Double.toString(dist41));
		System.out.println(Double.toString(dist23));
		System.out.println(Double.toString(dist34));
		System.out.println(Double.toString(dist42));
		*/
		midDist = (dist12+dist13+dist41+dist23+dist34+dist42)/6;
		//System.out.println("mittlerer Abstand: " + Double.toString(midDist));
	}
	
	int countRAngle(){
		int i= 0;
		
		if ( angle123 <= 95 && angle123 >= 85){
			i+=1;
		}
		if ( angle312 <= 95 && angle312 >= 85){
			i+=1;
		}
		if ( angle231 <= 95 && angle231 >= 85){
			i+=1;
		}
		if ( angle413 <= 95 && angle413 >= 85){
			i+=1;
		}
		
		if ( angle431 <= 95 && angle431 >= 85){
			i+=1;
		}
		
		if ( angle341 <= 95 && angle341 >= 85){
			i+=1;
		}
		if ( angle241 <= 95 && angle241 >= 85){
			i+=1;
		}
		if ( angle243 <= 95 && angle243 >= 85){
			i+=1;
		}
		if ( angle421 <= 95 && angle421 >= 85){
			i+=1;
		}
		if ( angle423 <= 95 && angle423 >= 85){
			i+=1;
		}
		if ( angle432 <= 95 && angle432 >= 85){
			i+=1;
		}
		if ( angle412 <= 95 && angle412 >= 85){
			i+=1;
		}
				
		//System.out.println(Integer.toString(i));
		return i;
	}
	
	
	public void getAngle(){
		
 //for angle 123 //Winkel zwischen P1P2 und P1P3 eingeschlossen
	Point2D vek1 = new Point2D.Double(p2.getX()-p1.getX(), p2.getY()-p1.getY());
	Point2D vek2 = new Point2D.Double(p3.getX()-p1.getX(), p3.getY()-p1.getY());	
	double norm1 = Math.sqrt(vek1.getX()*vek1.getX() + vek1.getY()*vek1.getY());
	double norm2 = Math.sqrt(vek2.getX()*vek2.getX() + vek2.getY()*vek2.getY());
	double skpr =  vek1.getX()*vek2.getX() + vek1.getY()*vek2.getY();
	angle312 = Math.acos(skpr/(norm1*norm2));
	
	//for angle between P1P2 und P3P2  
	Point2D vek3 = new Point2D.Double(p1.getX()-p2.getX(), p1.getY()-p2.getY());
	Point2D vek4 = new Point2D.Double(p3.getX()-p2.getX(), p3.getY()-p2.getY());
	double norm3 = Math.sqrt(vek3.getX()*vek3.getX() + vek3.getY()*vek3.getY());
	double norm4 = Math.sqrt(vek4.getX()*vek4.getX() + vek4.getY()*vek4.getY());	
	double skpr2 =  vek3.getX()*vek4.getX() + vek3.getY()*vek4.getY();
	angle123 = Math.acos(skpr2/(norm3*norm4));
	
	//-----------------------------	
	//for angle between P1P3 und P2P3  
	Point2D vek5 = new Point2D.Double(p1.getX()-p3.getX(), p1.getY()-p3.getY());
	Point2D vek6 = new Point2D.Double(p2.getX()-p3.getX(), p2.getY()-p3.getY());
	double norm5 = Math.sqrt(vek5.getX()*vek5.getX() + vek5.getY()*vek5.getY());
	double norm6 = Math.sqrt(vek6.getX()*vek6.getX() + vek6.getY()*vek6.getY());	
	double skpr3 =  vek5.getX()*vek6.getX() + vek5.getY()*vek6.getY();
	angle231 = Math.acos(skpr3/(norm5*norm6));

	//for angle between P4P1 und P4P3  
	Point2D vek7 = new Point2D.Double(p4.getX()-p1.getX(), p4.getY()-p1.getY());
	Point2D vek8 = new Point2D.Double(p3.getX()-p1.getX(), p3.getY()-p1.getY()); 
	double norm7 = Math.sqrt(vek7.getX()*vek7.getX() + vek7.getY()*vek7.getY());
	double norm8 = Math.sqrt(vek8.getX()*vek8.getX() + vek8.getY()*vek8.getY());	
	double skpr4 =  vek7.getX()*vek8.getX() + vek7.getY()*vek8.getY();
	angle413 = Math.acos(skpr4/(norm7*norm8));
	
	//for angle between P4P3 und P3P1  
	Point2D vek9 = new Point2D.Double(p4.getX()-p3.getX(), p4.getY()-p3.getY());
	Point2D vek10 = new Point2D.Double(p1.getX()-p3.getX(), p1.getY()-p3.getY()); //===vek5 
	double norm9 = Math.sqrt(vek9.getX()*vek9.getX() + vek9.getY()*vek9.getY());
	double norm10 = Math.sqrt(vek10.getX()*vek10.getX() + vek10.getY()*vek10.getY());	
	double skpr5 =  vek9.getX()*vek10.getX() + vek9.getY()*vek10.getY();
	angle431 = Math.acos(skpr5/(norm9*norm10));

	//for angle between P4P3 und P3P1  
	Point2D vek11 = new Point2D.Double(p1.getX()-p4.getX(), p1.getY()-p4.getY());
	Point2D vek12 = new Point2D.Double(p3.getX()-p4.getX(), p3.getY()-p4.getY()); 
	double norm11 = Math.sqrt(vek11.getX()*vek11.getX() + vek11.getY()*vek11.getY());
	double norm12 = Math.sqrt(vek12.getX()*vek12.getX() + vek12.getY()*vek12.getY());	
	double skpr6 =  vek11.getX()*vek12.getX() + vek11.getY()*vek12.getY();
	angle341 = Math.acos(skpr6/(norm11*norm12));
		
	//for angle between P1P4 und P2P4  
	Point2D vek13 = new Point2D.Double(p1.getX()-p4.getX(), p1.getY()-p4.getY());
	Point2D vek14 = new Point2D.Double(p2.getX()-p4.getX(), p2.getY()-p4.getY());
	double norm13 = Math.sqrt(vek13.getX()*vek13.getX() + vek13.getY()*vek13.getY());
	double norm14 = Math.sqrt(vek14.getX()*vek14.getX() + vek14.getY()*vek14.getY());	
	double skpr7 =  vek13.getX()*vek14.getX() + vek13.getY()*vek14.getY();
	angle241 = Math.acos(skpr7/(norm13*norm14));
	
	//for angle between P2P4 und P3P4  
	Point2D vek15 = new Point2D.Double(p1.getX()-p4.getX(), p1.getY()-p4.getY());
	Point2D vek16 = new Point2D.Double(p2.getX()-p4.getX(), p2.getY()-p4.getY());
	double norm15 = Math.sqrt(vek15.getX()*vek15.getX() + vek15.getY()*vek15.getY());
	double norm16 = Math.sqrt(vek16.getX()*vek16.getX() + vek16.getY()*vek16.getY());	
	double skpr8 =  vek15.getX()*vek16.getX() + vek15.getY()*vek16.getY();
	angle243 = Math.acos(skpr8/(norm15*norm16));
	
	//for angle between P4P2 und P1P2  
	Point2D vek17 = new Point2D.Double(p1.getX()-p4.getX(), p1.getY()-p4.getY());
	Point2D vek18 = new Point2D.Double(p2.getX()-p4.getX(), p2.getY()-p4.getY());
	double norm17 = Math.sqrt(vek17.getX()*vek17.getX() + vek17.getY()*vek17.getY());
	double norm18 = Math.sqrt(vek18.getX()*vek18.getX() + vek18.getY()*vek18.getY());	
	double skpr9 =  vek17.getX()*vek18.getX() + vek17.getY()*vek18.getY();
	angle421 = Math.acos(skpr9/(norm17*norm18));
	
	//for angle between P2P4 und P3P4  
	Point2D vek19 = new Point2D.Double(p2.getX()-p4.getX(), p2.getY()-p4.getY());
	Point2D vek20 = new Point2D.Double(p3.getX()-p4.getX(), p3.getY()-p4.getY());
	double norm19 = Math.sqrt(vek19.getX()*vek19.getX() + vek19.getY()*vek19.getY());
	double norm20 = Math.sqrt(vek20.getX()*vek20.getX() + vek20.getY()*vek20.getY());	
	double skpr10 =  vek19.getX()*vek20.getX() + vek19.getY()*vek20.getY();
	angle423 = Math.acos(skpr10/(norm19*norm20));
	
	//for angle between P4P3 und P2P3  
	Point2D vek21 = new Point2D.Double(p4.getX()-p3.getX(), p4.getY()-p3.getY());
	Point2D vek22 = new Point2D.Double(p2.getX()-p3.getX(), p2.getY()-p3.getY());
	double norm21 = Math.sqrt(vek21.getX()*vek21.getX() + vek21.getY()*vek21.getY());
	double norm22 = Math.sqrt(vek22.getX()*vek22.getX() + vek22.getY()*vek22.getY());	
	double skpr11 =  vek21.getX()*vek22.getX() + vek21.getY()*vek22.getY();
	angle432 = Math.acos(skpr11/(norm21*norm22));
	
	//for angle between P2P4 und P3P4  
	Point2D vek23 = new Point2D.Double(p4.getX()-p1.getX(), p4.getY()-p1.getY());
	Point2D vek24 = new Point2D.Double(p2.getX()-p1.getX(), p2.getY()-p1.getY());
	double norm23 = Math.sqrt(vek23.getX()*vek23.getX() + vek23.getY()*vek23.getY());
	double norm24 = Math.sqrt(vek24.getX()*vek24.getX() + vek24.getY()*vek24.getY());	
	double skpr12 =  vek23.getX()*vek24.getX() + vek23.getY()*vek24.getY();
	angle412 = Math.acos(skpr12/(norm23*norm24));
	
	
	
	
	angle123=  180*angle123/Math.PI;	
	angle312 = 180*angle312/Math.PI;
	angle231 = 180*angle231/Math.PI;	
	angle413 = 180*angle413/Math.PI;
	angle431 = 180*angle341/Math.PI;	
	angle341 = 180*angle341/Math.PI;	
	angle241=  180*angle241/Math.PI;
	angle243=  180*angle243/Math.PI;
	angle421=  180*angle421/Math.PI;
	angle423=  180*angle423/Math.PI;
	angle432=  180*angle432/Math.PI;
	angle412=  180*angle412/Math.PI;
	

	
		/*Langle123.setText("Winkel123: "+ angle123 + "\n"+
				"Winkel234:"+180*angle234/Math.PI);
		*/
	/*	System.out.println(
				"\nWinkel123: "+ angle123+ "\n"+
				"Winkel312:"+angle312+ "\n"+
				"Winkel231:"+ angle231+"\n"+
				"Winkel413:"+ angle413+"\n"+
				"Winkel431:"+ angle431+"\n"+
				"Winkel341:"+ angle341+"\n"+
				"Winkel241:"+ angle241+"\n"+
				"Winkel243:"+ angle241+"\n"+
				"Winkel421:"+ angle421+"\n"+
				"Winkel423:"+ angle423+"\n"+
				"Winkel432:"+ angle432+"\n"+
				"Winkel412:"+ angle412);
		*/
		
		
	}
	public boolean isStart(){
		
		if (m.circle1.getY()==0 && m.circle2.getY()==0 && m.circle3.getY()==0 && m.circle4.getY()==0){
			return true;
		}else
			return false;
	
	}
	
	public boolean isTriangle(){
		
		
		
		
		
		return false;
	}
	
	public boolean isInline(){
		
		double anglesum;
		anglesum = (angle123+ angle234+angle341+ angle412+ angle142+ angle143);
		//System.out.println("Summe aller Winkel: "+ Double.toString(anglesum));
		
	if ( angle123-10 <= 0 && angle123+10 >= 0 || angle123-10 <= 180 && angle123+10 >= 180 ){
		if (angle341-10 <= 0 && angle341+10 >= 0 || angle341-10 <= 180 && angle341+10 >= 180 ){
			return true;
		}else
			return false;
	}else
		
		return false;
		
	}
	
	/*public boolean equalDistances(){
		
		boolean rel1223, rel2334, rel1234, rel1242, rel2342;
		
		
		if(dist12<=dist23+10&&dist12>=dist23-10){
			rel1223 = true;
		}else 
			rel1223 = false;
		if(dist23<=dist34+10&&dist23>=dist34-10){
			rel2334 = true;
		}else 
			rel2334 = false;
		if(dist12<=dist34+10&&dist12>=dist34-10){
			rel1234 = true;
		}else 
			rel1234 = false;
		if(dist12<=dist42+10&&dist12>=dist42-10){
			rel1242 = true;
		}else 
			rel1242 = false;
		if(dist23<=dist42+10&&dist23>=dist42-10){
			rel2342 = true;
		}else 
			rel2342 = false;
		
		if (rel1223 && rel2334 && rel1234 && rel1242 && rel2342== true){
			
			return true;
		}else
		
		return false;
	}*/
	
	public boolean isOverlapping(){
		
		boolean t12,t23,t34,t14;
		
		
		if( m.circle1.intersects(m.circle2.getBounds2D())){
			t12=true;
		}else 
			t12=false;	
			
		if(m.circle2.intersects(m.circle3.getBounds2D())){
			t23=true;
		}else 
			t23=false;
		
		if(m.circle3.intersects(m.circle4.getBounds2D())){
			t34=true;
		}else 
			t34=false;
		
		if(m.circle1.intersects(m.circle4.getBounds2D())){
			t14=true;
		}else 
			t14=false;
		
		if(t12 || t23 || t34 || t14 == true){
			return true;
		}else 
			return false;
		
		
		
	}
	
	public boolean isOutofView(){
		Rectangle bounds = m.getBounds();
		if((m.circle1.getX()-bounds.getMaxX() )> 0|| m.circle1.getX()- bounds.getMinX()<0){
			return true;
		}
		if((m.circle2.getX()-bounds.getMaxX() )> 0|| m.circle2.getX()- bounds.getMinX()<0){
			return true;
		}
		if((m.circle3.getX()-bounds.getMaxX() )> 0|| m.circle3.getX()- bounds.getMinX()<0){
			return true;
		}
		if((m.circle4.getX()-bounds.getMaxX() )> 0|| m.circle4.getX()- bounds.getMinX()<0){
			return true;
		}
		//the same for y-border
		if((m.circle1.getY()-bounds.getMaxY() )>0|| m.circle1.getY()- bounds.getMinY()<0){
			return true;
		}
		if((m.circle2.getY()-bounds.getMaxY() )>0|| m.circle2.getY()- bounds.getMinY()<0){
			return true;
		}
		if((m.circle3.getY()-bounds.getMaxY() )>0|| m.circle3.getY()- bounds.getMinY()<0){
			return true;
		}
		if((m.circle4.getY()-bounds.getMaxY() )>0|| m.circle4.getY()- bounds.getMinY()<0){
			return true;
		}else
		
		
		return false;
	}
	
	public boolean isSquared(){
		return false;
		
		
	}
	public boolean isRandom(){
		
		
		
		return true;
	}
	public void guessForm() {
		
	}
	
	public void moveRandom(){
		Thread movingThread = new Thread(this);
		Random randomGenX= new Random();
		int randomInt;
		randomInt= randomGenX.nextInt((int) m.getBounds().getMaxX());
		//System.out.println(Integer.toString(randomInt));
		
		
		if(m.cRound){
			movingThread.start();
		}
		else if(!m.cRound){
			movingThread.interrupt();
		}
		
	
		
		
		
		
	}
	
	
	public Computer(Muenzen m){
		
		
		this.m = m;
		status = new JLabel("Das ist:...!");
		Langle123 = new JLabel("Winkel: ");
		Langle123.setSize(300,800);
		status.setSize(300,800);
		state = new JPanel();
		state.setBounds(10, 10, 200, 200);
		
		Langle123.setFocusable(true);
		Langle123.setAutoscrolls(true);
		
		statusBar = new StatusBar(this);
		statusBar.setVisible(true);
		
	

	
		
		
	}
	public void update(){
		
	
		getDistance();
		getAngle();
		statusBar.update();
		moveRandom();
		countRAngle();
	

	/*
		if (isRandom()==true){
			//status.setText("Völlig Ohne Sinn");
			System.out.println("Völlig Ohne Sinn");
		}
		if (isStart()==true){
			//status.setText("Noch nichts passiert");
			System.out.println("Noch nichts passiert");
		}
		if (isOverlapping()==true){
			//status.setText("Münzen liegen aufeinander");
			System.out.println("Münzen liegen aufeinander");
		}
		if (equalDistances()==true){
			//status.setText("Die Abstände sind ungefähr gleich");
			System.out.println("Die Abstände sind ungefähr gleich");
		}
		if (isInline()==true){
			//status.setText("Die Münzen liegen in Reihe");
			System.out.println("Die Münzen liegen in Reihe");
		}
		if (isOutofView()==true){
			
			//status.setText("Die Münzen liegen in Reihe");
			System.out.println("Es sind nicht alle Münzen zu sehen");
		}
		
	*/
	}
	@Override
	public void run() {

		//System.out.println("Thread started");
		move();
	
	}
	

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		
	}

}


	
