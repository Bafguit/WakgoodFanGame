package com.fastcat.labyrintale.skills.player;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class Barrier extends AbstractSkill {

    private static final String ID = "Barrier";
    private static final Texture IMG = SKILL_SHIELD;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.P_F;
    private static final int VALUE = 4;

    public Barrier(AbstractEntity e) {
        super(e, ID, IMG, CLASS, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    public void upgrade() {

    }
}
