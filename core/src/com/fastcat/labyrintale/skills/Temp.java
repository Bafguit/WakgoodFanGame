package com.fastcat.labyrintale.skills;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;

public class Temp extends AbstractSkill {

    private static final String ID = "Temp";
    private static final Texture IMG = SKILL_STRIKE;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.TOKEN;
    private static final CardTarget TARGET = CardTarget.NONE;
    private static final int VALUE = 4;

    public Temp() {
        super(ID, IMG, CLASS, RARITY, TARGET);
    }

    @Override
    public void use() {

    }

    @Override
    public void upgrade() {

    }
}
