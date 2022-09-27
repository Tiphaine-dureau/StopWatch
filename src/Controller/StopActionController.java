package Controller;

import java.awt.event.ActionEvent;

public class StopActionController extends ChronoActionController {
    public StopActionController(String label) {
        super(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action STOP");
    }
}
