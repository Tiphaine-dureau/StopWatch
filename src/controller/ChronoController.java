package controller;

import model.ChronoModel;
import view.ChronoView;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

public class ChronoController {
    private SwingWorker<Void, Integer> worker;
    private final ChronoModel chronoModel;
    private final ChronoView chronoView;

    // Constructeur
    public ChronoController() {
        this.chronoModel = new ChronoModel();
        this.chronoView = new ChronoView();
        List<ActionController> actionControllers = createActionControllers();
        addActionButtonsToActionsPanel(actionControllers);
        addActionExecutions(actionControllers);
        chronoView.configureAndDisplayFrame();
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

    private void addActionButtonsToActionsPanel(List<ActionController> controllers) {
        controllers.forEach((ActionController controller) -> chronoView.getButtonsPanel().add(controller.getActionView().getButton()));
    }

    private void addStartActionExecution(List<ActionController> controllers) {
        controllers.get(0).actionExecution = () -> {
            chronoModel.setSavedTimeInMilliseconds(System.currentTimeMillis());
            initializeWorker();
            worker.execute();
            controllers.get(1).updateEnabled(true);
            controllers.get(2).updateEnabled(true);
            controllers.get(4).updateEnabled(true);
        };
    }

    private void addLapActionExecution(List<ActionController> controllers) {
        LapActionController lapActionController = (LapActionController) controllers.get(1);
        lapActionController.actionExecution = () -> {
            if (lapActionController.getLapClickCounter() == 2) {
                lapActionController.updateEnabled(false);
            }
            chronoView.updateLapLabelText(
                    lapActionController.getLapClickCounter(),
                    chronoModel.getTimerModel().getHour(),
                    chronoModel.getTimerModel().getMinute(),
                    chronoModel.getTimerModel().getSecond(),
                    chronoModel.getTimerModel().getMillisecond()
            );
            lapActionController.setLapClickCounter(lapActionController.getLapClickCounter() + 1);
        };
    }

    private void addStopActionExecution(List<ActionController> controllers) {
        controllers.get(2).actionExecution = () -> {
            worker.cancel(true);
            chronoModel.setSavedTimeInMillisecondsOnBreakStart(System.currentTimeMillis());
            controllers.get(2).updateEnabled(false);
            controllers.get(3).updateEnabled(true);
        };
    }

    private void addResumeActionExecution(List<ActionController> controllers) {
        controllers.get(3).actionExecution = () -> {
            chronoModel.setSavedTimeInMillisecondsOnBreakEnd(System.currentTimeMillis());
            initializeWorker();
            worker.execute();
            controllers.get(2).updateEnabled(true);
            controllers.get(3).updateEnabled(false);
        };
    }

    private void addResetActionExecution(List<ActionController> controllers) {
        LapActionController lapActionController = (LapActionController) controllers.get(1);
        controllers.get(4).actionExecution = () -> {
            worker.cancel(true);
            chronoModel.getTimerModel().setHour(0);
            chronoModel.getTimerModel().setMinute(0);
            chronoModel.getTimerModel().setSecond(0);
            chronoModel.getTimerModel().setMillisecond(0);
            chronoModel.setSavedTimeInMillisecondsOnBreakStart(0);
            chronoModel.setSavedTimeInMillisecondsOnBreakEnd(0);
            lapActionController.setLapClickCounter(0);

            controllers.get(0).updateEnabled(true);
            controllers.get(1).updateEnabled(false);
            controllers.get(2).updateEnabled(false);
            controllers.get(3).updateEnabled(false);
            controllers.get(4).updateEnabled(false);

            chronoView.resetLabels();
        };
    }

    private void addActionExecutions(List<ActionController> controllers) {
        addStartActionExecution(controllers);
        addLapActionExecution(controllers);
        addStopActionExecution(controllers);
        addResumeActionExecution(controllers);
        addResetActionExecution(controllers);
    }

    private void initializeWorker() {
        worker = new SwingWorker<>() {
            @Override
            protected Void doInBackground() throws Exception {
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
                    chronoView.updateChronoLabelText(
                            chronoModel.getTimerModel().getHour(),
                            chronoModel.getTimerModel().getMinute(),
                            chronoModel.getTimerModel().getSecond(),
                            chronoModel.getTimerModel().getMillisecond()
                    );
                }
                return null;
            }
        };
    }
}
