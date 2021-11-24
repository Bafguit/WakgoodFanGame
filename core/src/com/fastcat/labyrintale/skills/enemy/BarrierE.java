package com.fastcat.labyrintale.skills.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_SHIELD;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;

public class BarrierE extends AbstractSkill {

    private static final String ID = "Barrier";
    private static final Texture IMG = SKILL_SHIELD;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final int VALUE = 4;

    public BarrierE() {
        super(ID, IMG, CLASS, RARITY);
        setEnemyTarget(true, false, false, false);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {

    }

    @Override
    public void upgrade() {

    }
}
