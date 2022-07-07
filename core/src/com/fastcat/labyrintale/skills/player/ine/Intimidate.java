package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.LethargyStatus;

public class Intimidate extends AbstractSkill {

    private static final String ID = "Intimidate";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 3;

    public Intimidate(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(2);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH));
        bot(new ApplyStatusAction(new LethargyStatus(value, false), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
