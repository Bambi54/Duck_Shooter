package components;

import frames.*;

import javax.swing.*;
import java.awt.*;


public class DiffButton extends JRadioButton {

    private final int diff;

    public DiffButton(int diff){
        super(
            switch (diff){
                case 1 ->  "easy";
                case 2 -> "medium";
                case 3 -> "hard";
                default -> null;
            }
        );
        setForeground(Color.WHITE);
        this.diff = diff;

        addActionListener(e -> {
            DifficultySelect df = (DifficultySelect) getClientProperty("frame");
            DiffButton current = (DiffButton) e.getSource();

            if(df.getDiff() != current.getDiff())
                df.setDiff(current.getDiff());

        });

        setOpaque(false);


    }

    public int getDiff() {
        return diff;
    }



}
