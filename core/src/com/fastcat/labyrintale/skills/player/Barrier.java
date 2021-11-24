package com.fastcat.labyrintale.skills.player;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class Barrier extends AbstractSkill {

    private static final String ID = "Barrier";
    private static final Texture IMG = SKILL_SHIELD;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final int VALUE = 3;

    public Barrier() {
        super(ID, IMG, CLASS, RARITY);
        setPlayerTarget(true, false, false, false);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {

    }

    @Override
    public void upgrade() {

    }
}
