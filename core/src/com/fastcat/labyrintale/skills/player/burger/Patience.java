package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class Patience extends AbstractSkill {

    private static final String ID = "Patience";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 3;

    public Patience(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        passive = true;
    }

    @Override
    public void use() {

    }

    @Override
    protected void upgradeCard() {

    }

    @Override
    public void onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        if(type != AbstractEntity.DamageType.LOSE && damage <= value) {
            flash();
            top(new ApplyStatusAction(new CourageStatus(2), owner, target, true));
        }
    }
}
