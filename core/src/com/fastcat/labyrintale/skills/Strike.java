package com.fastcat.labyrintale.skills;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.ImageHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.ImageHandler.WAK_BASIC;

public class Strike extends AbstractSkill {

    private static final String ID = "Strike";
    private static final Texture IMG = WAK_BASIC;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardType TYPE = CardType.ATTACK;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.ENEMY;
    private static final int COST = 1;
    private static final int ATTACK = 100;

    public Strike() {
        super(ID, IMG, CLASS, TYPE, RARITY, TARGET, COST);
        setBaseAttack(ATTACK);
    }

    @Override
    public boolean condition(AbstractSkill otherCard) {
        return true;
    }

    @Override
    public void use() {

    }

    @Override
    public void upgrade() {

    }
}
