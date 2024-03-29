package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class CharString {

    private final HashMap<String, CharData> data = new HashMap<>();

    public CharString() {
        JsonValue json = FileHandler.getJsonValue(FileHandler.JsonType.CHAR_JSON);
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                CharData data = new CharData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                this.data.put(id, data);
            }
        }
    }

    public CharData get(String id) {
        return data.get(id);
    }

    public static class CharData {
        public String NAME;
        public String DESC = "";
    }
}
