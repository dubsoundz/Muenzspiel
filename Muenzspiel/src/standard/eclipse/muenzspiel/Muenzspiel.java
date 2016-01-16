package standard.eclipse.muenzspiel;
import java.awt.EventQueue;
import java.io.IOException;

import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JToolBar;
import javax.sound.sampled.UnsupportedAudioFileException;
import javax.swing.JButton;
import javax.swing.SwingConstants;
import javax.swing.JLabel;



public class Muenzspiel extends JFrame {
	

	private static final long serialVersionUID = 1L;
	JButton btnIch, btnComputer;
	JLabel textLbl;

	
	double x0=0,x1=0,x2=0,x3=0, y0=0, y1=0, y2=0, y3=0, velx = 0, vely = 0;
	
	double XVal[]={x0,x1,x2,x3};
	double YVal[]={y0,y1,y2,x3};
	
	Computer brain;
	private static JPanel muenzPanel_1;
	
	
	private JPanel startPanel;

	
	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Muenzspiel window = new Muenzspiel();
					window.setVisible(true);
				
					//start Thread des Computers
					
				
					
					
					
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}
	
	

	/**
	 * Create the application.
	 */
	public Muenzspiel() {
		
		
		try {
			PlaySoundClip sound= new PlaySoundClip();
			sound.startClip();
		} catch (UnsupportedAudioFileException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//brain = new Computer();
		
		ReadXMLFile getStrings = new ReadXMLFile();
	    getStrings.readXML();
		
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.getContentPane().setLayout(null);
		
		startPanel = new StartGame();
		startPanel.setLocation(0,0);
		startPanel.setSize(500, 500);
		startPanel.setAlignmentX(1000);
		startPanel.setAlignmentY(1000);
	    
		
		//muenzPanel.add(m1);

		//status.getContentPane().add(statusPanel);
		//toolBar.add(((Muenzen) muenzPanel_1).brain.Langle123);
		
		
	//Give the computer the task to guess the Form
		
		//brain.setValues(((Muenzen) muenzPanel_1).XVal,((Muenzen) muenzPanel_1).YVal);
		//brain.getDistance();
		
		
	//Fill Frame with muenzPanel	
		//this.getContentPane().add(muenzPanel_1);
		this.getContentPane().add(startPanel);
		this.setResizable(true);
		this.setBounds(100, 400, 772, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
	

	
}
