package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.util.HashMap;

public class ChoiceString {

    private final HashMap<String, ChoiceData> data = new HashMap<>();

    public ChoiceString() {
        JsonValue json = FileHandler.CHOICE_JSON;
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                ChoiceData data = new ChoiceData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asStringArray();
                this.data.put(id, data);
            }
        }
    }

    public ChoiceData get(String id) {
        return data.get(id);
    }

    public static class ChoiceData {
        public String NAME;
        public String[] DESC;
    }
}
