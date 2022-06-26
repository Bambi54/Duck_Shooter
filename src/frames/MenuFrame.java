package frames;

import data.*;
import components.*;

import javax.swing.*;
import java.awt.*;

public class MenuFrame extends JFrame {

    private ScoreModel model;

    public MenuFrame(ScoreModel model){


        super();

        this.model = model;
        setTitle("Duck shooter");

        JPanel jpMenu = new JPanel();
        jpMenu.setPreferredSize(new Dimension(600,600));
        jpMenu.setBackground(new Color(
                45, 86, 187,255
        ));
        jpMenu.setLayout(new BoxLayout(jpMenu, BoxLayout.Y_AXIS));
        MenuButton jb1 = new MenuButton("New Game");

        MenuButton jb2 = new MenuButton("High Scores");
        MenuButton jb3 = new MenuButton("Exit");

        jb1.addActionListener(
                e -> {
                    SwingUtilities.invokeLater(()->{
                        dispose();
                        DifficultySelect df = new DifficultySelect(getModel());
                        df.setVisible(true);
                        df.setSize(new Dimension(600,600));
                        df.setLocationRelativeTo(null);
                        df.setDefaultCloseOperation(DISPOSE_ON_CLOSE);


                    });
                }
        );

        jb2.addActionListener(e->{
            SwingUtilities.invokeLater(()->{
                dispose();
                JFrame jf = new JFrame();

                jf.setVisible(true);
                jf.setSize(new Dimension(600,600));
                jf.setLocationRelativeTo(null);
                jf.setDefaultCloseOperation(EXIT_ON_CLOSE);

                JPanel jp = new JPanel();
                jp.setBackground(new Color(
                        45, 86, 187,255
                ));
                jp.setLayout(new BoxLayout(jp, BoxLayout.Y_AXIS));
                JLabel jl = new JLabel("High scores:");
                jl.setForeground(Color.WHITE);

                jp.add(jl);
                jl.setAlignmentX(Component.CENTER_ALIGNMENT);

                JList<UserData> list = new JList<>();



                if(getModel() != null) {
                    getModel().sort();
                    list.setModel(getModel());
                    list.setCellRenderer(
                            (list1, value, index, isSelected, cellHasFocus) -> {
                                JLabel jlb = new JLabel((index + 1) + ": " + value.toString());
                                jlb.setFont(new Font(Font.MONOSPACED, Font.BOLD, 15));
                                return jlb;
                            }
                    );
                }

                JScrollPane jScrollPane = new JScrollPane(list);

                jp.add(jScrollPane);
                JButton jb = new JButton("Return");
                jb.setAlignmentX(Component.CENTER_ALIGNMENT);
                jb.addActionListener(e1 -> {
                    SwingUtilities.invokeLater(
                            ()->{
                                jf.dispose();
                                MenuFrame f = new MenuFrame(getModel());
                                f.setSize(new Dimension(600,600));
                                f.setVisible(true);
                                f.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                                f.setLocationRelativeTo(null);

                            }
                    );
                });
                jp.add(jb);

                jf.add(jp);


            });
        });

        jb3.addActionListener(e -> {
            System.exit(0);
        });


        JLabel jl1 = new JLabel("Welcome in DUCK SHOOTER!");
        jl1.setForeground(Color.WHITE);
        jl1.setAlignmentX(Component.CENTER_ALIGNMENT);

        jpMenu.add(Box.createVerticalGlue());
        jpMenu.add(jl1);
        jpMenu.add(Box.createVerticalGlue());
        jpMenu.add(jb1);
        jpMenu.add(Box.createVerticalGlue());
        jpMenu.add(jb2);
        jpMenu.add(Box.createVerticalGlue());
        jpMenu.add(jb3);
        jpMenu.add(Box.createVerticalGlue());



        add(jpMenu);


    }

    public ScoreModel getModel() {
        return model;
    }

    public void setModel(ScoreModel model) {
        this.model = model;
    }

}
