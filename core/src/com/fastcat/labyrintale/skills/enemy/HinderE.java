package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HinderAction;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.UnblockableStatus;

public class HinderE extends AbstractSkill {

    private static final String ID = "HinderE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;

    public HinderE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(4, 1);
        setBaseValue(5, 5);
    }

    @Override
    public void use() {
        bot(new HinderAction(owner, target, attack, value));
    }

    @Override
    protected void upgradeCard() {

    }
}
