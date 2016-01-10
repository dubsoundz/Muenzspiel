package standard.eclipse.muenzspiel;
import java.awt.EventQueue;
import javax.swing.JFrame;
import java.awt.Color;
import javax.swing.JPanel;
import javax.swing.JToolBar;
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
		
		//brain = new Computer();
		
		this.getContentPane().setBackground(Color.LIGHT_GRAY);
		this.getContentPane().setLayout(null);
		
		
		JToolBar toolBar = new JToolBar();
		toolBar.setOrientation(SwingConstants.VERTICAL);
		toolBar.setAutoscrolls(true);
		toolBar.setBounds(480, 6, 286, 254);
		this.getContentPane().add(toolBar);
	
		//JPanel muenzPanel = new JPanel();	
		muenzPanel_1 = new Muenzen();
		
	
		muenzPanel_1.setLocation(0, 0);
		//muenzPanel.setBounds(10,6,341,254);
		muenzPanel_1.setSize(468,500);
		muenzPanel_1.setAlignmentX(1000);
		muenzPanel_1.setAlignmentY(1000);
	
		//muenzPanel.add(m1);
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
		
		//status.getContentPane().add(statusPanel);
		//toolBar.add(((Muenzen) muenzPanel_1).brain.Langle123);
		
		
	//Give the computer the task to guess the Form
		
		//brain.setValues(((Muenzen) muenzPanel_1).XVal,((Muenzen) muenzPanel_1).YVal);
		//brain.getDistance();
		
		
	//Fill Frame with muenzPanel	
		this.getContentPane().add(muenzPanel_1);
		this.setResizable(true);
		this.setBounds(100, 400, 772, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	    
	}
}
