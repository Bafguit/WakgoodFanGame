package com.fastcat.labyrintale.skills;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass;
import static com.fastcat.labyrintale.handlers.FileHandler.SKILL_STRIKE;

public class Temp extends AbstractSkill {

    private static final String ID = "Temp";
    private static final Texture IMG = SKILL_STRIKE;
    private static final PlayerClass CLASS = PlayerClass.TEST;
    private static final CardRarity RARITY = CardRarity.ADVISOR;
    private static final CardTarget TARGET = CardTarget.E_L;
    private static final int VALUE = 4;

    public Temp() {
        super(ID, IMG, CLASS, RARITY, TARGET);
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
