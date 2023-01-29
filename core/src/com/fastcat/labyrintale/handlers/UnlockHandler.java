package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import java.io.IOException;
import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.SaveHandler.mapper;

public class UnlockHandler {

    public static HashMap<Unlocks, HashMap<String, Boolean>> unlocks = new HashMap<>();

    public static void load() {
        if(InputHandler.isDesktop) loadDesktop();
        else loadAndroid();
        save();
    }

    public static void save() {
        if(InputHandler.isDesktop) saveDesktop();
        else saveAndroid();
    }

    private static void loadAndroid() {
        FileHandle data = Gdx.files.local("data/unlocks.json");
        if (data.exists()) {
            JsonValue js = FileHandler.generateJson(data);
            for (Unlocks ac : Unlocks.values()) {
                HashMap<String, Boolean> temp = new HashMap<>();
                for (JsonValue jj : js.get(ac.name())) {
                    temp.put(jj.name, jj.asBoolean());
                }
                unlocks.put(ac, temp);
            }
        } else {
            unlocks.clear();
            HashMap<String, Boolean> diff = new HashMap<>();
            diff.put("NORMAL", true);
            diff.put("HARD", false);
            diff.put("COFFIN", false);
            unlocks.put(Unlocks.DIFF, diff);
            HashMap<String, Boolean> skill = new HashMap<>();
            for (AbstractPlayer.PlayerClass p : AbstractPlayer.PlayerClass.values()) {
                for (AbstractSkill s : GroupHandler.SkillGroup.normalSkills.get(p)) {
                    skill.put(s.id, false);
                }
            }
            unlocks.put(Unlocks.SKILL, skill);
            HashMap<String, Boolean> item = new HashMap<>();
            for (AbstractItem i : GroupHandler.ItemGroup.allItem) {
                if (!i.id.equals("Placeholder")) item.put(i.id, false);
            }
            unlocks.put(Unlocks.ITEM, item);
            Gdx.files.local("data").mkdirs();
        }
    }

    private static void loadDesktop() {
        Preferences prefs = Gdx.app.getPreferences("WakestDungeon_Unlocks");
        FileHandle data = Gdx.files.local("data/unlocks.json");
        if (data.exists()) {
            JsonValue js = FileHandler.generateJson(data);
            for (Unlocks ac : Unlocks.values()) {
                HashMap<String, Boolean> temp = new HashMap<>();
                for (JsonValue jj : js.get(ac.name())) {
                    temp.put(jj.name, jj.asBoolean());
                }
                unlocks.put(ac, temp);
            }
            data.delete();
        } else {
            unlocks.clear();

            HashMap<String, Boolean> diff = new HashMap<>();
            diff.put("NORMAL", prefs.getBoolean("DIFF_NORMAL", true));
            diff.put("HARD", prefs.getBoolean("DIFF_HARD", false));
            diff.put("COFFIN", prefs.getBoolean("DIFF_COFFIN", false));
            unlocks.put(Unlocks.DIFF, diff);

            HashMap<String, Boolean> skill = new HashMap<>();
            for (AbstractPlayer.PlayerClass p : AbstractPlayer.PlayerClass.values()) {
                for (AbstractSkill s : GroupHandler.SkillGroup.normalSkills.get(p)) {
                    skill.put(s.id, prefs.getBoolean("SKILL_" + s.id, false));
                }
            }
            unlocks.put(Unlocks.SKILL, skill);

            HashMap<String, Boolean> item = new HashMap<>();
            for (AbstractItem i : GroupHandler.ItemGroup.allItem) {
                if (!i.id.equals("Placeholder")) item.put(i.id, prefs.getBoolean("ITEM_" + i.id, false));
            }
            unlocks.put(Unlocks.ITEM, item);
        }
    }

    private static void saveAndroid() {
        try {
            mapper.writeValue(Gdx.files.local("data/unlocks.json").file(), unlocks);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private static void saveDesktop() {
        Preferences prefs = Gdx.app.getPreferences("WakestDungeon_Unlocks");
        for(Unlocks u : unlocks.keySet()) {
            HashMap<String, Boolean> map = unlocks.get(u);
            for(String s : map.keySet()) {
                prefs.putBoolean(u.name() + "_" + s, map.get(s));
            }
        }
        prefs.flush();
    }

    public enum Unlocks {
        DIFF,
        SKILL,
        ITEM
    }
}
