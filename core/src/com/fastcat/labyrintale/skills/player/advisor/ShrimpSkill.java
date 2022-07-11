package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.LethargyStatus;

public class ShrimpSkill extends AbstractSkill {

    private static final String ID = "shrimp";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
    public static final int VALUE = 1;

    public ShrimpSkill() {
        super(ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE);
        cooltime = 3;
    }

    @Override
    public void use() {
        ActionHandler.bot(new ApplyStatusAction(new LethargyStatus(value), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
