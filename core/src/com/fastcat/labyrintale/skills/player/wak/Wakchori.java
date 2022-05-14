package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.InfectionStatus;


public class Wakchori extends AbstractSkill {

    private static final String ID = "Wakchori";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_F;
    private static final int ATTACK = 1;
    private static final int VALUE = 1;

    public Wakchori(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, null, true));
        bot(new AttackAction(owner, target, attack, null, true));
        bot(new AttackAction(owner, target, attack, null, true));
    }

    @Override
    protected void upgradeCard() {

    }
}
