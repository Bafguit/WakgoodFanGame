package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Overpower extends AbstractSkill {

    private static final String ID = "Overpower";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 2;

    public Overpower(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(4, 1);
        setBaseValue(1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AmbushAction(this));
    }

    @Override
    protected void upgradeCard() {

    }
}
