package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;

public class ResetActionController extends ActionController {
    public ResetActionController() {
        super("RESET", Color.DARK_GRAY);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action RESET");
        actionExecution.onActionPerformed();
    }
}
