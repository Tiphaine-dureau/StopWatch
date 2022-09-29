package Controller;

import Model.ChronoModel;
import java.awt.*;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;

public class ChronoController extends JFrame {
    private JPanel container = new JPanel();
    // 4 JLabel : 1 pour le chrono principal et 3 pour les 3 lap en dessous
    private JLabel[] screen = new JLabel[4];
    //Le SwingWorker qui va permettre de lancer le chrono en Background pour ne pas bloquer l'application
    private SwingWorker<Void, Integer> worker;
    private ChronoModel chronoModel;


    public ChronoController() {
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
    }

    // TODO View

    private void initComponent() {
        //On définit les 2 sous conteneurs buttons qui vont contenir les boutons et panEcran qui va contenir les jlabel d'affichage
        JPanel buttonsPanel = new JPanel();
        buttonsPanel.setPreferredSize(new Dimension(320, 225));
        JPanel panScreen = new JPanel();
        //Dimension de l'écran blanc qui contient le temps
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
            //Espace entre les temps affichés
            screen[i].setPreferredSize(new Dimension(300, 50));
            panScreen.add(screen[i]);
        }

        //On ajoute après les deux sous conteneurs au conteneur principal container
        container.add(panScreen, BorderLayout.NORTH);
        container.add(buttonsPanel, BorderLayout.CENTER);
        List<ActionController> actionControllers = createActionControllers();
        addActionButtonsToActionsPanel(actionControllers, buttonsPanel);
        addActionExecutions(actionControllers);
    }

    private List<ActionController> createActionControllers() {
        List<ActionController> controllers = new ArrayList<>();
        StartActionController startActionController = new StartActionController();
        LapActionController lapActionController = new LapActionController();
        StopActionController stopActionController = new StopActionController();
        ResumeActionController resumeActionController = new ResumeActionController();
        ResetActionController resetActionController = new ResetActionController();

        controllers.add(startActionController);
        controllers.add(lapActionController);
        controllers.add(stopActionController);
        controllers.add(resumeActionController);
        controllers.add(resetActionController);
        return controllers;
    }

    private void addActionButtonsToActionsPanel(List<ActionController> controllers, JPanel buttonsPanel) {
        controllers.forEach((ActionController controller) -> buttonsPanel.add(controller.getActionView().getButton()));
    }

    private void addActionExecutions(List<ActionController> controllers) {
        // START
        controllers.get(0).actionExecution = () -> {
            chronoModel.setSavedTimeInMilliseconds(System.currentTimeMillis());
            initializeWorker();
            worker.execute();
            controllers.get(1).updateEnabled(true);
            controllers.get(2).updateEnabled(true);
            controllers.get(4).updateEnabled(true);
        };
        // LAP
        controllers.get(1).actionExecution = () -> {
            if (chronoModel.getLapClickCounter() == 3) {
                controllers.get(1).updateEnabled(false);
            }
            screen[chronoModel.getLapClickCounter()].setText(chronoModel.getLapClickCounter() + ":" + String.format("%02d", chronoModel.getTimerModel().getHour()) + ":" + String.format("%02d", chronoModel.getTimerModel().getMinute()) + ":" + String.format("%02d", chronoModel.getTimerModel().getSecond()) + ":" + String.format("%02d", chronoModel.getTimerModel().getMillisecond()));
            //screen[lap].paintImmediately(screen[lap].getVisibleRect()); ENLEVÉE CAR FAIT BUGUER LE VISUEL
            chronoModel.setLapClickCounter(chronoModel.getLapClickCounter() + 1);
        };
        // STOP
        controllers.get(2).actionExecution = () -> {
            worker.cancel(true);
            chronoModel.setSavedTimeInMillisecondsOnBreakStart(System.currentTimeMillis());
            controllers.get(2).updateEnabled(false);
            controllers.get(3).updateEnabled(true);
        };
        // RESUME
        controllers.get(3).actionExecution = () -> {
            chronoModel.setSavedTimeInMillisecondsOnBreakEnd(System.currentTimeMillis());
            initializeWorker();
            worker.execute();
            controllers.get(2).updateEnabled(true);
            controllers.get(3).updateEnabled(false);
        };
        // RESET
        controllers.get(4).actionExecution = () -> {
            worker.cancel(true);
            chronoModel.getTimerModel().setHour(0);
            chronoModel.getTimerModel().setMinute(0);
            chronoModel.getTimerModel().setSecond(0);
            chronoModel.getTimerModel().setMillisecond(0);
            chronoModel.setSavedTimeInMillisecondsOnBreakStart(0);
            chronoModel.setSavedTimeInMillisecondsOnBreakEnd(0);
            chronoModel.setLapClickCounter(1);
            for (int i = 0; i < 4; i++) {
                if (i == 0) {
                    screen[i].setText("00:00:00:00");
                } else {
                    screen[i].setText(i + " : 00:00:00:00");
                }
                controllers.get(i).updateEnabled(false);
            }
            controllers.get(0).updateEnabled(true);
            controllers.get(4).updateEnabled(false);
        };
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
                    chronoModel.getTimerModel().setMillisecond(chronoModel.getCurrentTimeInMilliseconds() % 100);
                    chronoModel.getTimerModel().setSecond(chronoModel.getCurrentTimeInMilliseconds() / 100);
                    chronoModel.getTimerModel().setMinute(chronoModel.getTimerModel().getSecond() / 60);
                    chronoModel.getTimerModel().setHour(chronoModel.getTimerModel().getMinute() / 60);
                    screen[0].setText(String.format("%02d", chronoModel.getTimerModel().getHour()) + ":" + String.format("%02d", chronoModel.getTimerModel().getMinute()) + ":" + String.format("%02d", chronoModel.getTimerModel().getSecond()) + ":" + String.format("%02d", chronoModel.getTimerModel().getMillisecond()));
                    //screen[0].paintImmediately(screen[0].getVisibleRect()); ENLEVÉE CAR FAIT BUGUER LE VISUEL
                }
                return null;
            }
        };
    }
}
