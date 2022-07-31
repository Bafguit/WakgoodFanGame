package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;

public class ShieldPush extends AbstractSkill {

    private static final String ID = "ShieldPush";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 4;

    public ShieldPush(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new BlockAction(this.owner, target, spell));
        AbstractEnemy e = (AbstractEnemy) AbstractSkill.getTargets(SkillTarget.ENEMY_FIRST).get(0);
        bot(new MoveAction(e, false, 0.2f));
    }

    @Override
    protected void upgradeCard() {

    }
}
