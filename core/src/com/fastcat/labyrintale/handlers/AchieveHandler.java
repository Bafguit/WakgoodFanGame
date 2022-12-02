package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.SaveHandler.mapper;

public class AchieveHandler {

    private static FileHandle data = Gdx.files.local("data/achievements.json");
    public static HashMap<Achievement, Integer> achvs = new HashMap<>();

    public static void load() {
        if(data.exists()) {
            JsonValue js = FileHandler.generateJson(data);
            for(Achievement ac : Achievement.values()) {
                achvs.put(ac, js.get(ac.name()).asInt());
            }
        } else {
            achvs.clear();
            for(Achievement ac : Achievement.values()) {
                achvs.put(ac, 0);
            }
            new File("data").mkdir();
            try {
                mapper.writeValue(new File("data/achievements.json"), achvs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            mapper.writeValue(new File("data/achievements.json"), achvs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Achievement {
        LAST_ONE, PURITY, REFLECT, IMMORTAL, FIRST_ROUND, COFFIN,
        FASTEST, WAK, INE, BURGER, LILPA, GOSEGU, VIICHAN, JURURU,
        MANAGER, NO_ITEM, NO_USE_GOLD, ALL_CHAR, ALL_ADV, GOLDEN
    }


}
