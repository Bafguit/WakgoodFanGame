package com.fastcat.labyrintale.skills.enemy;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;

public class StrikeE extends AbstractSkill {

    private static final String ID = "Strike";
    private static final Sprite IMG = SKILL_STRIKE;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.STARTER;
    private static final CardTarget TARGET = CardTarget.P_F;
    private static final int VALUE = 5;

    public StrikeE(AbstractEntity e) {
        super(ID, IMG, CLASS, RARITY, TARGET);
        setBaseAttack(VALUE);
        owner = e;
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, null));
    }

    @Override
    public void upgrade() {

    }
}
