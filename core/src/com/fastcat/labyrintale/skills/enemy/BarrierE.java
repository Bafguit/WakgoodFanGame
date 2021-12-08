package com.fastcat.labyrintale.skills.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_SHIELD;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;

public class BarrierE extends AbstractSkill {

    private static final String ID = "Barrier";
    private static final Sprite IMG = SKILL_SHIELD;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.E_F;
    private static final int VALUE = 4;

    public BarrierE(AbstractEntity e) {
        super(ID, IMG, CLASS, RARITY, TARGET);
        setBaseSpell(VALUE);
        owner = e;
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    public void upgrade() {

    }
}
