package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.util.HashMap;

public class RiskString {

    private final HashMap<String, RiskData> data = new HashMap<>();

    public RiskString() {
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.RISK_JSON));
    }

    private void generateString(JsonValue json) {
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                RiskData data = new RiskData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asStringArray();
                this.data.put(id, data);
            }
        }
    }

    public RiskData get(String id) {
        return data.get(id);
    }

    public static class RiskData {
        public String NAME;
        public String[] DESC;
    }
}
