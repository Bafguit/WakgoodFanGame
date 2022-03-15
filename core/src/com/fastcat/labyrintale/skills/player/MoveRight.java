package com.fastcat.labyrintale.skills.player;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
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
        bot(new MoveAction((AbstractPlayer) owner, false));
    }

    @Override
    public boolean canUse() {
        if(super.canUse()) {
            for (int i = 0; i < 4; i++) {
                AbstractPlayer t = AbstractLabyrinth.players[i];
                if (t == owner) return i > 0;
            }
        }
        return false;
    }

    @Override
    protected void upgradeCard() {

    }
}
