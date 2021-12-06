package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.FileHandler.WAK_BASIC;

public abstract class AbstractAdvisor {

    public AdvisorClass cls;
    public AbstractSkill skill;
    public boolean used = false;
    public Sprite img;

    public AbstractAdvisor() {
        this(AdvisorClass.NONE, null);
    }

    public AbstractAdvisor(AdvisorClass cls, AbstractSkill skill) {
        this.img = getWak(cls);
        this.cls = cls;
        this.skill = skill;
    }

    private static Sprite getWak(AbstractAdvisor.AdvisorClass cls) {
        switch (cls) {
            case BURGER:
                return WAK_BASIC;
            default:
                return CHAR_SELECT;
        }
    }

    public enum AdvisorClass {
        NONE, BURGER
    }
}
