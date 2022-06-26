package components;

import javax.swing.*;
import java.awt.*;

public class MenuButton extends JButton {

    public MenuButton(String s){
        super(s);
        setPreferredSize(new Dimension(200,45));
        setMaximumSize(new Dimension(200,125));
        setAlignmentX(Box.CENTER_ALIGNMENT);
        setAlignmentY(Box.CENTER_ALIGNMENT);

    }


}
