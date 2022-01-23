package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;

import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class StatusString {

    public final HashMap<String, SkillData> skillData = new HashMap<>();

    public StatusString() {
        generateString(CARD_JSON_BASIC);
        generateString(CARD_JSON_WAK);
        generateString(CARD_JSON_MANAGER);
        generateString(CARD_JSON_INE);
        generateString(CARD_JSON_VIICHAN);
        generateString(CARD_JSON_LILPA);
        generateString(CARD_JSON_BASIC);
        generateString(CARD_JSON_GOSEGU);
        generateString(CARD_JSON_JURURU);
        generateString(CARD_JSON_ADV);
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
                temp = js.get("UP_DESC");
                if (temp != null) {
                    data.UP_DESC = temp.asString();
                }
                temp = js.get("EX_DESC");
                if (temp != null) {
                    data.EX_DESC = temp.asStringArray();
                }
                skillData.put(id, data);
            }
        }
    }

    public SkillData get(String id) {
        return skillData.get(id);
    }

    public static class SkillData {
        public String NAME;
        public String DESC = "";
        public String UP_DESC;
        public String[] EX_DESC;
    }
}
