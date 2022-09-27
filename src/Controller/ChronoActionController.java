package Controller;

import View.ActionView;

import java.awt.event.ActionListener;

public abstract class ChronoActionController implements ActionListener {
    private Boolean isEnabled;
    private ActionView actionView;

    public ChronoActionController(String label) {
        this.isEnabled = false;
        this.actionView = new ActionView(label);

    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
