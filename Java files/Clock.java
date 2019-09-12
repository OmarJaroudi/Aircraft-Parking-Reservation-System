package application;

import javafx.application.Platform;
import javafx.scene.control.Label;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Timer;
import java.util.TimerTask;

public class Clock {
    private String hours;
    private String minutes;
    private String dayOfMonth;
    private String dayOfWeek;
    private String amPm;
    private String year;
    private String month;

    public String getMonth() {
        return month;
    }

    public String getHours() {
        return hours;
    }

    public String getMinutes() {
        return minutes;
    }

    public String getDayOfMonth() {
        return dayOfMonth;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public String getAmPm() {
        return amPm;
    }

    public String getYear() {
        return year;
    }



    public Clock(){ 
        SimpleDateFormat sdf = new SimpleDateFormat("hh");
        if (Calendar.getInstance().get(Calendar.HOUR) <= 9)
            sdf = new SimpleDateFormat("h");
        hours = sdf.format(new Date());
        sdf = new SimpleDateFormat("mm");
        minutes = sdf.format(new Date());
        sdf = new SimpleDateFormat("dd");
        dayOfMonth = sdf.format(new Date());
        sdf = new SimpleDateFormat("EEEE");
        dayOfWeek = sdf.format(new Date());
        amPm = Calendar.getInstance().get(Calendar.AM_PM)== 0 ? "AM":"PM";
        sdf = new SimpleDateFormat("yyyy");
        year = sdf.format(new Date());
        sdf = new SimpleDateFormat("MMMMM");
        month = sdf.format(new Date());

    }
    public void startSystemClock(Label time, Label date) {
        Timer timer = new java.util.Timer();
        timer.schedule(new TimerTask() {
            public void run() {
                Platform.runLater(new Runnable() {
                    public void run() {
                        Clock clock = new Clock();
                        time.setText(clock.getTimeString());
                        date.setText(clock.getDateString());
                    }
                });
            }
        },1000,1000 );

    }
    public String getTimeString(){
        return getHours()+":"+getMinutes()+" "+getAmPm();
    }
    public String getDateString(){
        return getDayOfWeek()+", "+getMonth()+" "+getDayOfMonth()+", "+getYear();
    }
}