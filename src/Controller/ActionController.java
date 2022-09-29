package Controller;

import View.ActionView;

import java.awt.*;
import java.awt.event.ActionListener;

public abstract class ActionController implements ActionListener {
    private final ActionView actionView;
    protected Boolean isEnabled;
    public ActionExecution actionExecution;

    // Constructeur
    public ActionController(String label, Color color) {
        this.actionView = new ActionView(label, color);
        this.actionView.getButton().addActionListener(this);
        this.actionExecution = () -> System.out.println("on action execution");
        isEnabled = false;
    }

    public void updateEnabled(Boolean isEnabled) {
        this.isEnabled = isEnabled;
        actionView.toggleButton(this.isEnabled);
    }

    public ActionView getActionView() {
        return actionView;
    }
}
