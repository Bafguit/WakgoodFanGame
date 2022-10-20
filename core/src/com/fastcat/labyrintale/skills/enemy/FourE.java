package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class FourE extends AbstractSkill {

    private static final String ID = "FourE";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.ENEMY;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_FIRST;
    private static final int VALUE = 1;

    public FourE(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
    }

    @Override
    public void use() {
        AbstractAction a = new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHT, true);
        bot(a);
        for(int i = 0; i < 3; i++) {
            AbstractAction aa = new AttackAction(owner, TARGET, attack, AttackAction.AttackType.LIGHT, true);
            aa.preAction = a;
            bot(aa);
        }
    }

    @Override
    protected void upgradeCard() {

    }
}
