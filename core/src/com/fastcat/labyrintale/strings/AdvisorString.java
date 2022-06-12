package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.util.HashMap;

public class AdvisorString {

    private final HashMap<String, AdvisorData> data = new HashMap<>();

    public AdvisorString() {
        JsonValue json = FileHandler.ADV_JSON;
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                AdvisorData data = new AdvisorData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                this.data.put(id, data);
            }
        }
    }

    public AdvisorData get(String id) {
        return data.get(id);
    }

    public static class AdvisorData {
        public String NAME;
        public String DESC = "";
    }
}
