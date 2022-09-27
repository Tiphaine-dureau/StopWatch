package Controller;

import java.awt.event.ActionListener;

public abstract class ChronoActionController implements ActionListener {

    private final String label;
    private Boolean isEnabled;

    public ChronoActionController(String label) {
        this.label = label;
        this.isEnabled = false;
    }

    public String getLabel() {
        return label;
    }

    public Boolean getEnabled() {
        return isEnabled;
    }

    public void setEnabled(Boolean enabled) {
        isEnabled = enabled;
    }
}
