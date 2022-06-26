package data;

import javax.swing.*;
import java.io.*;
import java.util.ArrayList;

public class ScoreModel extends AbstractListModel<UserData> implements Serializable {

    private final ArrayList<UserData> arr;

    public ScoreModel(){
        this.arr = new ArrayList<>();
    }

    @Override
    public int getSize() {
        return arr.size();
    }

    @Override
    public UserData getElementAt(int index) {
        return arr.get(index);
    }

    public void addElement(UserData userData){
        arr.add(userData);
        save();
    }

    public void sort(){
        arr.sort((o1, o2) -> o1.getHighScore() == o2.getHighScore() ?
                o1.getTime().equals(o2.getTime()) ?
                        o2.getDiff() - o1.getDiff() :
                        o1.getTime().compareTo(o2.getTime()) :
                o2.getHighScore() - o1.getHighScore());
    }

    public void save(){
        try {
            FileOutputStream fo = new FileOutputStream("files/high_scores.bin");
            ObjectOutputStream o = new ObjectOutputStream(fo);
            o.writeObject(this);
            o.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
