package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;

public class CharString {

    private final HashMap<String, CharData> data = new HashMap<>();

    public CharString() {
        JsonReader jsonReader = new JsonReader();
        FileHandle fileHandle = Gdx.files.internal("json/chars.json");
        InputStreamReader is;
        is = new InputStreamReader(fileHandle.read(), StandardCharsets.UTF_8);
        readString(jsonReader.parse(is));
    }

    private void readString(JsonValue json) {
        data.clear();
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
