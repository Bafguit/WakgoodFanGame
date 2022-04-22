package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.status.BlindStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class EyeSting extends AbstractSkill {

    private static final String ID = "EyeSting";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_F;
    private static final int ATTACK = 2;
    private static final int UP = 1;

    public EyeSting(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(ATTACK, UP);
        cooltime = 3;
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, null));
        bot(new ApplyStatusAction(new BlindStatus(), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
