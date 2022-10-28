package com.fastcat.labyrintale.strings;

import com.badlogic.gdx.utils.JsonValue;
import com.fastcat.labyrintale.handlers.FileHandler;
import java.util.HashMap;

public class AdvisorString {

  private final HashMap<String, SkillString.SkillData> data = new HashMap<>();

  public AdvisorString() {
    JsonValue json = FileHandler.getJsonValue(FileHandler.JsonType.ADV_JSON);
    for (JsonValue js : json) {
      String id = js.name;
      if (!id.equals("")) {
        SkillString.SkillData data = new SkillString.SkillData();
        data.NAME = js.get("NAME").asString();
        data.DESC = js.get("DESC").asString();
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
