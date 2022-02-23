package sysTick;

import static org.junit.Assert.assertEquals;
import java.util.Scanner;


public class CortexM0_SysTick implements Cortex_M0_SysTic_Interface{
	private int CVR,RVR,CSR;
	private boolean ENABLE,TICKINT,CLKSRC,COUNTFLAG,INTERRUPT,zeroCVR = false;


	public CortexM0_SysTick() {
		
	}
	
	public void tickExternal() {
		if (CLKSRC==false) 
			tick();
	}
	public void tickInternal() {
		if (CLKSRC==true)
			tick();
	}
	
	
	public void setCVR(int CVR) {
		this.CVR=0;
		COUNTFLAG=false;
		if (CVR==0)
			zeroCVR=true;
	}
	public void setRVR(int RVR) {
		this.RVR=RVR;
		if (RVR==0)
			{
			CVR=0;
			ENABLE=false;
			}
		else if(RVR<0)
			this.RVR = ((1<<24)-1);
		else if (RVR>(1<<24))
			this.RVR=1;
	}
	public void setCSR(int CSR) {
		this.CSR=CSR;
		COUNTFLAG=false;
	}
	
	
	public void reset() {
		ENABLE=false;
		TICKINT=false;
		CLKSRC=false;
		COUNTFLAG=false; 
		INTERRUPT=false;
		
	}
	public void setEnable() {
		ENABLE=true;
	}
	public void setDisable() {
		ENABLE=false;
	}
	public void setSource(boolean source) {
		if (source)
			CLKSRC=true;
		else
			CLKSRC=false;
	}
	public void setInterruptEnable() {
		TICKINT=true;
	}
	public void setInterruptDisable() {
		TICKINT=false;
		INTERRUPT=false;
	}
	public void setCountflagEnable() {
		COUNTFLAG=true;
	}
	public void setCountflagDisable() {
		COUNTFLAG=false;
	}
	
	public int getCVR() {
		return CVR;
	}
	public int getRVR() {
		return RVR;
	}
	public int getCSR() {	
		CSR=0;
		if(ENABLE)
			CSR+=1;
		if(TICKINT)
			CSR+=2;
		if(CLKSRC)
			CSR+=4;
		if(COUNTFLAG)
			CSR+=65536;
		COUNTFLAG=false;
		return CSR;
	}	
	
	
	public boolean getEnabled() {
		COUNTFLAG=false;
		return ENABLE;
		
	}
	public boolean getInterrupt() {
		COUNTFLAG=false;
		return TICKINT;
	}
	public boolean isCountFlag() {
		return COUNTFLAG;
	}
	public boolean isEnableFlag() {
		return ENABLE;
	}
	public boolean isInterruptFlag() {
		return TICKINT;
	}
	public boolean isInterrupt() {
		return INTERRUPT;
	}
	public boolean source() {
		return CLKSRC;
	}
	
	
	public void tick() {
		if (ENABLE==true) {
				if (zeroCVR==true)
				{
					reloadSysTick();
					zeroCVR=false;
				}
				else if(CVR==0)
				{
					reloadSysTick(); 
				}
				else
				{
					CVR--;

				}	
				if(CVR==0) {
					COUNTFLAG=true; 
					if (TICKINT)
						INTERRUPT=true;}
		}
		
	}
	public void reloadSysTick() {
		CVR=RVR;
	}
	public static void main(String[] args) {
		
	}
}
