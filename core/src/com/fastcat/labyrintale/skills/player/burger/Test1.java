package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.LethargyStatus;

public class Test1 extends AbstractSkill {

    private static final String ID = "Test1";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_F;
    private static final int VALUE = 2;

    public Test1(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        AbstractEnemy e = (AbstractEnemy) AbstractSkill.getTargets(target).get(0);
        if(e.mRight.canUse()) {
            bot(new MoveAction(e, false, 0.3f));
        }
        bot(new ApplyStatusAction(new LethargyStatus(value, false), owner, target, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
