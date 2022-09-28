package Controller;

import java.awt.event.ActionEvent;

public class StartActionController extends ActionController {

    public StartActionController() {
        super("START");
        this.isEnabled = true;
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action START child");
        this.isEnabled = false;
        actionExecution.onActionPerformed();
    }
}
