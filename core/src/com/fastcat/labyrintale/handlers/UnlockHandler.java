package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import java.io.File;
import java.io.IOException;
import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.SaveHandler.mapper;

public class UnlockHandler {

    private static FileHandle data = Gdx.files.local("data/unlocks.json");
    public static HashMap<Unlocks, HashMap<String, Boolean>> achvs = new HashMap<>();

    public static void load() {
        if(data.exists()) {
            JsonValue js = FileHandler.generateJson(data);
            for(Unlocks ac : Unlocks.values()) {
                HashMap<String, Boolean> temp = new HashMap<>();
                for(JsonValue jj : js.get(ac.name())) {
                    temp.put(jj.name, jj.asBoolean());
                }
                achvs.put(ac, temp);
            }
        } else {
            achvs.clear();
            HashMap<String, Boolean> diff = new HashMap<>();
            diff.put("NORMAL", true);
            diff.put("HARD", false);
            diff.put("COFFIN", false);
            achvs.put(Unlocks.DIFF, diff);
            HashMap<String, Boolean> skill = new HashMap<>();
            for(AbstractPlayer.PlayerClass p : AbstractPlayer.PlayerClass.values()) {
                for (AbstractSkill s : GroupHandler.SkillGroup.normalSkills.get(p)) {
                    skill.put(s.id, false);
                }
            }
            achvs.put(Unlocks.SKILL, skill);
            HashMap<String, Boolean> item = new HashMap<>();
            for (AbstractItem i : GroupHandler.ItemGroup.allItem) {
                if(!i.id.equals("Placeholder")) item.put(i.id, false);
            }
            achvs.put(Unlocks.ITEM, item);
            new File("data").mkdir();
            try {
                mapper.writeValue(new File("data/unlocks.json"), achvs);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public static void save() {
        try {
            mapper.writeValue(new File("data/unlocks.json"), achvs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public enum Unlocks {
        DIFF, SKILL, ITEM
    }
}
