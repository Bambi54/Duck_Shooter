package data;

public class User {

    private int totalPoints;
    private int health;
    private int bank;
    private double power;
    private double money;
    private double slow;

    public User(){
        this.bank = 0;
        this.totalPoints = 0;
        this.power = 1;
        this.money = 1;
        this.slow = 1;
        this.health = 10;

    }

    public int getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(int totalPoints) {
        this.totalPoints = totalPoints;
    }

    public int getHealth() {
        return health;
    }

    public void setHealth(int health) {
        this.health = health;
    }

    public int getBank() {
        return bank;
    }

    public void setBank(int bank) {
        this.bank = bank;
    }

    public double getPower() {
        return power;
    }

    public void setPower(double power) {
        this.power = power;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public double getSlow() {
        return slow;
    }

    public void setSlow(double slow) {
        this.slow = slow;
    }
}
