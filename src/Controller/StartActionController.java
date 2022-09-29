package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;

public class StartActionController extends ActionController {

    public StartActionController() {
        super("START", Color.RED);
        this.isEnabled = true;
        this.getActionView().toggleButton(this.isEnabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action START");
        updateEnabled(false);
        actionExecution.onActionPerformed();
    }
}
