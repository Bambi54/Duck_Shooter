package components;

import frames.*;

import javax.swing.*;
import java.time.LocalTime;

public class TimerLabel extends JLabel implements Runnable{

    private LocalTime time;
    private boolean canBlue = false;
    private boolean canPurple = false;
    private boolean canRed = false;

    public TimerLabel(){

        time = LocalTime.of(0,0);
        setText(
                (time.getMinute() < 10 ? "0" + time.getMinute() : time.getMinute())
                        + ":" +
                        (time.getSecond() < 10 ? "0" + time.getSecond() : time.getSecond())
        );

    }

    @Override
    public void run() {
        while (!Thread.currentThread().isInterrupted()) {
            GameFrame g = (GameFrame) getClientProperty("frame");

            time = time.plusSeconds(1);

            if(time.getSecond() % 5 == 0) {
                canBlue = true;
                g.setResSpeed(g.getResSpeed() > 0 ? g.getResSpeed() - 100 : 0);
            }

            if(time.getSecond() % 10 == 0)
                canPurple = true;

            if(time.getSecond() % 15 == 0)
                canRed = true;


            setText((time.getMinute() < 10 ? "0" + time.getMinute() : time.getMinute())
                    + ":" +
                    (time.getSecond() < 10 ? "0" + time.getSecond() : time.getSecond()));

            try {
                Thread.sleep(1000);
            } catch (InterruptedException ignored) {
                return;
            }
        }

    }


    public boolean isCanBlue() {
        return canBlue;
    }

    public boolean isCanPurple() {
        return canPurple;
    }

    public boolean isCanRed() {
        return canRed;
    }

    public void setCanBlue(boolean canBlue) {
        this.canBlue = canBlue;
    }

    public void setCanPurple(boolean canPurple) {
        this.canPurple = canPurple;
    }

    public void setCanRed(boolean canRed) {
        this.canRed = canRed;
    }

    public LocalTime getTime() {
        return time;
    }
}
