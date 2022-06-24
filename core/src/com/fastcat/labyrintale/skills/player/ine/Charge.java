package com.fastcat.labyrintale.skills.player.ine;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class Charge extends AbstractSkill {

    private static final String ID = "Charge";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;
    private static final int VALUE = 3;

    public Charge(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(VALUE, 1);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new MoveAction((AbstractPlayer) owner, false, 0.05f));
        bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SMASH));
        bot(new BlockAction(this.owner, SkillTarget.SELF, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
