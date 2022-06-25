package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class PungSkill extends AbstractSkill {

    private static final String ID = "pung";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ADVISOR;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    public static final int VALUE = 3;

    public PungSkill() {
        super(ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH, true));
        AbstractEnemy e = (AbstractEnemy) AbstractSkill.getTargets(target).get(0);
        bot(new MoveAction(e, 3, 0.2f));
    }

    @Override
    protected void upgradeCard() {

    }
}
