package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class ItemString {

    private final HashMap<String, SkillString.SkillData> data = new HashMap<>();

    public ItemString() {
        generateString(FileHandler.getJsonValue(FileHandler.JsonType.ITEM_JSON));
    }

    private void generateString(JsonValue json) {
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                SkillString.SkillData data = new SkillString.SkillData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                JsonValue t = js.get("SUB");
                if (t != null) {
                    data.SUB = t.asString();
                }
                JsonValue temp = js.get("KEY");
                if (temp != null) {
                    data.KEY = temp.asStringArray();
                }
                this.data.put(id, data);
            }
        }
    }

    public SkillString.SkillData get(String id) {
        return data.get(id);
    }
}
