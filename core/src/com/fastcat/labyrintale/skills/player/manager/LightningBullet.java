package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.ShockStatus;

public class LightningBullet extends AbstractSkill {

    private static final String ID = "LightningBullet";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ALL;
    private static final int VALUE = 3;

    public LightningBullet(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
        setBaseValue(2, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new ShockStatus(value), owner, target, true));
        top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHT));
    }

    @Override
    protected void upgradeCard() {

    }
}
