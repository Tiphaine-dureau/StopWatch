package Model;

public class ChronoModel {
    private TimerModel timerModel;

    //Cette variable permet de connaitre combien de fois on a actionné le bouton Lap :
    private int lapClickCounter;

    //Les variables qui vont permettre de calculer le temps et simuler le chrono :
    long savedTimeInMilliseconds;
    long currentTimeInMilliseconds;
    long savedTimeInMillisecondsOnBreakStart;
    long savedTimeInMillisecondsOnBreakEnd;

    public ChronoModel() {
        this.timerModel = new TimerModel();
        this.lapClickCounter = 0;
        this.savedTimeInMilliseconds = 0;
        this.currentTimeInMilliseconds = 0;
        this.savedTimeInMillisecondsOnBreakStart = 0;
        this.savedTimeInMillisecondsOnBreakEnd = 0;
    }

    public TimerModel getTimerModel() {
        return timerModel;
    }

    public void setTimerModel(TimerModel timerModel) {
        this.timerModel = timerModel;
    }

    public int getLapClickCounter() {
        return lapClickCounter;
    }

    public void setLapClickCounter(int lapClickCounter) {
        this.lapClickCounter = lapClickCounter;
    }

    public long getSavedTimeInMilliseconds() {
        return savedTimeInMilliseconds;
    }

    public void setSavedTimeInMilliseconds(long savedTimeInMilliseconds) {
        this.savedTimeInMilliseconds = savedTimeInMilliseconds;
    }

    public long getCurrentTimeInMilliseconds() {
        return currentTimeInMilliseconds;
    }

    public void setCurrentTimeInMilliseconds(long currentTimeInMilliseconds) {
        this.currentTimeInMilliseconds = currentTimeInMilliseconds;
    }

    public long getSavedTimeInMillisecondsOnBreakStart() {
        return savedTimeInMillisecondsOnBreakStart;
    }

    public void setSavedTimeInMillisecondsOnBreakStart(long savedTimeInMillisecondsOnBreakStart) {
        this.savedTimeInMillisecondsOnBreakStart = savedTimeInMillisecondsOnBreakStart;
    }

    public long getSavedTimeInMillisecondsOnBreakEnd() {
        return savedTimeInMillisecondsOnBreakEnd;
    }

    public void setSavedTimeInMillisecondsOnBreakEnd(long savedTimeInMillisecondsOnBreakEnd) {
        this.savedTimeInMillisecondsOnBreakEnd = savedTimeInMillisecondsOnBreakEnd;
    }

}
