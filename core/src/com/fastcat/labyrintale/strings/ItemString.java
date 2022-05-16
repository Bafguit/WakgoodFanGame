package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class ItemString {

    private final HashMap<String, ItemData> data = new HashMap<>();

    public ItemString() {
        generateString(ITEM_JSON);
    }

    private void generateString(JsonValue json) {
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                ItemData data = new ItemData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                data.FLAV = js.get("FLAV").asString();
                this.data.put(id, data);
            }
        }
    }

    public ItemData get(String id) {
        return data.get(id);
    }

    public static class ItemData {
        public String NAME;
        public String DESC = "";
        public String FLAV;
    }
}