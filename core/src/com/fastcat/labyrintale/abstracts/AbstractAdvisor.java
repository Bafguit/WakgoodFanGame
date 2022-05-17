package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public abstract class AbstractAdvisor {

    public AdvisorClass cls;
    public AbstractSkill skill;

    public AbstractAdvisor(AdvisorClass cls, AbstractSkill skill) {
        this.cls = cls;
        this.skill = skill;
    }

    public enum AdvisorClass {
        DOPA, SECRET, RUSEOK, HIKI, PUNG, SHRIMP, NEGATIVE,
        JK, SOPHIA, DUKSU, HAKU, BUSINESS, FREETER, CARNAR
    }
}
