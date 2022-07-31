package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.ArmourStatus;
import com.fastcat.labyrintale.status.CourageStatus;

public class Test75 extends AbstractSkill {

    private static final String ID = "Test75";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 2;

    public Test75(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new ArmourStatus(value), owner, e, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
