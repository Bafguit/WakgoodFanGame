package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.ScarStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Disarm extends AbstractSkill {

    private static final String ID = "Disarm";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 4;

    public Disarm(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseValue(2, 1);
    }

    @Override
    public void use() {
        AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_H, true);
        bot(a);
        bot(new ApplyStatusAction(new UnfortifiedStatus(value), owner, target, true), a);
    }

    @Override
    protected void upgradeCard() {}
}
