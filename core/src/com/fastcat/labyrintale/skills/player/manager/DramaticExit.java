package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.ResistMinusStatus;

public class DramaticExit extends AbstractSkill {

    private static final String ID = "DramaticExit";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 6;

    public DramaticExit(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 2);
        setBaseCost(2);
        setBaseValue(1);
    }

    @Override
    public void use() {
        AbstractAction a = new ApplyStatusAction(new ResistMinusStatus(value), owner, target, true);
        bot(a);
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.GUN, true), a);
        bot(new MoveAction(owner, owner, 3, 0.3f));
    }

    @Override
    protected void upgradeCard() {}
}
