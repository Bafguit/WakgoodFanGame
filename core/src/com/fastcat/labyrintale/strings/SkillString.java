package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Iterator;

public class SkillString {

    private final HashMap<String, SkillData> skillData = new HashMap<>();

    public SkillString() {
        JsonReader jsonReader = new JsonReader();
        FileHandle fileHandle = Gdx.files.internal("json/skills.json");
        InputStreamReader is = null;
        try {
            is = new InputStreamReader(fileHandle.read(), "UTF-8");
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }
        readString(jsonReader.parse(is));
    }

    private void readString(JsonValue json) {
        skillData.clear();
        for(Iterator iterator = json.iterator(); iterator.hasNext();) {
            JsonValue js = (JsonValue) iterator.next();
            String id = js.name;
            if(!id.equals("")) {
                SkillData data = new SkillData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                skillData.put(id, data);
            }
        }
    }

    public SkillData get(String id) {
        return skillData.get(id);
    }

    public class SkillData {
        public String NAME;
        public String DESC;
    }
}
