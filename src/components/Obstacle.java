package components;

import frames.*;

import javax.swing.*;


public class Obstacle extends JButton implements Runnable{

    private final Obstacles obstacles;
    private boolean moveRight;

    public Obstacle(Obstacles obstacles){
        super();
        moveRight = (int)(Math.random()*2) == 0;

        this.obstacles = obstacles;
        setIcon(obstacles.getIcon());
        setOpaque(false);
        setContentAreaFilled(false);
        setBorderPainted(false);


    }

    public Obstacles getObstacles() {
        return obstacles;
    }


    @Override
    public void run() {

        try {

            while (!Thread.currentThread().isInterrupted()) {

                GameFrame g = (GameFrame) getClientProperty("frame");

                if (moveRight) {
                    setBounds(getX() + 2, getY(), getSize().width, getSize().height);

                    if (getX() >= ((g == null ? 600  : g.getSize().width)) - (getSize().width / 2))
                        setMoveRight();

                } else {
                    setBounds(getX() - 2, getY(), getSize().width, getSize().height);

                    if (getX() <= (-getSize().width / 2))
                        setMoveRight();
                }

                Thread.sleep(1000 / 30);

            }
        } catch (InterruptedException ignored){}
    }

    public void setMoveRight(){
        moveRight = !moveRight;
    }



}
