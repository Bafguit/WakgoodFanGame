package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;


public class Wakchori extends AbstractSkill {

    private static final String ID = "Wakchori";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_ALL;
    private static final int VALUE = 100;

    public Wakchori(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        ActionHandler.bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHTNING));
    }

    @Override
    protected void upgradeCard() {

    }
}
