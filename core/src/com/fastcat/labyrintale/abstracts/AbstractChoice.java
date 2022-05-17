package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.ChoiceString;
import org.graalvm.compiler.loop.MathUtil;

import java.io.Serializable;

public class AbstractChoice {

    public ChoiceString.ChoiceData data;
    public ChoiceType type;
    public AbstractRoom room;
    public String name;
    public String desc;
    public String[] rawDesc;
    public Sprite img = FileHandler.ui.get("DRAW");
    public int prob;
    public boolean must = false;

    public AbstractChoice(ChoiceString.ChoiceData data) {

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
        if(prob > 75) return rawDesc[3];
        else if(prob > 50) return rawDesc[2];
        else if (prob > 25) return rawDesc[1];
        else return rawDesc[0];
    }

    public void must() {
        must = true;
        desc = rawDesc[AbstractLabyrinth.mapRandom.nextInt(rawDesc.length - 1)];
    }

    public enum ChoiceType {
        BATTLE, ELITE, BOSS, REST, LOOK, SHOP, DETOUR, GOOD
    }
}
