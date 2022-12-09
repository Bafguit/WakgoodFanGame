package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.CoercionAction;

public class BattleE extends AbstractSkill {

    private static final String ID = "BattleE";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

    public BattleE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setIntent(IntentType.DEBUFF);
    }

    @Override
    public void use() {
        bot(new CoercionAction(owner, false));
    }

    @Override
    protected void upgradeCard() {}
}
