package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.UnblockableStatus;

public class CrystalStrike extends AbstractSkill {

    private static final String ID = "CrystalStrike";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 4;

    public CrystalStrike(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        AbstractAction a = new AttackAction(owner, target, attack, AttackAction.AttackType.HEAVY, true);
        bot(a);
        AbstractAction m = new ApplyStatusAction(new UnblockableStatus(), owner, target, true);
        m.preAction = a;
        bot(m);
    }

    @Override
    protected void upgradeCard() {

    }
}
