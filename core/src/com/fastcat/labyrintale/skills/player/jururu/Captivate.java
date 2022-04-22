package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.LethargyStatus;
import com.fastcat.labyrintale.status.UnfortifiedStatus;

public class Captivate extends AbstractSkill {

    private static final String ID = "Captivate";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.E_DF;
    private static final int VALUE = 1;

    public Captivate(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        ActionHandler.bot(new ApplyStatusAction(new LethargyStatus(spell, false), owner, target, false));
    }

    @Override
    protected void upgradeCard() {

    }
}
