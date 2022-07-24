package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.SaveHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.ChoiceString;

public class AbstractChoice {

    public ChoiceString.ChoiceData data;
    public ChoiceType type;
    public AbstractRoom room;
    public String name;
    public String desc;
    public String[] rawDesc;
    public Sprite img = FileHandler.getUi().get("DRAW");
    public int prob;
    public boolean must = false;

    public AbstractChoice(SaveHandler.ChoiceData data) {
        this(GroupHandler.RoomGroup.getRoom(data.room.id), ChoiceType.valueOf(data.type), data.prob);
        must = data.must;
        room.isDone = data.room.isDone;
        room.battleDone = data.room.battleDone;
    }

    public AbstractChoice(AbstractRoom r, ChoiceType t, boolean m) {
        this(r, t, 1);
        must = m;
    }

    public AbstractChoice(AbstractRoom r, ChoiceType t, int p) {
        data = StringHandler.choiceString.get(t.toString().toLowerCase());
        name = data.NAME;
        rawDesc = data.DESC;
        room = r;
        type = t;
        prob = MathUtils.clamp(p, 0, 100);
        desc = getDesc();
    }

    public String getDesc() {
        if (prob > 75) return rawDesc[3];
        else if (prob > 50) return rawDesc[2];
        else if (prob > 25) return rawDesc[1];
        else return rawDesc[0];
    }

    public void must() {
        must = true;
        desc = rawDesc[AbstractLabyrinth.mapRandom.random(rawDesc.length - 2)];
    }

    public enum ChoiceType {
        BATTLE, ELITE, BOSS, REST, LOOK, SHOP, DETOUR, GOOD
    }
}
