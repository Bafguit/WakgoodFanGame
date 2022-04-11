package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.MoveAction;

public class MoveLeftE extends AbstractSkill {

    private static final String ID = "MoveLeft";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.TOKEN;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private final AbstractEnemy enemy;

    public MoveLeftE(AbstractEnemy e) {
        super(e, ID, TYPE, RARITY, TARGET);
        enemy = e;
    }

    @Override
    public void use() {
        bot(new MoveAction(enemy, true));
    }

    @Override
    protected boolean available() {
        return enemy.tempIndex > 0;
    }

    @Override
    protected void upgradeCard() {

    }
}
