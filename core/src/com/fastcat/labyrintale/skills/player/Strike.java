package com.fastcat.labyrintale.skills.player;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;
import static com.fastcat.labyrintale.handlers.FileHandler.WAK_BASIC;

public class Strike extends AbstractSkill {

    private static final String ID = "Strike";
    private static final Texture IMG = SKILL_STRIKE;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.E_F;
    private static final int VALUE = 5;

    public Strike(AbstractEntity e) {
        super(e, ID, IMG, CLASS, RARITY, TARGET);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, null));
    }

    @Override
    public void upgrade() {

    }
}
