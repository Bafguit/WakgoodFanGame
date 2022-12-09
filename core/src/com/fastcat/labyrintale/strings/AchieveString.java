package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.AchieveHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class AchieveString {

    private final HashMap<AchieveHandler.Achievement, KeyString.KeyData> data = new HashMap<>();

    public AchieveString() {
        JsonValue json = FileHandler.getJsonValue(FileHandler.JsonType.ACHV_JSON);
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                KeyString.KeyData data = new KeyString.KeyData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                this.data.put(AchieveHandler.Achievement.valueOf(id), data);
            }
        }
    }

    public KeyString.KeyData get(AchieveHandler.Achievement id) {
        return data.get(id);
    }
}
