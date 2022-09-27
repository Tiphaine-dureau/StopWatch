package Model;

public class TimerModel {
    //Les variables qui vont permettre de formater ce temps en format : hh : mm :ss : mili
    long hour;
    long minute;
    long second;
    long milisecond;

    public long getHour() {
        return hour;
    }

    public void setHour(long hour) {
        this.hour = hour;
    }

    public long getMinute() {
        return minute;
    }

    public void setMinute(long minute) {
        this.minute = minute;
    }

    public long getSecond() {
        return second;
    }

    public void setSecond(long second) {
        this.second = second;
    }

    public long getMilisecond() {
        return milisecond;
    }

    public void setMilisecond(long milisecond) {
        this.milisecond = milisecond;
    }
}
