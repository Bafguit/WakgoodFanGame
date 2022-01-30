package com.fastcat.labyrintale.skills;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class MoveRight extends AbstractSkill {

    private static final String ID = "MoveRight";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.TOKEN;
    private static final SkillTarget TARGET = SkillTarget.SELF;

    public MoveRight(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
    }

    @Override
    public void use() {
        //위치 변경
    }

    @Override
    protected void upgradeCard() {

    }
}
