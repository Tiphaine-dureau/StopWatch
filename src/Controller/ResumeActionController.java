package Controller;

import java.awt.event.ActionEvent;

public class ResumeActionController extends ActionController {
    public ResumeActionController() {
        super("RESUME");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action RESUME");
        actionExecution.onActionPerformed();
    }
}
