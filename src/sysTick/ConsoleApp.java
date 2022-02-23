package sysTick;

import java.util.Scanner;

public class ConsoleApp {
	CortexM0_SysTick CortexSysTick = new CortexM0_SysTick();
	static int x,y;
	static Scanner in = new Scanner( System.in );
	
	 private void pressEnter()
	 { 
	        System.out.println("Press Enter key to continue...");
	        try
	        {
	            System.in.read();
	        }  
	        catch(Exception e)
	        {}  
	 }
	 public void menu() {
		 do {
			 String ENABLE = CortexSysTick.isEnableFlag()?"1":"0";
			 String COUNTFLAG = CortexSysTick.isCountFlag()?"1":"0";
			 String TICKINT = CortexSysTick.isInterruptFlag()?"1":"0";
			 String INTERRUPT= CortexSysTick.isInterrupt()?"1":"0";
			 String SOURCE;
			 if(CortexSysTick.source()) 
			 	SOURCE= "Internal";
			 else 
			 	SOURCE= "External";


			 System.out.println("*************************************************");
			 System.out.println("Aktualne wartoœci:\nCVR = " + CortexSysTick.getCVR() + "\t\tRVR = " + CortexSysTick.getRVR());
			 System.out.println("ENABLE = " + ENABLE + "\tCOUNTFLAG = " + COUNTFLAG + "\t\tTICKINT = " + TICKINT);
			 System.out.println("INTERRUPT = " + INTERRUPT + "\tSOURCE = " + SOURCE );
			 System.out.println("*************************************************");
			 System.out.println("1. Odczyt CSR");
			 System.out.println("2. Ustaw CVR");
			 System.out.println("3. Ustaw RVR");
			 System.out.println("4. Wykonaj funkcjê tick");
			 System.out.println("5. Ustaw zród³o timera (internal/external)"); 
			 System.out.println("6. W³¹cz lub wy³¹cz timer"); 
			 System.out.println("7. Zresetuj CSR"); 
			 System.out.println("8. W³¹cz lub wy³¹cz obs³ugê przerwañ"); 
			 System.out.println("9. Wyjscie");
			 x = in.nextInt();
			clear();
			 switch(x)
			 {
			 	case 1 :
			 		System.out.println("CSR = " + Integer.toBinaryString(CortexSysTick.getCSR()));
			 		pressEnter();
			 		clear();
			 		break;
			 	case 2 :
			 		System.out.println("Podaj wartoœæ jak¹ chcesz wprowadziæ : ");
			 		y=in.nextInt();
			 		CortexSysTick.setCVR(y);
			 		pressEnter();
			 		clear();
			 		break;
			 	case 3 :
			 		System.out.println("Podaj wartoœæ jak¹ chcesz wprowadziæ : ");
			 		y=in.nextInt();
			 		CortexSysTick.setRVR(y);
			 		pressEnter();
			 		clear();
			 		break;
			 	case 4 :
			 		System.out.println("Podaj ile razy chcesz wywo³aæ funkcjê : ");
			 		y=in.nextInt();
			 		for (int i=0;i<y;i++)
			 			CortexSysTick.tick();
			 		pressEnter();
			 		clear();
			 		break;
			 	case 5 :
			 		System.out.println("Wprowadz czy chcesz korzystac z wewnetrznego timera (1) czy zewnetrznego (0) : ");
			 		y=in.nextInt();
			 		if (y==1)
			 			CortexSysTick.setSource(true);
			 		else if (y==0)
			 			CortexSysTick.setSource(false);
			 		pressEnter();
			 		clear();
			 		break;
			 	case 6 :
			 		System.out.println("Wprowadz czy chcesz w³¹czyæ timer (1) czy wy³¹czyæ (0) : ");
			 		y=in.nextInt();
			 		if (y==1)
			 			CortexSysTick.setEnable();
			 		else if (y==0)
			 			CortexSysTick.setDisable();
			 		pressEnter();
			 		clear();
			 		break;
			 	case 7 :
			 		CortexSysTick.reset();
			 		System.out.println("Timer zosta³ zresetowany ");
			 		pressEnter();
			 		clear();
			 		break;
			 	case 8 :
			 		System.out.println("Wprowadz czy chcesz w³¹czyæ obs³ugê przerwañ (1) czy wy³¹czyæ (0) : ");
			 		y=in.nextInt();
			 		if (y==1)
			 			CortexSysTick.setInterruptEnable();
			 		else if (y==0)
			 			CortexSysTick.setInterruptDisable();
			 		pressEnter();
			 		clear();
			 		break;
			 		
			 }
			 }while(x!=9);
	 }
	 public void clear() {
		 for (int i=0;i<5;i++)
			 System.out.println("");
	 }
	public static void main(String[] args) {
		ConsoleApp console = new ConsoleApp();
		do {
		console.menu();
		}while(x!=9);
	}

}


