package frames;

import components.*;
import data.*;
import panes.*;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.ArrayList;

public class GameFrame extends JFrame {

    private final JLabel health;
    private final User u1;
    private final DuckPanel duckP;
    private int resSpeed;
    private final int diff;
    private final ScoreModel model;


    public GameFrame(int diff, ScoreModel model){
        super();
        setTitle("Components.Duck shooter");
        this.diff = diff;
        this.resSpeed = 3000/diff;
        this.model = model;

        setSize(new Dimension(600,600));

        JPanel jpMain = new JPanel();
        jpMain.setLayout(new BorderLayout());

        JPanel buttonP = new JPanel();
        buttonP.setPreferredSize(new Dimension(300,100));
        buttonP.setLayout(new BorderLayout());

        jpMain.add(buttonP, BorderLayout.NORTH);
        DuckPanel duckP = new DuckPanel();
        this.duckP = duckP;
        ObstaclePane obstaclePane = new ObstaclePane(5);
        LayerPane layerPane = new LayerPane(duckP, obstaclePane);
        obstaclePane.putClientProperty("frame", this);
        duckP.putClientProperty("frame", this);

        KeyStroke keyStroke = KeyStroke.getKeyStroke(KeyEvent.VK_Q, KeyEvent.SHIFT_DOWN_MASK + KeyEvent.CTRL_DOWN_MASK);
        duckP.getInputMap(JComponent.WHEN_IN_FOCUSED_WINDOW).put(keyStroke, "close");
        duckP.getActionMap().put("close", new AbstractAction() {
            @Override
            public void actionPerformed(ActionEvent e) {
                GameFrame f = (GameFrame) duckP.getClientProperty("frame");
                f.dispatchEvent(new WindowEvent(f, WindowEvent.WINDOW_CLOSING));
            }
        });

        duckP.setBackground(Color.BLUE);

        jpMain.add(layerPane);

        User u1 = new User();
        this.u1 = u1;

        Color c = new Color(
                200, 227, 31,255
        );

        JPanel upgradeP = new JPanel();
        upgradeP.setBackground(c);

        upgradeP.setLayout(new BoxLayout(upgradeP, BoxLayout.Y_AXIS));

        JLabel pow = new JLabel("Current power: " + u1.getPower());
        pow.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel mon = new JLabel("Current score multiplier: " + u1.getMoney());
        mon.setAlignmentX(Component.LEFT_ALIGNMENT);

        JLabel slow = new JLabel("Shot's slow value: " + u1.getSlow());
        slow.setAlignmentX(Component.LEFT_ALIGNMENT);

        upgradeP.add(pow);
        upgradeP.add(Box.createVerticalGlue());
        upgradeP.add(mon);
        upgradeP.add(Box.createVerticalGlue());
        upgradeP.add(slow);


        JPanel labelP = new JPanel();
        labelP.setBackground(c);
        labelP.setLayout(new BoxLayout(labelP,BoxLayout.Y_AXIS));

        TimerLabel timer = new TimerLabel();
        timer.putClientProperty("frame", this);
        timer.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel diffL = new JLabel("Difficulty: " +
                switch (diff){
                    case 1 -> "easy";
                    case 2 -> "medium";
                    case 3 -> "hard";
                    default -> null;
                }
        );
        diffL.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel totalPoints = new JLabel("Total Score: 0");
        totalPoints.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel bank = new JLabel("Bank: 0");

        bank.setAlignmentX(Component.CENTER_ALIGNMENT);

        JLabel health = new JLabel("Health: " + u1.getHealth());
        this.health = health;

        health.setAlignmentX(Component.CENTER_ALIGNMENT);

        labelP.add(timer);
        labelP.add(Box.createVerticalGlue());
        labelP.add(diffL);
        labelP.add(Box.createVerticalGlue());
        labelP.add(totalPoints);
        labelP.add(Box.createVerticalGlue());
        labelP.add(bank);
        labelP.add(Box.createVerticalGlue());
        labelP.add(health);



        JPanel upgrades = new JPanel();
        upgrades.setBackground(c);
        upgrades.setLayout(new BoxLayout(upgrades,BoxLayout.Y_AXIS));
        UpgradeButton powerUP = new UpgradeButton("Power Upgrade: " ,300);
        UpgradeButton moneyUP = new UpgradeButton("Money per duck upgrade: ",200);
        UpgradeButton slowUP = new UpgradeButton("Slowing shots: ",500);

        powerUP.addActionListener(e -> {
            if(u1.getBank() < powerUP.getUpCost()) {
                System.out.println("Insufficient funds!");
                return;
            }

            u1.setPower(u1.getPower() + 0.5);
            u1.setBank(u1.getBank() - powerUP.getUpCost());
            powerUP.setUpCost(powerUP.getUpCost() * 2);
            powerUP.setText("Power Upgrade: " + powerUP.getUpCost());
            bank.setText("Bank: " + u1.getBank());
            pow.setText("Current power: " + u1.getPower());
        });

        moneyUP.addActionListener(e -> {
            if(u1.getBank() < moneyUP.getUpCost()) {
                System.out.println("Insufficient funds!");
                return;
            }
            u1.setMoney(u1.getMoney() + 0.2);
            u1.setBank(u1.getBank() - moneyUP.getUpCost());
            moneyUP.setUpCost(moneyUP.getUpCost() * 2);
            moneyUP.setText("Money per duck upgrade: " + moneyUP.getUpCost());
            bank.setText("Bank: " + u1.getBank());
            mon.setText("Current score multiplier: " + u1.getMoney());

        });


        slowUP.addActionListener(e -> {
            if(u1.getSlow() == 0.75){
                System.out.println("Slowing shots already acquired.");
                return;
            }
            if(u1.getBank() < moneyUP.getUpCost()) {
                System.out.println("Insufficient funds!");
                return;
            }

            u1.setSlow(0.75);
            u1.setBank(u1.getBank() - slowUP.getUpCost());
            slowUP.setText("Slowing shots acquired!");
            bank.setText("Bank: " + u1.getBank());
            slow.setText("Shot's slow value: " + u1.getSlow());

        });

        upgrades.add(Box.createVerticalGlue());
        upgrades.add(powerUP);
        upgrades.add(Box.createVerticalGlue());
        upgrades.add(moneyUP);
        upgrades.add(Box.createVerticalGlue());
        upgrades.add(slowUP);
        upgrades.add(Box.createVerticalGlue());

        buttonP.add(upgradeP,BorderLayout.LINE_START);

        buttonP.add(labelP, BorderLayout.CENTER);

        buttonP.add(upgrades, BorderLayout.LINE_END);

        add(jpMain);

        Thread t = new Thread(() -> {

            Thread t2 = new Thread(timer);
            t2.start();

            ArrayList<Thread> tList = new ArrayList<>();
            tList.add(t2);


            try {

                while (!Thread.currentThread().isInterrupted()) {

                    boolean canBlue = timer.isCanBlue();
                    boolean canPurple = timer.isCanPurple();
                    boolean canRed = timer.isCanRed();

                    ArrayList<Duck.DuckColor> arr = new ArrayList<>();
                    arr.add(Duck.DuckColor.Yellow);
                    arr.add(Duck.DuckColor.Green);

                    if(canBlue)
                        arr.add(Duck.DuckColor.Blue);

                    if(canPurple)
                        arr.add(Duck.DuckColor.Purple);

                    if(canRed)
                        arr.add(Duck.DuckColor.Red);


                    boolean moveright = (int) (Math.random() * 2) == 1;

                    int color = (int)(Math.random() * arr.size());

                    Duck d = new Duck(arr.get(color), moveright);

                    switch (arr.get(color)){
                        case Blue -> timer.setCanBlue(false);
                        case Purple -> timer.setCanPurple(false);
                        case Red -> timer.setCanRed(false);
                    }

                    d.putClientProperty("frame", this);

                    duckP.add(d);
                    Thread t1 = new Thread(d);
                    t1.start();


                    d.addActionListener(e -> {
                        double power = u1.getPower();
                        double money = u1.getMoney();
                        double slowVal = u1.getSlow();

                        d.setHp(d.getHp() - power);

                        if(!d.isSlowed() && slowVal != 1){
                            d.setSpeed(d.getSpeed()* slowVal);
                            d.setSlowed(true);
                        }

                        if(d.getHp() <= 0) {
                            int score = (int)(d.getScore()*money);
                            u1.setBank(u1.getBank() + score);
                            bank.setText("Bank: " + u1.getBank());
                            u1.setTotalPoints(u1.getTotalPoints() + score);
                            totalPoints.setText("Total Score: " + u1.getTotalPoints());

                            t1.interrupt();
                        }

                    });

                    tList.add(t1);

                    int sleep = (int)(Math.random() * (resSpeed)) + 300;

                    Thread.sleep(sleep);

                }
            } catch (InterruptedException e){
                for(Thread t3 : tList){
                    t3.interrupt();
                }
            }
        });

        t.start();

        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                dispose();
                t.interrupt();
                obstaclePane.kill();



                SwingUtilities.invokeLater(()->{
                    JFrame f = new JFrame();
                    f.setVisible(true);
                    f.setSize(new Dimension(400, 400));
                    f.setLocationRelativeTo(null);
                    f.setDefaultCloseOperation(EXIT_ON_CLOSE);
                    Color c = new Color(
                            45, 86, 187,255
                    );

                    JPanel jPanel = new JPanel();
                    jPanel.setBackground(c);
                    jPanel.setVisible(true);
                    jPanel.setLayout(new BoxLayout(jPanel, BoxLayout.Y_AXIS));

                    jPanel.add(Box.createVerticalGlue());

                    JLabel jl = new JLabel("Type username:");
                    jl.setForeground(Color.WHITE);
                    jl.setAlignmentX(Component.CENTER_ALIGNMENT);
                    jPanel.add(jl);
                    jPanel.add(Box.createVerticalGlue());


                    JTextArea jt = new JTextArea();

                    jt.setOpaque(false);
                    jt.setForeground(Color.WHITE);
                    jt.setLineWrap(true);

                    JPanel areaPane = new JPanel();
                    areaPane.setBackground(c);
                    areaPane.setMaximumSize(new Dimension(400,50));
                    areaPane.add(jt);

                    jPanel.add(areaPane);
                    jPanel.add(Box.createVerticalGlue());

                    JButton jb = new JButton("Zapisz");

                    jb.addActionListener(e1 -> {
                        f.dispose();
                        UserData data = new UserData(
                                jt.getText(),
                                u1.getTotalPoints(),
                                timer.getTime(),
                                getDiff()
                        );


                        SwingUtilities.invokeLater(()->{
                            ScoreModel model1 = getModel();
                            if(model1 == null){
                                model1 = new ScoreModel();
                            }
                            model1.addElement(data);
                            MenuFrame menuFrame = new MenuFrame(model1);
                            menuFrame.setSize(new Dimension(600,600));
                            menuFrame.setVisible(true);
                            menuFrame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
                            menuFrame.setLocationRelativeTo(null);



                        });

                    });

                    jb.setAlignmentX(Component.CENTER_ALIGNMENT);
                    jPanel.add(jb);
                    jPanel.add(Box.createVerticalGlue());


                    f.add(jPanel);

                });

            }
        });

        addComponentListener(new ComponentAdapter() {
            @Override
            public void componentResized(ComponentEvent e) {
                GameFrame g = (GameFrame)e.getSource();
                int width = g.getSize().width;
                int height = g.getSize().height;

                layerPane.setLayerPane(
                        width, height - buttonP.getSize().height
                );
                duckP.scale(width, height);
                obstaclePane.reload();

            }
        });

    }

    public void disappear(){
        u1.setHealth(u1.getHealth() - 1);
        health.setText("Health: " + u1.getHealth());
        duckP.updateUI();

        if(u1.getHealth() <= 0){
            this.dispatchEvent(new WindowEvent(this, WindowEvent.WINDOW_CLOSING));
        }
    }


    public int getResSpeed() {
        return resSpeed;
    }

    public void setResSpeed(int resSpeed) {
        this.resSpeed = resSpeed;
    }

    public int getDiff() {
        return diff;
    }

    public ScoreModel getModel() {
        return model;
    }
}
