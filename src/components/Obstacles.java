package components;

import javax.swing.*;
import java.awt.*;

public enum Obstacles{
    Tree("images/log.png", 100, 60),
    Cloud("images/cloud.png", 100,100),
    Bush("images/bush.png", 80,80);

    private final ImageIcon icon;
    private final int width;
    private final int height;


    Obstacles(String s, int width, int height){
        this.width = width;
        this.height = height;
        ImageIcon icon = new ImageIcon(s);
        Image image = (icon.getImage()).getScaledInstance(width, height, Image.SCALE_AREA_AVERAGING);
        this.icon = new ImageIcon(image);
    }

    public ImageIcon getIcon() {
        return icon;
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }
}
