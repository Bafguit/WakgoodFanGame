package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Bless extends AbstractSkill {

    private static final String ID = "Bless";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 2;

    public Bless(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {
        bot(new SelectTargetAction(this, target));
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        top(new BlockAction(this.owner, e, spell));
        top(new HealAction(this.owner, e, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
