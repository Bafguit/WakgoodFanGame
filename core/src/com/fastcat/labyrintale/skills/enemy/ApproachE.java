package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;

public class ApproachE extends AbstractSkill {

    private static final String ID = "ApproachE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 6;

    public ApproachE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 3);
        setIntent(IntentType.ATTACK);
    }

    @Override
    public void use() {
        bot(new AmbushAction(this));
    }

    @Override
    protected void upgradeCard() {}
}
