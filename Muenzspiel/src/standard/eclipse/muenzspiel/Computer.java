package standard.eclipse.muenzspiel;
import java.awt.geom.Point2D;

import javax.swing.JLabel;

public class Computer implements Runnable {
	
	public JLabel status;
	public double XVal[];
	public double YVal[];
	private double angle123, angle234, angle341, angle412, angle142, angle143;
	private double Xdelta12,Xdelta13, Xdelta14, Xdelta21, Xdelta24,Xdelta23,Xdelta31,Xdelta32,Xdelta34,Xdelta41,Xdelta42,Xdelta43;
	private double Ydelta12,Ydelta13, Ydelta14, Ydelta21, Ydelta24,Ydelta23,Ydelta31,Ydelta32,Ydelta34,Ydelta41,Ydelta42,Ydelta43;
	private double dist12,dist13,dist41, dist23,dist34, dist42;
	private Muenzen m;
	private Point2D p1,p2,p3,p4;
	
	protected JLabel Langle123,Langle234;
	
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
		
		
		
		Langle123.setText("Winkel123: "+ 180*angle123/Math.PI + "\n"+
				"Winkel234:"+180*angle234/Math.PI);
		
		System.out.println("Winkel123: "+ 180*angle123/Math.PI + "\n"+
				"Winkel234:"+180*angle234/Math.PI +  "\n"+
				"Winkel341:"+180*angle341/Math.PI+ "\n"+
				"Winkel412:"+180*angle412/Math.PI+"\n"+
				"Winkel142:"+180*angle142/Math.PI+"\n"+
				"Winkel143:"+180*angle143/Math.PI);
		
		
		
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
		anglesum = ((angle123+ angle234+angle341+ angle412+ angle142+ angle143)*180)/Math.PI;
		System.out.println("Summe aller Winkel: "+ Double.toString(anglesum));
		
	if (anglesum >=712 ){
		return true;
	}else if((180*angle341)/Math.PI>=170 && (180*angle142)/Math.PI>=170 && (180*angle143)/Math.PI>=170){
		return true;
	}else 
		
		
		return false;
		
	}
	public boolean equalDistances(){
		
		boolean rel1223, rel2334;
		
		
		if(dist12<=dist23+10&&dist12>=dist23-10){
			rel1223 = true;
		}else 
			rel1223 = false;
		if(dist23<=dist34+10&&dist23>=dist34-10){
			rel2334 = true;
		}else 
			rel2334 = false;
		
		
		if (rel1223 && rel2334 == true){
			return true;
		}else
		
		return false;
	}
	
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
	
	public boolean isSquared(){
		return false;
		
		
	}
	public boolean isRandom(){
		
		
		
		return true;
	}
	public void guessForm() {
		
	}
	
	
	
	public Computer(Muenzen m){
		
		this.m = m;
		status = new JLabel("Das ist:...!");
		Langle123 = new JLabel("Winkel: ");
		Langle123.setSize(300,800);
		status.setSize(300,800);
		Langle123.setFocusable(true);
		Langle123.setAutoscrolls(true);
		
		
	}
	public void update(){
		
		getDistance();
		getAngle();
		
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
		
		
		
		
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
	
		System.out.println("Thread läuft");
		update();
				
	}
	
	
	
	
	

}
