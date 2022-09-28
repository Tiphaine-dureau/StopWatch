package Controller;

import java.awt.event.ActionEvent;

public class StopActionController extends ActionController {
    public StopActionController() {
        super("STOP");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action STOP");
        this.isEnabled = false;
        actionExecution.onActionPerformed();
    }
}
