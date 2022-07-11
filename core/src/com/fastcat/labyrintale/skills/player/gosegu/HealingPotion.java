package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.status.InfectionStatus;

public class HealingPotion extends AbstractSkill {

    private static final String ID = "HealingPotion";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 3;

    public HealingPotion(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new InfectionStatus(1), owner, e, true));
        top(new HealAction(owner, e, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
