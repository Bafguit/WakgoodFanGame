package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.InfectionStatus;
import com.fastcat.labyrintale.status.ShockStatus;

public class Lightning extends AbstractSkill {

    private static final String ID = "Lightning";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 3;

    public Lightning(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new ShockStatus(value), owner, e, true));
        top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHTNING));
    }

    @Override
    protected void upgradeCard() {

    }
}
