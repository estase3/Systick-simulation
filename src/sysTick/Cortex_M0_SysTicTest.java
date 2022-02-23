package sysTick;

import static org.junit.Assert.*;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

public class Cortex_M0_SysTicTest
{
    
    /**
     * Default constructor for test class Cortex_M0_SysTicTest
     */
    public Cortex_M0_SysTicTest()
    {
        
    }
    
    @Test
    public void disable_test()
    {
    	CortexM0_SysTick cortexM01 = new CortexM0_SysTick();
        cortexM01.setSource(true); // internal clock
        cortexM01.setRVR(1);
        cortexM01.setCVR(4);
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(1, cortexM01.getCVR());
        cortexM01.setRVR(0);
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(0, cortexM01.getRVR());
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(0, cortexM01.getRVR());
        cortexM01.setRVR(2);
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(2, cortexM01.getRVR());
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(2, cortexM01.getCVR());
        cortexM01.tickInternal();			 
        assertEquals(1, cortexM01.getCVR());
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
    }
    
    @Test
    public void testReload()
    {
    	CortexM0_SysTick cortexM01 = new CortexM0_SysTick();
        cortexM01.setSource(true); // internal clock
        cortexM01.setCVR(4);
        cortexM01.setRVR(4);
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(4, cortexM01.getRVR());
        for (int i=0; i<5; i++) cortexM01.tickInternal();
        assertEquals(4, cortexM01.getRVR());
        assertEquals(true, cortexM01.isCountFlag());
        cortexM01.getCSR();
        assertEquals(false, cortexM01.isCountFlag());
    }
    
    @Test
    public void testFlags()
    {
    	CortexM0_SysTick cortexM01 = new CortexM0_SysTick();
        cortexM01.setSource(true); // internal clock
        assertEquals(false, cortexM01.isInterruptFlag());
        assertEquals(false, cortexM01.isCountFlag());
        assertEquals(false, cortexM01.isEnableFlag());
        cortexM01.setEnable(); 
        cortexM01.setInterruptEnable();
        assertEquals(true, cortexM01.isInterruptFlag());
        assertEquals(false, cortexM01.isCountFlag());
        assertEquals(true, cortexM01.isEnableFlag());
        cortexM01.setCVR(4);
        cortexM01.setRVR(1);
        cortexM01.tickInternal();
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(true, cortexM01.isCountFlag());
        cortexM01.getCSR();
        assertEquals(false, cortexM01.isCountFlag());
    }
    
    
    @Test
    public void testRVR()
    {
    	CortexM0_SysTick cortexM01 = new CortexM0_SysTick();
        cortexM01.setSource(true); // internal clock
        cortexM01.setCVR(0);
        cortexM01.setRVR(0);
        assertEquals(0, cortexM01.getRVR());
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        cortexM01.setRVR(10);
        assertEquals(10, cortexM01.getRVR());
        cortexM01.setRVR(-1);
        assertEquals((1<<24)-1, cortexM01.getRVR());
        cortexM01.setRVR((1<<24)+1);
        assertEquals(1, cortexM01.getRVR());
    }
    
    
    @Test
    public void testInputs()
    {
    	CortexM0_SysTick cortexM01 = new CortexM0_SysTick();
        cortexM01.setSource(true); // internal clock
        cortexM01.setCVR(0);
        cortexM01.setRVR(5);
        assertEquals(0, cortexM01.getCVR());
        cortexM01.setEnable();
        cortexM01.tickInternal();
        assertEquals(5, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
        cortexM01.setSource(false); // external clock
        for(int i=0;i<5;++i)
            cortexM01.tickInternal();
        assertEquals(5, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
        cortexM01.setSource(true); // internal clock
        for(int i=0;i<5;++i)
            cortexM01.tickInternal();
        assertEquals(0, cortexM01.getCVR());
        assertEquals(true, cortexM01.isCountFlag());
        cortexM01.setCVR(10);
        assertEquals(0, cortexM01.getCVR());
        assertEquals(false, cortexM01.isCountFlag());
    }
    
 
    @Test
    public void testInt()
    {
    	CortexM0_SysTick cortexM01 = new CortexM0_SysTick();
        cortexM01.setSource(true); // internal clock
        cortexM01.setCVR(0);
        cortexM01.setRVR(1);
        cortexM01.setEnable();
        cortexM01.setInterruptEnable();
        assertEquals(false, cortexM01.isInterrupt()); 
        cortexM01.tickInternal();
        cortexM01.tickInternal();
        assertEquals(true, cortexM01.isCountFlag());
        assertEquals(0, cortexM01.getCVR());
        assertEquals(true, cortexM01.isInterrupt());
        cortexM01.tickInternal();
        assertEquals(1, cortexM01.getCVR());
    }
    
}
 

 

 

