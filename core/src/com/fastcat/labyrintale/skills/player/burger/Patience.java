package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Patience extends AbstractSkill {

    private static final String ID = "Patience";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.SELF;
    private static final int VALUE = 1;

    public Patience(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE);
        passive = true;
    }

    @Override
    public void use() {

    }

    @Override
    protected void upgradeCard() {
        if((upgradeCount + 1) % 2 == 0 && value < 3) {
            setBaseValue(++baseValue);
        }
    }

    @Override
    public int onAttacked(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
        if(type != AbstractEntity.DamageType.LOSE && owner.health <= owner.maxHealth / 2)
            return damage - value;
        else
            return damage;
    }
}
