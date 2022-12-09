package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class KeyString {

    private final HashMap<String, KeyData> data = new HashMap<>();

    public KeyString() {
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.KEY_JSON));
    }

    private void generateString(JsonValue json) {
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                KeyData data = new KeyData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                this.data.put(id, data);
            }
        }
    }

    public KeyData get(String id) {
        return data.get(id);
    }

    public static class KeyData {
        public String NAME;
        public String DESC;
    }
}
