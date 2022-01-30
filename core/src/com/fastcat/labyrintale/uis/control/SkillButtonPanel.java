package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class SkillButtonPanel extends AbstractUI {

    public SkillButtonType type;
    public AbstractSkill skill;

    public SkillButtonPanel(SkillButtonType type) {
        super(BORDER);
        this.type = type;
    }

    public enum SkillButtonType {
        PLAYER, ADVISOR
    }
}
