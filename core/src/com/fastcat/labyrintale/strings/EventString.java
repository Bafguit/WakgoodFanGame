package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class EventString {

  public final HashMap<String, EventData> data = new HashMap<>();

  public EventString() {
    JsonValue json = FileHandler.getJsonValue(FileHandler.JsonType.EVENT_JSON);
    for (JsonValue js : json) {
      String id = js.name;
      if (!id.equals("")) {
        EventData data = new EventData();
        data.NAME = js.get("NAME").asString();
        data.DESC = js.get("DESC").asStringArray();
        data.SELECT = js.get("SELECT").asStringArray();
        data.IMAGE = js.get("IMAGE").asStringArray();
        this.data.put(id, data);
      }
    }
  }

  public static class EventData {
    public String NAME;
    public String[] DESC;
    public String[] SELECT;
    public String[] IMAGE;
  }
}
