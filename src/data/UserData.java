package data;

import java.io.Serializable;
import java.time.LocalTime;

public class UserData implements Serializable {

    private final String nick;
    private final int highScore;
    private final LocalTime time;
    private final int diff;

    public UserData(String nick, int highScore, LocalTime time, int diff) {
        this.nick = nick;
        this.highScore = highScore;
        this.time = time;
        this.diff = diff;
    }

    @Override
    public String toString() {
        return nick + " | " +
                "score = " + highScore +
                ", time = " +
                    (time.getMinute() < 10 ? "0" + time.getMinute() : time.getMinute())
                    + ":" +
                    (time.getSecond() < 10 ? "0" + time.getSecond() : time.getSecond()) +

                ", difficulty = " +
                switch (diff) {
                    case 1 -> "easy";
                    case 2 -> "medium";
                    case 3 -> "hard";
                    default -> "";
                };

    }


    public int getHighScore() {
        return highScore;
    }

    public LocalTime getTime() {
        return time;
    }

    public int getDiff() {
        return diff;
    }
}

