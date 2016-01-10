package standard.eclipse.muenzspiel;

import java.awt.Color;
import java.awt.FlowLayout;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class StatusBar extends JFrame {
	
JPanel statusPanel;
	
	
	public StatusBar(){
		

		this.setSize(500, 500);
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.getContentPane().setLayout(new FlowLayout());
		this.setLocation(0, 0);
		this.setResizable(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		statusPanel = new JPanel();
		this.getContentPane().add(statusPanel);
		
		
	}
	
	
	
	

}
