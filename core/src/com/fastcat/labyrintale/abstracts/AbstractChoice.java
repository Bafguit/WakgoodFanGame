package com.fastcat.labyrintale.abstracts;

import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.ChoiceString;

public class AbstractChoice {

    public ChoiceString.ChoiceData data;
    public ChoiceType type;
    public AbstractRoom room;
    public String id;
    public String name;
    public String desc;
    public String[] rawDesc;
    public float prob;
    public boolean must = false;

    public AbstractChoice(String id, AbstractRoom r, ChoiceType t) {
        this(id, r, t, 1);
    }

    public AbstractChoice(String id, AbstractRoom r, ChoiceType t, float p) {
        this.id = id;
        data = StringHandler.choiceString.get(id);
        name = data.NAME;
        rawDesc = data.DESC;
        room = r;
        type = t;
        prob = p;
    }

    public void must() {
        must = true;
    }

    public enum ChoiceType {
        BATTLE, ELITE, BOSS, REST, LOOK, DETOUR, GOOD
    }
}
