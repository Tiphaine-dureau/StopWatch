package controller;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LapActionController extends ActionController {
    private int lapClickCounter;

    public LapActionController() {
        super("LAP", Color.MAGENTA);
        this.lapClickCounter = 0;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action LAP");
        actionExecution.onActionPerformed();
    }

    public int getLapClickCounter() {
        return lapClickCounter;
    }

    public void setLapClickCounter(int lapClickCounter) {
        this.lapClickCounter = lapClickCounter;
    }
}
