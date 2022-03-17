package com.fastcat.labyrintale.skills.player.lilpa;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.LilpaaAction;

public class Lilpaa extends AbstractSkill {

    private static final String ID = "Lilpaa";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.ALL;
    private static final int ATTACK = 5;
    private static final int VALUE = 2;
    private static final int UP = 1;

    public Lilpaa(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, UP);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        bot(new LilpaaAction(owner, attack, value));
    }

    @Override
    protected void upgradeCard() {

    }
}
