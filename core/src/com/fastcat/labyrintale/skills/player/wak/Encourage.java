package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;

public class Encourage extends AbstractSkill {

    private static final String ID = "Encourage";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
    private static final int VALUE = 5;

    public Encourage(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
        setBaseCost(2);
    }

    @Override
    public void use() {
        top(new BlockAction(owner, target, spell));
    }

    @Override
    protected void upgradeCard() {}
}
