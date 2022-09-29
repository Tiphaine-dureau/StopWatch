package View;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;

public class ChronoView {
    private final JLabel chronoLabel;
    private final List<JLabel> lapLabels;

    // Constructeur
    public ChronoView() {
        chronoLabel = new JLabel();
        List<JLabel> lapLabels = new ArrayList<>();
        for (int i = 0; i < 3; i++) {
            JLabel label = new JLabel();
            label.setHorizontalAlignment(JLabel.CENTER);
            label.setPreferredSize(new Dimension(300, 50));
            label.setFont(new Font("Arial", Font.BOLD, 25));
            lapLabels.add(label);
        }
        this.lapLabels = lapLabels;
        chronoLabel.setHorizontalAlignment(JLabel.CENTER);
        chronoLabel.setPreferredSize(new Dimension(300, 50));
        chronoLabel.setFont(new Font("Arial", Font.BOLD, 50));

        resetLabels();
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

    public JLabel getChronoLabel() {
        return chronoLabel;
    }

    public List<JLabel> getLapLabels() {
        return lapLabels;
    }
}
