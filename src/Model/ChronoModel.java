package Model;

public class ChronoModel {
    //Cette variable permet de connaitre combien de fois on a actionné le bouton Lap :
    private int lap = 0;
    //Les variables qui vont permettre de calculer le temps et simuler le chrono :
    long initVar, nowVar, breakStart = 0, breakEnd = 0;

    public int getLap() {
        return lap;
    }

    public void setLap(int lap) {
        this.lap = lap;
    }

    public long getInitVar() {
        return initVar;
    }

    public void setInitVar(long initVar) {
        this.initVar = initVar;
    }

    public long getNowVar() {
        return nowVar;
    }

    public void setNowVar(long nowVar) {
        this.nowVar = nowVar;
    }

    public long getBreakStart() {
        return breakStart;
    }

    public void setBreakStart(long breakStart) {
        this.breakStart = breakStart;
    }

    public long getBreakEnd() {
        return breakEnd;
    }

    public void setBreakEnd(long breakEnd) {
        this.breakEnd = breakEnd;
    }
}
