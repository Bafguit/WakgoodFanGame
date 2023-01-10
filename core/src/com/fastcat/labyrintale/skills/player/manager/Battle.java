package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.UnblockableStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Battle extends AbstractSkill {

    private static final String ID = "Battle";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 3;

    public Battle(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 2);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new MoveAction(e, owner, 0, 0.15f));
        top(new ApplyStatusAction(new UnblockableStatus(), owner, e, true));
        top(new MoveAction(owner, owner, 0, 0.15f));
        top(new ApplyStatusAction(new CourageStatus(value), owner, owner, true));
    }

    @Override
    protected void upgradeCard() {}
}
