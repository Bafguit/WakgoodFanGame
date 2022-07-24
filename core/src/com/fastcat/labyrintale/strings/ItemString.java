package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.util.HashMap;

public class ItemString {

    private final HashMap<String, ItemData> data = new HashMap<>();

    public ItemString() {
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.ITEM_JSON));
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
