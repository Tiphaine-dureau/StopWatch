package View;

import javax.swing.*;

public class ActionView {
    private final String label;
    private JButton button;
    private Boolean isEnabled;

    // Constructeur
    public ActionView(String label) {
        this.label = label;
        this.isEnabled = false;
        this.button = new JButton(label);
    }

    public void toggleButton(Boolean isEnabled){
        button.setEnabled(isEnabled);
    }

    public JButton getButton() {
        return button;
    }

}
