package standard.eclipse.muenzspiel;

import java.util.TimerTask;

public class MyTimerTask extends TimerTask {

	double dy, dx;
	double f = 10;
	double dt =0;
	int x, y;
	int index =0;
	Muenzen m;
	
	MyTimerTask(int index){
		
		this.index=index;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
}
