package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.MoveAction;

public class PungSkill extends AbstractSkill {

    private static final String ID = "pung";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;

    public PungSkill() {
        super(ID, TYPE, RARITY, TARGET);
        cooltime = 3;
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new MoveAction(e, owner, 3, 0.25f));
    }

    @Override
    protected void upgradeCard() {

    }
}
