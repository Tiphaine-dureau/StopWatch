package Model;

public class ChronoModel {

    //Cette variable permet de connaitre combien de fois on a actionné le bouton Lap :
    private int lapClickCounter = 0;

    //Les variables qui vont permettre de calculer le temps et simuler le chrono :
    long savedTimeInMilliseconds = 0;
    long currentTimeInMilliseconds = 0;
    long savedTimeInMillisecondsOnBreakStart = 0;
    long savedTimeInMillisecondsOnBreakEnd = 0;


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
