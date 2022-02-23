package sysTick;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Generator extends Thread implements PulseSource{

	Boolean alive=true;
	Boolean run=false;
	private int delay;
	private int pulseCount;
	private byte mode;
	ActionListener al; 
	public void run() {
		while(alive) {
			try {
				
				if(run) {
					if(al!=null)
						al.actionPerformed(new ActionEvent(this,ActionEvent.ACTION_PERFORMED,"tick"));
					Thread.sleep(delay);
				}
			
				Thread.sleep(1);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}	
		}
	}
	@Override
	public void addActionListener(ActionListener pl) {
		al = AWTEventMulticaster.add(al,pl);
		
	}

	@Override
	public void removeActionListener(ActionListener pl) {
		al = AWTEventMulticaster.remove(al,pl);
	}
	
	public void kill() {
		alive=false;
	}
	


	@Override
	public void trigger() {
		run=true;
	}

	@Override
	public void setMode(byte mode) {
		// TODO Auto-generated method stub
		this.mode=mode;
		
	}

	@Override
	public byte getMode() {
		// TODO Auto-generated method stub
		return mode;
	}

	@Override
	public void halt() {
		run=false;
	}

	@Override
	public void setPulseDelay(int ms) {
		// TODO Auto-generated method stub
		delay=ms;
	}

	@Override
	public int getPulseDelay() {
		// TODO Auto-generated method stub
		return delay;
	}

	@Override
	public void setPulseCount(int count) {
		// TODO Auto-generated method stub
		pulseCount = count;
	}

}
 
