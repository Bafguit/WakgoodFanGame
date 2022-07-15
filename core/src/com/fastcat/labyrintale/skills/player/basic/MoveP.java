package com.fastcat.labyrintale.skills.player.basic;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.MoveAction;

public class MoveP extends AbstractSkill {

    private static final String ID = "MoveP";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.BASIC;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;

    public MoveP(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity target) {
        top(new MoveAction(owner, target.index));
    }

    @Override
    protected void upgradeCard() {

    }
}
