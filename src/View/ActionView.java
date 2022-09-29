package View;

import javax.swing.*;
import java.awt.*;

public class ActionView {
    private final String label;
    private JButton button;
    private Boolean isEnabled;

    // Constructeur
    public ActionView(String label, Color color) {
        this.label = label;
        this.isEnabled = false;
        this.button = new JButton(label);
        this.button.setPreferredSize(new Dimension(90, 70));
        this.button.setForeground(color);
    }

    public void toggleButton(Boolean isEnabled) {
        button.setEnabled(isEnabled);
    }


    public JButton getButton() {
        return button;
    }

}
