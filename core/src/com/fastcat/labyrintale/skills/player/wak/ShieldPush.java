package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;

public class ShieldPush extends AbstractSkill {

    private static final String ID = "ShieldPush";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 4;

    public ShieldPush(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
        setBaseAttack(VALUE);
        setBaseCost(2);
    }

    @Override
    public void use() {
        bot(new BlockAction(owner, owner, spell));
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.HEAVY, true));
    }

    @Override
    protected void upgradeCard() {}
}
