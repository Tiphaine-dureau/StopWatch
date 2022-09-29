package view;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChronoView extends JFrame {
    private final JPanel containerPanel;
    private final JPanel timerPanel;
    private final JPanel buttonsPanel;
    private final JLabel chronoLabel;
    private final List<JLabel> lapLabels;

    // Constructeur
    public ChronoView() {
        chronoLabel = new JLabel();
        timerPanel = new JPanel();
        lapLabels = new ArrayList<>();
        containerPanel = new JPanel();
        buttonsPanel = new JPanel();
        configureTimerPanel();
        configureButtonsPanel();
        configureLabels();
        configureContainerPanel();
    }

    public void resetLabels() {
        updateChronoLabelText(0, 0, 0, 0);
        for (int i = 0; i < lapLabels.size(); i++) {
            updateLapLabelText(i, 0, 0, 0, 0);
        }
    }

    public void updateChronoLabelText(long hour, long minute, long second, long millisecond) {
        chronoLabel.setText(getTimeText(hour, minute, second, millisecond));
    }

    public void updateLapLabelText(int lapLabelIndex, long hour, long minute, long second, long millisecond) {
        lapLabels.get(lapLabelIndex).setText((lapLabelIndex + 1) + " : " + getTimeText(hour, minute, second, millisecond));
    }

    private String getTimeText(long hour, long minute, long second, long millisecond) {
        String formattedHour = String.format("%02d", hour);
        String formattedMinute = String.format("%02d", minute);
        String formattedSecond = String.format("%02d", second);
        String formattedMillisecond = String.format("%02d", millisecond);
        return formattedHour + ":" +
                formattedMinute + ":" +
                formattedSecond + ":" +
                formattedMillisecond;
    }

    public void configureAndDisplayFrame() {
        this.setSize(350, 435);
        this.setTitle("Chronomètre");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        this.setContentPane(containerPanel);
        this.setVisible(true);
    }

    private void configureLabels() {
        configureChronoLabel();
        configureLapLabels();
        resetLabels();
    }

    private void configureTimerPanel() {
        this.timerPanel.setPreferredSize(new Dimension(320, 230));
        this.timerPanel.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        this.timerPanel.setBackground(Color.WHITE);
        this.timerPanel.add(chronoLabel);
    }

    private void configureChronoLabel() {
        this.chronoLabel.setHorizontalAlignment(JLabel.CENTER);
        this.chronoLabel.setPreferredSize(new Dimension(300, 50));
        this.chronoLabel.setFont(new Font("Arial", Font.BOLD, 50));
    }

    private void configureLapLabels() {
        for (int i = 0; i < 3; i++) {
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setPreferredSize(new Dimension(300, 50));
            label.setFont(new Font("Arial", Font.BOLD, 25));
            this.lapLabels.add(label);
        }
        this.lapLabels.forEach(timerPanel::add); // Method reference
    }

    private void configureContainerPanel() {
        this.containerPanel.add(this.timerPanel, BorderLayout.NORTH);
        this.containerPanel.add(this.buttonsPanel, BorderLayout.CENTER);
    }

    private void configureButtonsPanel() {
        this.buttonsPanel.setPreferredSize(new Dimension(320, 225));
    }

    public JPanel getButtonsPanel() {
        return buttonsPanel;
    }
}
