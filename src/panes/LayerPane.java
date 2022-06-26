package panes;

import javax.swing.*;

public class LayerPane extends JLayeredPane {

    private final DuckPanel d;
    private final ObstaclePane o;

    public LayerPane(DuckPanel d, ObstaclePane o){
        super();
        this.d = d;
        this.o = o;

        d.setOpaque(true);

        o.setOpaque(false);

        setLayerPane(600,500);

        add(d, 0,0);
        add(o,1,0);

    }

    public void setLayerPane(int width, int height ){
        d.setBounds(0,0,width, height);
        o.setBounds(0,0,width,height);
    }

}
