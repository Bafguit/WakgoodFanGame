package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;


public class Wakchori extends AbstractSkill {

    private static final String ID = "Wakchori";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int ATTACK = 1;
    private static final int VALUE = 1;

    public Wakchori(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHT, true));
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHT, true));
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHT, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
