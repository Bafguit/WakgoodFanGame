package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.CounterStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Counter extends AbstractSkill {

    private static final String ID = "Counter";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 3;

    public Counter(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_H, true));
        top(new ApplyStatusAction(new CounterStatus(value), owner, owner, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
