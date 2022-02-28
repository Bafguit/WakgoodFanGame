package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class StatusString {

    private final HashMap<String, StatusData> data = new HashMap<>();

    public StatusString() {
        generateString(STATUS_JSON);
    }

    private void generateString(JsonValue json) {
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                StatusData data = new StatusData();
                data.NAME = js.get("NAME").asString();
                JsonValue temp = js.get("DESC");
                if (temp != null) {
                    data.DESC = temp.asString();
                }
                temp = js.get("DESC_B");
                if (temp != null) {
                    data.DESC_B = temp.asStringArray();
                }
                this.data.put(id, data);
            }
        }
    }

    public StatusData get(String id) {
        return data.get(id);
    }

    public static class StatusData {
        public String NAME;
        public String DESC = "";
        public String[] DESC_B;
    }
}
