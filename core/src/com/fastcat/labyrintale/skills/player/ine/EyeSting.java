package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BlindStatus;

public class EyeSting extends AbstractSkill {

    private static final String ID = "EyeSting";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int ATTACK = 2;
    private static final int UP = 1;

    public EyeSting(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, UP);
        cooltime = 5;
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHT));
        bot(new ApplyStatusAction(new BlindStatus(), owner, target, true));
    }

    @Override
    protected void upgradeCard() {
        if(upgradeCount == 2) cooltime = 4;
    }
}
