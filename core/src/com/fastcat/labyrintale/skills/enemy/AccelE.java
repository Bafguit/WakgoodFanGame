package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class AccelE extends AbstractSkill {

    private static final String ID = "AccelE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 3;

    public AccelE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setIntent(IntentType.ATTACK);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AmbushAction(this));
    }

    @Override
    protected void upgradeCard() {}
}
