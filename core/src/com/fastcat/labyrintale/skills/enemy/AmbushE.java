package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class AmbushE extends AbstractSkill {

    private static final String ID = "AmbushE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_LAST;
    private static final int VALUE = 4;

    public AmbushE(AbstractEntity e) {
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
