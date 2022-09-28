package Controller;

import View.ActionView;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public abstract class ActionController implements ActionListener {
    private ActionView actionView;
    protected Boolean isEnabled;
    public ActionExecution actionExecution;

    public ActionController(String label) {
        this.isEnabled = false;
        this.actionView = new ActionView(label);
        this.actionView.getButton().addActionListener(this);
        this.actionExecution = () -> System.out.println("on action execution");
    }

    public ActionView getActionView() {
        return actionView;
    }
}
