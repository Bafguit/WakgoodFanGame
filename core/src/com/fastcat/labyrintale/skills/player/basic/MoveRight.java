package com.fastcat.labyrintale.skills.player.basic;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.MoveAction;

public class MoveRight extends AbstractSkill {

    private static final String ID = "MoveRight";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.BASIC;
    private static final SkillTarget TARGET = SkillTarget.SELF;

    public MoveRight(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
    }

    @Override
    public void use() {
        bot(new MoveAction(owner, false));
    }

    @Override
    protected boolean available() {
        return owner.index > 0;
    }

    @Override
    protected void upgradeCard() {

    }
}
