package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.InfectionStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Provoke extends AbstractSkill {

    private static final String ID = "Provoke";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.E_DF;
    private static final int VALUE = 2;

    public Provoke(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new ApplyStatusAction(new UnfortifiedStatus(spell, false), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
