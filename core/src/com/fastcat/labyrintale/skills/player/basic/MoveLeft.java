package com.fastcat.labyrintale.skills.player.basic;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.MoveAction;

public class MoveLeft extends AbstractSkill {

    private static final String ID = "MoveLeft";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.BASIC;
    private static final SkillTarget TARGET = SkillTarget.SELF;

    public MoveLeft(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
    }

    @Override
    public void use() {
        bot(new MoveAction((AbstractPlayer) owner, true));
    }

    @Override
    protected boolean available() {
        return owner.tempIndex < 3;
    }

    @Override
    protected void upgradeCard() {

    }
}
