package Controller;

import java.awt.event.ActionEvent;

public class ResetActionController extends ActionController {
    public ResetActionController() {
        super("RESET");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action RESET");
        this.isEnabled = false;
        actionExecution.onActionPerformed();
    }
}
