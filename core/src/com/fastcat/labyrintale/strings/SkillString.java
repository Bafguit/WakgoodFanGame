package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class SkillString {

    private final HashMap<String, SkillData> data = new HashMap<>();

    public SkillString() {
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_BASIC));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_WAK));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_MANAGER));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_INE));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_VIICHAN));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_LILPA));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_BURGER));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_GOSEGU));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_JURURU));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_ADV));
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.CARD_JSON_ENEMY));
    }

    private void generateString(JsonValue json) {
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                SkillData data = new SkillData();
                data.NAME = js.get("NAME").asString();
                JsonValue temp = js.get("DESC");
                if (temp != null) {
                    data.DESC = temp.asString();
                }
                temp = js.get("KEY");
                if (temp != null) {
                    data.KEY = temp.asStringArray();
                }
                this.data.put(id, data);
            }
        }
    }

    public SkillData get(String id) {
        return data.get(id);
    }

    public static class SkillData {
        public String NAME;
        public String DESC = "";
        public String SUB = "";
        public String[] KEY;
    }
}
