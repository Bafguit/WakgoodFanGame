package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.FileHandler;

public abstract class AbstractAdvisor implements Cloneable {

    public AdvisorClass cls;
    public AbstractSkill skill;
    public Sprite img;

    public AbstractAdvisor(AdvisorClass cls, AbstractSkill skill) {
        this.cls = cls;
        this.skill = skill;
        img = FileHandler.getAdvImg().get(cls);
    }

    public void onHire() {}

    public void onFire() {}

    @Override
    public AbstractAdvisor clone() {
        try {
            return (AbstractAdvisor) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }

    public enum AdvisorClass {
        DOPA,
        SECRET,
        RUSUK,
        HIKI,
        PUNG,
        CALLYCARLY,
        MANDU,
        JK,
        SOPHIA,
        DUKSU,
        HAKU,
        BUSINESS,
        FREETER,
        CARNAR
    }
}
