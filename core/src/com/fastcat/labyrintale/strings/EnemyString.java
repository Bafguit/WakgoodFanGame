package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class EnemyString {

    private final HashMap<String, CharString.CharData> data = new HashMap<>();

    public EnemyString() {
        JsonValue json = FileHandler.getJsonValue(FileHandler.JsonType.ENEMY_JSON);
        for (JsonValue js : json) {
            String id = js.name;
            if (!id.equals("")) {
                CharString.CharData data = new CharString.CharData();
                data.NAME = js.get("NAME").asString();
                data.DESC = js.get("DESC").asString();
                this.data.put(id, data);
            }
        }
    }

    public CharString.CharData get(String id) {
        return data.get(id);
    }
}
