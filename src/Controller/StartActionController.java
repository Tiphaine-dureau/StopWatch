package Controller;

import java.awt.event.ActionEvent;

public class StartActionController extends ChronoActionController {
    public StartActionController(String label) {
        super(label);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        System.out.println("perform action START");
    }
}
