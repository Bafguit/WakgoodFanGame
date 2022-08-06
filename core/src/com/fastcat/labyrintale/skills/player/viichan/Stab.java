package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.ScarStatus;

public class Stab extends AbstractSkill {

    private static final String ID = "Stab";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 2;

    public Stab(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_H, true));
        bot(new ApplyStatusAction(new ScarStatus(value), owner, target, true));
    }

    @Override
    protected void upgradeCard() {
        if(upgradeCount % 2 == 1) {
            value = ++baseValue;
        }
    }
}
