package com.fastcat.labyrintale.skills.player.manager;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class DramaticExit extends AbstractSkill {

    private static final String ID = "DramaticExit";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 4;

    public DramaticExit(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.LIGHT, true));
        bot(new MoveAction((AbstractPlayer) owner, 3, 0.3f));
    }

    @Override
    protected void upgradeCard() {

    }
}
