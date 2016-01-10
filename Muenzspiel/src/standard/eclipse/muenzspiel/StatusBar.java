package standard.eclipse.muenzspiel;

import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Point;
import java.sql.Time;
import javax.swing.BoxLayout;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JFrame{

/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
Point p= new Point(1000,1000);
JPanel statusPanel;
JLabel computer;
JLabel test,test2,test3;
Computer brain;
JLabel clock;
Time t;



	
	public StatusBar(Computer brain){
		this.setSize(300,300);
		this.setLocation(p);
		this.setLayout(new FlowLayout());
		this.setResizable(true);	
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.setLayout(new FlowLayout());
		
		test = new JLabel("inline: yes/no");
		test2 = new JLabel("All coins visible?: yes/no");
		test3 = new JLabel("same Distance: yes/no");
		
		
		statusPanel = new JPanel();
	statusPanel.setAlignmentX(10);
	statusPanel.setAlignmentY(10);
	statusPanel.setSize(200, 200);
	statusPanel.setBackground(Color.green);
	statusPanel.setLayout(new BoxLayout(statusPanel, BoxLayout.PAGE_AXIS));
	statusPanel.setFocusable(true);
	statusPanel.add(test);
	statusPanel.add(test2);
	statusPanel.add(test3);
	
	
	this.add(statusPanel);
	this.brain=brain;
	
	t = new Time(System.currentTimeMillis());
	clock = new JLabel(t.toString());
	statusPanel.add(clock);

	
	}
	
	public void update(){
		
	t= new Time(System.currentTimeMillis());
	clock.setText("Uhrzeit: "+ t.toString());
		
	if(brain.isInline()==true){
		test.setText("inline: yes");
	}else
		test.setText("inline: no");
	
	if(brain.isOutofView()==true){
		test2.setText("All coins visible?: no");
	}else
		test2.setText("All coins visible?: yes");
	}
	

	
	
	
}
