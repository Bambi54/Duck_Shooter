package panes;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;

public class DuckPanel extends JPanel {

    private Image image;

    public DuckPanel(){
        super(null);

        try {
            image = ImageIO.read(new File("images/background.jpg"));
            image = image.getScaledInstance(600,500,Image.SCALE_DEFAULT);
        } catch (IOException ignored) {
            System.out.println("Background image not loaded.");
        }

    }


    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image, 0, 0, null);

    }

    public void scale(int width, int height){

        image = image.getScaledInstance(width, height, Image.SCALE_DEFAULT);

    }
}
