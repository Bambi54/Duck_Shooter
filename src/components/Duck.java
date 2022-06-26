package components;

import frames.*;

import javax.swing.*;
import java.awt.*;

public class Duck extends JButton implements Runnable {

    public enum DuckColor {
        Yellow, Green, Blue, Purple, Red
    }

    private double hp;
    private int score;
    private boolean slowed;
    private double speed;
    private final boolean moveRight;

    public Duck(DuckColor color, boolean moveRight){
        super();
        setPreferredSize(new Dimension(60,50));
        this.slowed = false;
        this.moveRight = moveRight;

        switch (color){
            case Yellow -> {
                this.speed = 3;
                this.hp = 1;
                this.score = 10;
                ImageIcon icon = new ImageIcon(isMoveRight() ? "images/yellow_right.png" : "images/yellow_left.png");
                Image image = (icon.getImage()).getScaledInstance(75, -1, Image.SCALE_AREA_AVERAGING);
                setIcon(new ImageIcon(image));

            }
            case Green -> {
                this.speed = 2.5;
                this.hp = 2;
                this.score = 25;
                ImageIcon icon = new ImageIcon(isMoveRight() ? "images/green_right.png" : "images/green_left.png");
                Image image = (icon.getImage()).getScaledInstance(75, -1, Image.SCALE_AREA_AVERAGING);
                setIcon(new ImageIcon(image));

            }
            case Blue -> {
                this.speed = 4;
                this.hp = 3;
                this.score = 50;
                ImageIcon icon = new ImageIcon(isMoveRight() ? "images/blue_right.png" : "images/blue_left.png");
                Image image = (icon.getImage()).getScaledInstance(75, -1, Image.SCALE_AREA_AVERAGING);
                setIcon(new ImageIcon(image));
            }
            case Purple -> {
                this.speed = 3;
                this.hp = 4;
                this.score = 50;
                ImageIcon icon = new ImageIcon(isMoveRight() ? "images/purple_right.png" : "images/purple_left.png");
                Image image = (icon.getImage()).getScaledInstance(75, -1, Image.SCALE_AREA_AVERAGING);
                setIcon(new ImageIcon(image));
            }
            case Red -> {
                this.speed = 5;
                this.hp = 2;
                this.score = 100;
                ImageIcon icon = new ImageIcon(isMoveRight() ? "images/red_right.png" : "images/red_left.png");
                Image image = (icon.getImage()).getScaledInstance(75, -1, Image.SCALE_AREA_AVERAGING);
                setIcon(new ImageIcon(image));
            }
        }

        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);

    }

    @Override
    public void run() {

        GameFrame jf = (GameFrame) getClientProperty("frame");

        int bound = jf.getSize().width;

        int y = jf.getSize().height == 0 ?
                (int)(Math.random()*401) :
                (int)(Math.random()*(jf.getSize().height - 200));


        if(moveRight) {
            setBounds(
                    -getPreferredSize().width, y, getPreferredSize().width, getPreferredSize().height
            );
        } else {
            setBounds(
                    bound, y, getPreferredSize().width, getPreferredSize().height
            );
        }

        try {

            while (!Thread.currentThread().isInterrupted()) {

                bound = jf.getSize().width;

                if (moveRight) {
                    setBounds(getX() + (int)speed, getY(), getSize().width, getSize().height);

                    if (getX() >= bound) {

                        jf.disappear();

                        break;
                    }

                } else {
                    setBounds(getX() - (int)speed, getY(), getSize().width, getSize().height);

                    if (getX() <= -getSize().width) {

                        jf.disappear();

                        break;
                    }
                }

                Thread.sleep(1000/60);


            }
        } catch (InterruptedException ignored){}

        setVisible(false);

    }

    public boolean isMoveRight() {
        return moveRight;
    }

    public void setSpeed(double speed) {
        this.speed = speed;
    }

    public double getSpeed() {
        return speed;
    }

    public boolean isSlowed() {
        return slowed;
    }

    public void setSlowed(boolean slowed) {
        this.slowed = slowed;
    }

    public double getHp() {
        return hp;
    }

    public void setHp(double hp) {
        this.hp = hp;
    }

    public int getScore() {
        return score;
    }

}
