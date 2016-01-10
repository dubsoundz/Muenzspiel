package standard.eclipse.muenzspiel;
import java.awt.Rectangle;
import java.awt.geom.Point2D;
import java.util.Random;
import java.util.Timer;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Computer extends JPanel implements Runnable {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	public JLabel status;
	public double XVal[];
	public double YVal[];
	private double angle123, angle234, angle341, angle412, angle142, angle143;
	private double dist12,dist13,dist41, dist23,dist34, dist42, midDist;
	private Muenzen m;
	private Point2D p1,p2,p3,p4;
	public JPanel state;
	public StatusBar statusBar;
	
	protected JLabel Langle123,Langle234;
	Timer t;
	
	int x, y;
	
	
	public void setValues(Muenzen m){
		
		this.m=m;
		p1 = new Point2D.Double(m.XVal[0],m.YVal[0]);
		p2 = new Point2D.Double(m.XVal[1],m.YVal[1]);
		p3 = new Point2D.Double(m.XVal[2],m.YVal[2]);
		p4 = new Point2D.Double(m.XVal[3],m.YVal[3]);
		
			
	}
	
	public void getDistance(){
		
		dist12 = p1.distance(p2);
		dist13 = p1.distance(p3);
		dist41 = p1.distance(p4);
		dist23 = p2.distance(p3);
		dist34 = p3.distance(p4);
		dist42 = p4.distance(p2);
		System.out.println(Double.toString(dist12));
		System.out.println(Double.toString(dist13));
		System.out.println(Double.toString(dist41));
		System.out.println(Double.toString(dist23));
		System.out.println(Double.toString(dist34));
		System.out.println(Double.toString(dist42));
		midDist = (dist12+dist13+dist41+dist23+dist34+dist42)/6;
		System.out.println("mittlerer Abstand: " + Double.toString(midDist));
	}
	
	public void getAngle(){
		
	Point2D vek1 = new Point2D.Double(p2.getX()-p1.getX(), p2.getY()-p1.getY());
	Point2D vek2 = new Point2D.Double(p3.getX()-p1.getX(), p3.getY()-p1.getY());

	Point2D vek4 = new Point2D.Double(p2.getX()-p4.getX(), p2.getY()-p4.getY());
	Point2D vek5 = new Point2D.Double(p2.getX()-p3.getX(), p2.getY()-p3.getY());
	
	Point2D vek3 = new Point2D.Double(p3.getX()-p1.getX(), p3.getY()-p1.getY());
	Point2D vek6 = new Point2D.Double(p3.getX()-p4.getX(), p3.getY()-p4.getY());
	
	Point2D vek7 = new Point2D.Double(p1.getX()-p4.getX(), p1.getY()-p4.getY());
	
	
	double skpr = vek1.getX()*vek2.getX() + vek1.getY()*vek2.getY();
	double skpr2 = vek5.getX()*vek4.getX() + vek5.getY()*vek4.getY();
	double skpr3 = vek3.getX()*vek6.getX() + vek3.getY()*vek6.getY();
	double skpr4 = vek4.getX()*vek7.getX() + vek4.getY()*vek7.getY();
	double skpr5 = vek7.getX()*vek1.getX() + vek7.getY()*vek1.getY();
	double skpr6 = vek7.getX()*vek2.getX() + vek7.getY()*vek2.getY();
	
	angle123 = Math.acos(skpr/(Math.abs(dist12)*Math.abs(dist13)));
	angle234 = Math.acos(skpr2/(Math.abs(dist23)*Math.abs(dist42)));
	angle341 = Math.acos(skpr3/(Math.abs(dist34)*Math.abs(dist13)));
	angle412 = Math.acos(skpr4/(Math.abs(dist41)*Math.abs(dist42)));
	angle142 = Math.acos(skpr5/(Math.abs(dist41)*Math.abs(dist12)));
	angle143 = Math.acos(skpr6/(Math.abs(dist41)*Math.abs(dist13)));
	
			//angle123 = Math.asin(Math.sin(dist13/dist12));
			System.out.println(Double.toString(angle123));	
		
	angle123= 180*angle123/Math.PI;	
	angle234 = 180*angle234/Math.PI;
	angle341 = 180*angle341/Math.PI;
	angle412 = 180*angle412/Math.PI;
	angle142 = 180*angle142/Math.PI;
	angle143 = 180*angle143/Math.PI;
	
		Langle123.setText("Winkel123: "+ angle123 + "\n"+
				"Winkel234:"+180*angle234/Math.PI);
		
		System.out.println("Winkel123: "+ angle123+ "\n"+
				"Winkel234:"+angle234 +  "\n"+
				"Winkel341:"+angle341+ "\n"+
				"Winkel412:"+angle412+"\n"+
				"Winkel142:"+angle142+"\n"+
				"Winkel143:"+angle143);
		
		
		
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
		System.out.println("Summe aller Winkel: "+ Double.toString(anglesum));
		
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
		Random randomGenX= new Random();
		int randomInt;
		randomInt= randomGenX.nextInt((int) m.getBounds().getMaxX());
		System.out.println(Integer.toString(randomInt));
		
		
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
		/*if (equalDistances()==true){
			//status.setText("Die Abstände sind ungefähr gleich");
			System.out.println("Die Abstände sind ungefähr gleich");
		}*/
		if (isInline()==true){
			//status.setText("Die Münzen liegen in Reihe");
			System.out.println("Die Münzen liegen in Reihe");
		}
		if (isOutofView()==true){
			
			//status.setText("Die Münzen liegen in Reihe");
			System.out.println("Es sind nicht alle Münzen zu sehen");
		}
		
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		System.out.println("Thread läuft");
		update();
				
	}
	
	
	
	
	

}
