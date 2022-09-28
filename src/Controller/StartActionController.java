package Controller;

import java.awt.event.ActionEvent;

public class StartActionController extends ActionController {

    public StartActionController() {
        super("START");
        this.isEnabled = true;
        this.getActionView().toggleButton(this.isEnabled);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action START child");
        updateEnabled(false);
        actionExecution.onActionPerformed();
    }
}
