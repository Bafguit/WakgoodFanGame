package com.fastcat.labyrintale.skills.player;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_HEAL;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_SHIELD;

public class Heal extends AbstractSkill {

    private static final String ID = "Heal";
    private static final Sprite IMG = SKILL_HEAL;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.P_ALL;
    private static final int VALUE = 2;

    public Heal(AbstractEntity e) {
        super(e, ID, IMG, CLASS, RARITY, TARGET);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new HealAction(owner, TARGET, value, null));
    }

    @Override
    protected void upgradeCard() {

    }
}
