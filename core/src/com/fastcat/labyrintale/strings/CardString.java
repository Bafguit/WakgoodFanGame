package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.JsonReader;
import com.badlogic.gdx.utils.JsonValue;

import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Iterator;

public class CardString {

    private final HashMap<String, CardData> cardData = new HashMap<>();

    public CardString() {
        JsonReader jsonReader = new JsonReader();
        FileHandle fileHandle = Gdx.files.internal("json/cards.json");
        InputStreamReader is = null;
        is = new InputStreamReader(fileHandle.read(), StandardCharsets.UTF_8);
        readString(jsonReader.parse(is));
    }

    private void readString(JsonValue json) {
        cardData.clear();
        for(Iterator iterator = json.iterator(); iterator.hasNext();) {
            JsonValue js = (JsonValue) iterator.next();
            String id = js.name;
            if(!id.equals("")) {
                CardData data = new CardData();
                data.NAME = js.get("NAME").asString();
                JsonValue temp = js.get("DESC");
                if(temp != null) {
                    data.DESC = temp.asString();
                }
                temp = js.get("UP_DESC");
                if(temp != null) {
                    data.UP_DESC = temp.asString();
                }
                temp = js.get("EX_DESC");
                if(temp != null) {
                    data.EX_DESC = temp.asStringArray();
                }
                cardData.put(id, data);
            }
        }
    }

    public CardData get(String id) {
        return cardData.get(id);
    }

    public class CardData {
        public String NAME;
        public String DESC = "";
        public String UP_DESC;
        public String[] EX_DESC;
    }
}
