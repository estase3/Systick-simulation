package sysTick;

import java.awt.event.*;

/**
 * Write a description of interface PulseSource here.
 *
 * @author (x)
 * @version (a version number or a date)
 */

public interface PulseSource {
    final static byte BURST_MODE = 0;

    void addActionListener(ActionListener pl);

    void removeActionListener(ActionListener pl);

    void trigger();

    void setMode(byte mode);

    byte getMode();

    void halt();

    void setPulseDelay(int ms);

    int getPulseDelay();

    void setPulseCount(int burst);
}


 
