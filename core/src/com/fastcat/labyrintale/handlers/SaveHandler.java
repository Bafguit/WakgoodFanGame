package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.math.RandomXS128;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractFloor;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.io.*;

public class SaveHandler {

    public static File FILE = new File("savefile.sav");
    public static boolean hasSave = false;

    public SaveHandler() {

    }

    public void save() {
        try (FileOutputStream fos = new FileOutputStream(FILE);
            ObjectOutputStream oos = new ObjectOutputStream(fos)) {
            oos.writeObject(GameData.getInstance());
            oos.flush();
        } catch(IOException e) {
            e.printStackTrace();
        }
    }

    public void load() {
        GameData result = null;
        try (FileInputStream fis = new FileInputStream(FILE);
             ObjectInputStream ois = new ObjectInputStream(fis)) {
            result = (GameData) ois.readObject();

            //TODO apply loaded data
        } catch(IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

}

class GameData implements Serializable {

    private static final long serialVersionUID = 1L;

    public String seed;
    public RandomXS128 publicRandom;
    public RandomXS128 skillRandom;
    public RandomXS128 itemRandom;
    public RandomXS128 relicRandom;
    public RandomXS128 mapRandom;
    public RandomXS128 monsterRandom;
    public RandomXS128 eventRandom;
    public RandomXS128 shopRandom;
    public AbstractFloor[] floors;
    public AbstractFloor currentFloor;
    public AbstractPlayer[] players;
    public AbstractAdvisor advisor;
    public ControlPanel cPanel;
    public int itemAble;
    public int selection;
    public int maxEnergy;
    public int energy;
    public int gold;

    public static GameData getInstance() {
        GameData data = new GameData();

        data.seed = AbstractLabyrinth.seed;
        data.publicRandom = AbstractLabyrinth.publicRandom;
        data.skillRandom = AbstractLabyrinth.skillRandom;
        data.itemRandom = AbstractLabyrinth.itemRandom;
        data.relicRandom = AbstractLabyrinth.relicRandom;
        data.mapRandom = AbstractLabyrinth.mapRandom;
        data.monsterRandom = AbstractLabyrinth.monsterRandom;
        data.eventRandom = AbstractLabyrinth.eventRandom;
        data.shopRandom = AbstractLabyrinth.shopRandom;
        data.floors = AbstractLabyrinth.floors;
        data.currentFloor = AbstractLabyrinth.currentFloor;
        data.players = AbstractLabyrinth.players;
        data.advisor = AbstractLabyrinth.advisor;
        data.cPanel = AbstractLabyrinth.cPanel;
        data.itemAble = AbstractLabyrinth.itemAble;
        data.selection = AbstractLabyrinth.selection;
        data.maxEnergy = AbstractLabyrinth.maxEnergy;
        data.energy = AbstractLabyrinth.energy;
        data.gold = AbstractLabyrinth.gold;

        return data;
    }
}
