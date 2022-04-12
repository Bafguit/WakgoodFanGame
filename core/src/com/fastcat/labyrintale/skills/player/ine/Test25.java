package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Test25 extends AbstractSkill {

    private static final String ID = "Test25";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.E_F;
    private static final int VALUE = 3;

    public Test25(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        if(owner.tempIndex > 0) bot(new MoveAction((AbstractPlayer) owner, false, 0.1f));
        bot(new AttackAction(owner, target, attack, null));
        bot(new BlockAction(this.owner, SkillTarget.SELF, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
