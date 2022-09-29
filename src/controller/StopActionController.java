package controller;

import java.awt.*;
import java.awt.event.ActionEvent;

public class StopActionController extends ActionController {
    public StopActionController() {
        super("STOP", Color.BLUE);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action STOP");
        this.isEnabled = false;
        actionExecution.onActionPerformed();
    }
}
