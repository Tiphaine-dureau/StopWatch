package Controller;

import java.awt.*;
import java.awt.event.ActionEvent;

public class LapActionController extends ActionController {
    public LapActionController() {
        super("LAP", Color.MAGENTA);

    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action LAP");
        actionExecution.onActionPerformed();
    }
}
