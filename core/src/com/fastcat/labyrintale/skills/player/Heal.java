package com.fastcat.labyrintale.skills.player;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_HEAL;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_SHIELD;

public class Heal extends AbstractSkill {

    private static final String ID = "Heal";
    private static final Texture IMG = SKILL_HEAL;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final int VALUE = 1;

    public Heal(AbstractEntity e) {
        super(e, ID, IMG, CLASS, RARITY);
        setPlayerTarget(true, true, true, true);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {

    }

    @Override
    public void upgrade() {

    }
}
