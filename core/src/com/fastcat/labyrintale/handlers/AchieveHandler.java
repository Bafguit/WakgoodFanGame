package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.SaveHandler.mapper;

public class AchieveHandler {

    private static FileHandle data = Gdx.files.local("data/achievements.json");
    private static FileHandle aCheck = Gdx.files.local("data/checks.json");
    public static HashMap<Achievement, Integer> achvs = new HashMap<>();
    public static AchieveCheck check = new AchieveCheck();

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

        if(aCheck.exists()) {
            JsonValue js = FileHandler.generateJson(aCheck);
            JsonValue aa = js.get("ALL_ADV");
            for(AbstractAdvisor.AdvisorClass p : AbstractAdvisor.AdvisorClass.values()) {
                check.ALL_ADV.put(p, aa.get(p.toString()).asBoolean());
            }
            check.DEATH = js.get("DEATH").asInt();
            check.WIN = js.get("WIN").asInt();
        } else {
            check = new AchieveCheck();
            for(AbstractAdvisor.AdvisorClass p : AbstractAdvisor.AdvisorClass.values()) {
                check.ALL_ADV.put(p, false);
            }
            check.DEATH = 0;
            check.WIN = 0;
            new File("data").mkdir();
            try {
                mapper.writeValue(new File("data/checks.json"), check);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            mapper.writeValue(new File("data/achievements.json"), achvs);
            mapper.writeValue(new File("data/checks.json"), check);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static class AchvLabCheck {
        public boolean NO_USE_GOLD = true;
        public boolean IMMORTAL = false;
        public int REFLECT = 0;
    }

    public static class AchieveCheck {
        public HashMap<AbstractAdvisor.AdvisorClass, Boolean> ALL_ADV = new HashMap<>();
        public int DEATH;
        public int WIN;
    }

    public enum Achievement {
        COFFIN, ALL_CHAR, ALL_ADV, NO_ITEM, NO_USE_GOLD, GOLDEN, WAK,
        INE, BURGER, LILPA, GOSEGU, VIICHAN, JURURU, MANAGER,
        LAST_ONE, PURITY, REFLECT, IMMORTAL, FASTEST, DEATH, WIN
    }
}