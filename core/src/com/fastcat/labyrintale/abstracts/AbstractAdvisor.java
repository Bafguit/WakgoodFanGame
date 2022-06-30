package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.AdvisorString;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public abstract class AbstractAdvisor implements Cloneable {

    public AdvisorClass cls;
    public AbstractSkill skill;
    public AdvisorString.AdvisorData data;
    public Sprite img;

    public AbstractAdvisor(AdvisorClass cls, AbstractSkill skill) {
        this.cls = cls;
        this.skill = skill;
        img = advImg.get(cls);
        data = StringHandler.advisorString.get(cls.toString().toLowerCase());
    }

    public void onHire() {

    }

    @Override
    public AbstractAdvisor clone() {
        try {
            return (AbstractAdvisor) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public enum AdvisorClass {
        DOPA, SECRET, RUSEOK, HIKI, PUNG, SHRIMP, NEGATIVE,
        JK, SOPHIA, DUKSU, HAKU, BUSINESS, FREETER, CARNAR
    }
}
