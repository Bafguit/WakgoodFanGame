package com.fastcat.labyrintale.skills.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;

public class StrikeE extends AbstractSkill {

    private static final String ID = "Strike";
    private static final Texture IMG = SKILL_STRIKE;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final int VALUE = 4;

    public StrikeE() {
        super(ID, IMG, CLASS, RARITY);
        setPlayerTarget(true, false, false, false);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {

    }

    @Override
    public void upgrade() {

    }
}
