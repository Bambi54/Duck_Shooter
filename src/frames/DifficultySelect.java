package frames;

import components.*;
import data.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.WindowEvent;


public class DifficultySelect extends JFrame {

    private int diff;

    public DifficultySelect(ScoreModel model){

        this.diff = -1;
        setTitle("Components.Duck shooter");


        JPanel main = new JPanel();
        main.setBackground(new Color(
                45, 86, 187,255
        ));

        main.setLayout(new BoxLayout(main, BoxLayout.Y_AXIS));



        DiffButton easy = new DiffButton(1);
        easy.putClientProperty("frame", this);

        DiffButton medium = new DiffButton(2);
        medium.putClientProperty("frame", this);

        DiffButton hard = new DiffButton(3);
        hard.putClientProperty("frame", this);


        ButtonGroup radioGrp = new ButtonGroup();
        radioGrp.add(easy);
        radioGrp.add(medium);
        radioGrp.add(hard);

        JPanel radio = new JPanel();
        radio.setOpaque(false);

        radio.add(easy);
        radio.add(medium);
        radio.add(hard);
        hard.setAlignmentX(Component.RIGHT_ALIGNMENT);

        JLabel jl = new JLabel("Choose difficulty: ");
        jl.setForeground(Color.WHITE);
        jl.setAlignmentX(Component.CENTER_ALIGNMENT);

        JButton jb = new JButton("Start game");
        jb.putClientProperty("frame", this);
        jb.addActionListener(
                e -> SwingUtilities.invokeLater(()->{

                    if(diff != -1) {
                        GameFrame gf = new GameFrame(diff, model);
                        gf.setVisible(true);
                        gf.setLocationRelativeTo(null);
                        DifficultySelect df = (DifficultySelect) jb.getClientProperty("frame");
                        df.dispatchEvent(new WindowEvent(df, WindowEvent.WINDOW_CLOSING));
                    }
                })

        );

        jb.setPreferredSize(new Dimension(200,45));
        jb.setMaximumSize(new Dimension(200,125));


        main.add(Box.createVerticalGlue());
        main.add(jl);

        main.add(Box.createVerticalGlue());

        main.add(radio);


        main.add(jb);
        main.add(Box.createVerticalGlue());
        jb.setAlignmentX(Component.CENTER_ALIGNMENT);

        add(main);

    }

    public int getDiff() {
        return diff;
    }

    public void setDiff(int diff) {
        this.diff = diff;
    }
}
