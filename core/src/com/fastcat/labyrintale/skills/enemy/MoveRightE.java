package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.MoveAction;

public class MoveRightE extends AbstractSkill {

    private static final String ID = "MoveRight";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.TOKEN;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private final AbstractEnemy enemy;

    public MoveRightE(AbstractEnemy e) {
        super(e, ID, TYPE, RARITY, TARGET);
        enemy = e;
    }

    @Override
    public void use() {
        bot(new MoveAction(enemy, false));
    }

    @Override
    public boolean canUse() {
        if(super.canUse()) {
            for (int i = 0; i < 4; i++) {
                AbstractEnemy t = AbstractLabyrinth.currentFloor.currentRoom.enemies[i];
                if (t == owner) return i < 3;
            }
        }
        return false;
    }

    @Override
    protected void upgradeCard() {

    }
}
