package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.util.HashMap;

public class CreditString {

  public final HashMap<String, CreditData> data = new HashMap<>();

  public CreditString() {
    JsonValue json = FileHandler.getJsonValue(FileHandler.JsonType.CREDIT_JSON);
    for (JsonValue js : json) {
      String id = js.name;
      if (!id.equals("")) {
        CreditData data = new CreditData();
        data.TYPE = js.get("TYPE").asString();
        data.NAME = js.get("NAME").asStringArray();
        this.data.put(id, data);
      }
    }
  }

  public static class CreditData {
    public String TYPE;
    public String[] NAME;
  }
}
