package View;

import javax.swing.*;

public class ActionView {
    private final String label;
    private JButton button;
    private Boolean isEnabled;

    public ActionView(String label) {
        this.label = label;
        this.isEnabled = false;
        this.button = new JButton(label);
    }

    public JButton getButton() {
        return button;
    }
}
