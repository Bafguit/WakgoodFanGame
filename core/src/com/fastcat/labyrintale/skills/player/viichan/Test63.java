package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.AttackStatus;

public class Test63 extends AbstractSkill {

    private static final String ID = "Test63";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_F;
    private static final int VALUE = 2;

    public Test63(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(3, 1);
        setBaseValue(1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, target, attack, null));
        bot(new ApplyStatusAction(new AttackStatus(value), owner, SkillTarget.SELF, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
