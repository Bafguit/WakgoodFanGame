package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.RestrictStatus;

public class RestrictAttackE extends AbstractSkill {

    private static final String ID = "RestrictAttackE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_LOW_HP;
    private static final int VALUE = 2;

    public RestrictAttackE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        setBaseAttack(8, 2);
        setIntent(IntentType.ATTACK_DEBUFF);
    }

    @Override
    public void use() {
        AbstractAction a;
        bot(a = new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHTNING, true));
        bot(new ApplyStatusAction(new RestrictStatus(value), owner, target, true), a);
    }

    @Override
    protected void upgradeCard() {}
}
