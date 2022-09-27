import Model.ChronoModel;
import Model.TimerModel;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class Chrono extends JFrame {
    private JPanel container = new JPanel();
    //Tableau stockant les éléments à afficher dans le Chrono:
    String[] arrayOfString = {"Start", "Lap", "Stop", "Resume", "Reset"}; // TODO ChronoEnum (model)
    //Un bouton par élément à afficher :
    JButton[] jButtons = new JButton[arrayOfString.length];
    // 4 JLabel : 1 pour le chrono principal et 3 pour les 3 lap en dessous
    private JLabel[] screen = new JLabel[4];
    //Cette variable stocke la dimension des boutons:
    private Dimension dimension = new Dimension(90, 70);
    //Le SwingWorker qui va permettre de lancer le chrono en Background pour ne pas bloquer l'application
    private SwingWorker<Void, Integer> worker;
    private ChronoModel chronoModel;
    private TimerModel timerModel;


    //Constructeur classe Chrono
    public Chrono() {
        this.setSize(350, 435);
        this.setTitle("Chronomètre");
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        this.setLocationRelativeTo(null);
        this.setResizable(false);
        //On initialise le conteneur avec tous les composants
        initComponent();
        //On ajoute le conteneur à la fenêtre;
        this.setContentPane(container);
        this.setVisible(true);
        this.chronoModel = new ChronoModel();
        this.timerModel = new TimerModel();

    }

    // TODO View

    private void initComponent() {
        //On définit les 2 sous conteneurs buttons qui vont contenir les boutons et panEcran qui va contenir les jlabel d'affichage
        JPanel buttons = new JPanel();
        buttons.setPreferredSize(new Dimension(320, 225));
        JPanel panScreen = new JPanel();
        panScreen.setPreferredSize(new Dimension(320, 230));
        panScreen.setBorder(BorderFactory.createLineBorder(Color.black, 2));
        panScreen.setBackground(Color.WHITE);

        //On definit la police d'écriture à utiliser
        Font police = new Font("Arial", Font.BOLD, 50);
        Font police2 = new Font("Arial", Font.BOLD, 25);

        //On initialise les JLABELS avec le texte de départ, la police et la dimension et on les ajoute au sous conteneur correspondant
        for (int i = 0; i < 4; i++) {
            if (i == 0) {
                screen[i] = new JLabel("00:00:00:00");
                screen[i].setFont(police);
            } else {
                screen[i] = new JLabel(i + " : 00:00:00:00");
                screen[i].setFont(police2);
            }

            //On aligne les informations au centre dans le Jlabel
            screen[i].setHorizontalAlignment(JLabel.CENTER);
            screen[i].setPreferredSize(new Dimension(300, 50));
            panScreen.add(screen[i]);
        }

        //On parcourt le tableau initialisé afin de créer nos boutons avec le texte, la dimension et on les ajoute au sous conteneur
        for (int i = 0; i < arrayOfString.length; i++) {
            jButtons[i] = new JButton(arrayOfString[i]);
            jButtons[i].setPreferredSize(dimension);
            buttons.add(jButtons[i]);
            if (i != 0)
                jButtons[i].setEnabled(false);
        }

        //On définit pour chaque bouton l'action Listener correspondant et une couleur
        jButtons[0].addActionListener(new StartListener());
        jButtons[0].setForeground(Color.RED);
        jButtons[1].addActionListener(new LapListener());
        jButtons[1].setForeground(Color.MAGENTA);
        jButtons[2].addActionListener(new StopListener());
        jButtons[2].setForeground(Color.BLUE);
        jButtons[3].addActionListener(new ResumeListener());
        jButtons[3].setForeground(Color.DARK_GRAY);
        jButtons[4].addActionListener(new ResetListener());
        jButtons[4].setForeground(Color.DARK_GRAY);


        //On ajoute après les deux sous conteneurs au conteneur principal container
        container.add(panScreen, BorderLayout.NORTH);
        container.add(buttons, BorderLayout.CENTER);
    }

    private void initializeWorker() {
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
                //savedTimeInMilliseconds = savedTimeInMilliseconds + (savedTimeInMillisecondsOnBreakEnd - savedTimeInMillisecondsOnBreakStart);
                chronoModel.setSavedTimeInMilliseconds(chronoModel.getSavedTimeInMilliseconds() + (chronoModel.getSavedTimeInMillisecondsOnBreakEnd() - chronoModel.getSavedTimeInMillisecondsOnBreakStart()));
                chronoModel.setSavedTimeInMillisecondsOnBreakStart(0);
                chronoModel.setSavedTimeInMillisecondsOnBreakEnd(0);



                while (!isCancelled()) {
                    chronoModel.setCurrentTimeInMilliseconds(System.currentTimeMillis() - chronoModel.getSavedTimeInMilliseconds());
                    chronoModel.setCurrentTimeInMilliseconds(chronoModel.getCurrentTimeInMilliseconds() / 10);
                    timerModel.setMilisecond(chronoModel.getCurrentTimeInMilliseconds() % 100);
                    timerModel.setSecond(chronoModel.getCurrentTimeInMilliseconds() / 100);
                    timerModel.setMinute(timerModel.getSecond()/60);
                    timerModel.setHour(timerModel.getMinute()/60);
                    screen[0].setText(String.format("%02d", timerModel.getHour()) + ":" + String.format("%02d", timerModel.getMinute()) + ":" + String.format("%02d", timerModel.getSecond()) + ":" + String.format("%02d", timerModel.getMilisecond()));
                    //screen[0].paintImmediately(screen[0].getVisibleRect()); ENLEVÉE CAR FAIT BUGUER LE VISUEL
                }
                return null;
            }
        };
    }

    // TODO Listeners dans Controller

    //Listener affecté au bouton Start
    class StartListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            chronoModel.setSavedTimeInMilliseconds(System.currentTimeMillis());
            initializeWorker();
            worker.execute();
            jButtons[0].setEnabled(false);
            jButtons[1].setEnabled(true);
            jButtons[2].setEnabled(true);
            jButtons[4].setEnabled(true);
        }
    }

    // Listener affecté au bouton Lap
    class LapListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent arg0) {
            if (chronoModel.getLapClickCounter() == 3)
                jButtons[1].setEnabled(false);
            screen[chronoModel.getLapClickCounter()].setText(chronoModel.getLapClickCounter() + ":" + String.format("%02d", timerModel.getHour()) + ":" + String.format("%02d", timerModel.getMinute()) + ":" + String.format("%02d", timerModel.getSecond()) + ":" + String.format("%02d", timerModel.getMilisecond()));
            //screen[lap].paintImmediately(screen[lap].getVisibleRect()); ENLEVÉE CAR FAIT BUGUER LE VISUEL
            chronoModel.setLapClickCounter(chronoModel.getLapClickCounter() +1);
        }
    }

    //Listener affecté au bouton Stop
    class StopListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            worker.cancel(true);
            chronoModel.setSavedTimeInMillisecondsOnBreakStart(System.currentTimeMillis());
            jButtons[2].setEnabled(false);
            jButtons[3].setEnabled(true);
        }
    }

    //Listener affecté au bouton Resume
    class ResumeListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            chronoModel.setSavedTimeInMillisecondsOnBreakEnd(System.currentTimeMillis());
            initializeWorker();
            worker.execute();
            jButtons[2].setEnabled(true);
            jButtons[3].setEnabled(false);
        }
    }

    //Listener affecté au bouton Reset
    class ResetListener implements ActionListener {
        public void actionPerformed(ActionEvent arg0) {
            worker.cancel(true);
            timerModel.setHour(0);
            timerModel.setMinute(0);
            timerModel.setSecond(0);
            timerModel.setMilisecond(0);
            chronoModel.setSavedTimeInMillisecondsOnBreakStart(0);
            chronoModel.setSavedTimeInMillisecondsOnBreakEnd(0);
            chronoModel.setLapClickCounter(1);
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    screen[i].setText("00:00:00:00");
                } else {
                    screen[i].setText(i + " : 00:00:00:00");
                }
                jButtons[i].setEnabled(false);
            }
            jButtons[0].setEnabled(true);
            jButtons[4].setEnabled(false);
        }
    }
}
