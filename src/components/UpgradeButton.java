package components;

import javax.swing.*;

public class UpgradeButton extends JButton {

    private int upCost;

    public UpgradeButton(String s, int upCost){
        super(s + upCost);

        this.upCost = upCost;

    }

    public int getUpCost() {
        return upCost;
    }

    public void setUpCost(int upCost) {
        this.upCost = upCost;
    }
}
