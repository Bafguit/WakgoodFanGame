package com.fastcat.labyrintale.handlers;

import static com.fastcat.labyrintale.handlers.SaveHandler.mapper;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import java.io.IOException;
import java.util.HashMap;

public class AchieveHandler {

    public static HashMap<Achievement, Integer> achvs = new HashMap<>();
    public static AchieveCheck check = new AchieveCheck();

    public static void load() {
        Preferences av = Gdx.app.getPreferences("WakestDungeon_Achievements");
        FileHandle data = Gdx.files.local("data/achievements.json");
        if (data.exists()) {
            JsonValue js = FileHandler.generateJson(data);
            for (Achievement ac : Achievement.values()) {
                achvs.put(ac, av.getInteger(ac.name(), js.get(ac.name()).asInt()));
            }
            data.delete();
        } else {
            achvs.clear();
            for (Achievement ac : Achievement.values()) {
                achvs.put(ac, av.getInteger(ac.name(), 0));
            }
        }

        Preferences ch = Gdx.app.getPreferences("WakestDungeon_Checks");
        FileHandle aCheck = Gdx.files.local("data/checks.json");
        if (aCheck.exists()) {
            JsonValue js = FileHandler.generateJson(aCheck);
            JsonValue aa = js.get("ALL_ADV");
            for (int i = 0; i < AbstractAdvisor.AdvisorClass.values().length - 3; i++) {
                AbstractAdvisor.AdvisorClass p = AbstractAdvisor.AdvisorClass.values()[i];
                check.ALL_ADV.put(p, ch.getBoolean(p.name(), aa.get(p.name()).asBoolean()));
            }
            check.DEATH = ch.getInteger("DEATH", js.get("DEATH").asInt());
            check.WIN = ch.getInteger("WIN", js.get("WIN").asInt());
            aCheck.delete();
        } else {
            check = new AchieveCheck();
            for (int i = 0; i < AbstractAdvisor.AdvisorClass.values().length - 3; i++) {
                AbstractAdvisor.AdvisorClass p = AbstractAdvisor.AdvisorClass.values()[i];
                check.ALL_ADV.put(p, ch.getBoolean(p.name(), false));
            }
            check.DEATH = ch.getInteger("DEATH", 0);
            check.WIN = ch.getInteger("WIN", 0);
        }
        save();
    }

    public static void save() {
        Preferences av = Gdx.app.getPreferences("WakestDungeon_Achievements");
        for (Achievement ac : Achievement.values()) {
            av.putInteger(ac.name(), achvs.get(ac));
        }
        av.flush();

        Preferences ch = Gdx.app.getPreferences("WakestDungeon_Checks");
        for (int i = 0; i < AbstractAdvisor.AdvisorClass.values().length - 3; i++) {
            AbstractAdvisor.AdvisorClass p = AbstractAdvisor.AdvisorClass.values()[i];
            ch.putBoolean(p.name(), check.ALL_ADV.get(p));
        }
        ch.putInteger("DEATH", check.DEATH);
        ch.putInteger("WIN", check.WIN);
        ch.flush();
    }

    public enum Achievement {
        COFFIN,
        ALL_CHAR,
        ALL_ADV,
        NO_ITEM,
        NO_USE_GOLD,
        GOLDEN,
        WAK,
        INE,
        BURGER,
        LILPA,
        GOSEGU,
        VIICHAN,
        JURURU,
        MANAGER,
        LAST_ONE,
        PURITY,
        REFLECT,
        IMMORTAL,
        FASTEST,
        DEATH,
        WIN
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
}
