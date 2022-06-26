package frames;

import data.*;

import javax.swing.*;
import java.awt.*;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;

public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(
                ()->{
                    ScoreModel model = null;
                    try {
                        FileInputStream fo = new FileInputStream("files/high_scores.bin");
                        ObjectInputStream o = new ObjectInputStream(fo);
                        model = (ScoreModel) o.readObject();
                        model.sort();
                        o.close();
                        System.out.println("high scores read successfully");

                    } catch (IOException | ClassNotFoundException ignored) {}

                    MenuFrame f = new MenuFrame(model);
                    f.setSize(new Dimension(600,600));
                    f.setVisible(true);
                    f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                    f.setLocationRelativeTo(null);

                }
        );
    }
}
