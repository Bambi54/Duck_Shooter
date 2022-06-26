package panes;

import components.*;

import frames.GameFrame;

import javax.swing.*;
import java.util.ArrayList;


public class ObstaclePane extends JPanel {

    private final int count;
    ArrayList<Thread> tList = new ArrayList<>();

    public ObstaclePane(int count){
        super();
        setLayout(null);

        this.count = count;

        reload();

    }

    public void reload(){

        GameFrame g = (GameFrame) getClientProperty("frame");

        removeAll();

        Obstacle obstacle;

        int countCopy = getCount();

        int widthC = g == null ? 500 : g.getSize().width - 100;
        double heightC = (g == null ? 400 : g.getSize().height - 200) * 0.25;

        int xC = (int)(Math.random()*widthC);
        int yC = (int)(Math.random()*heightC);

        obstacle = new Obstacle(Obstacles.Cloud);

        add(g, obstacle, xC, yC);

        while (countCopy > 0){

            int width = g == null ? 500 : g.getSize().width - 100;
            int height = g == null ? 400 : g.getSize().height - 200;

            int x = (int)(Math.random()*width);
            int y = (int)(Math.random()*height);

            if(y < height*0.25){
                obstacle = new Obstacle(Obstacles.Cloud);
            } else if(y < height*0.75){
                obstacle = new Obstacle(Obstacles.Tree);
            } else {
                obstacle = new Obstacle(Obstacles.Bush);
            }

            add(g, obstacle, x, y);

            countCopy--;
        }
    }

    public void add(GameFrame g, Obstacle obstacle, int x, int y){

        obstacle.putClientProperty("frame", g);

        if(obstacle.getObstacles() == Obstacles.Cloud) {
            Thread t = new Thread(obstacle);
            t.start();
            tList.add(t);
        }

        int widthO = obstacle.getObstacles().getWidth();
        int heightO = obstacle.getObstacles().getHeight();


        obstacle.setBounds(
                x, y,
                widthO, heightO
        );


        add(obstacle);


    }

    public void kill(){
        for (Thread t : tList){
            t.interrupt();
        }
    }

    public int getCount() {
        return count;
    }

}
