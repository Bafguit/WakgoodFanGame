package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public abstract class AbstractAdvisor {

    public AdvisorClass cls;
    public AbstractSkill skill;
    public boolean used = false;

    public AbstractAdvisor() {
        this(AdvisorClass.NONE, null);
    }

    public AbstractAdvisor(AdvisorClass cls, AbstractSkill skill) {
        this.cls = cls;
        this.skill = skill;
    }

    public enum AdvisorClass {
        NONE, BURGER
    }
}
