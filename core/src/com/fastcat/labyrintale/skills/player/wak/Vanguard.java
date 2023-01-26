package com.fastcat.labyrintale.skills.player.wak;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Vanguard extends AbstractSkill {

    private static final String ID = "Vanguard";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;

    public Vanguard(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(9, 1);
        setBaseValue(2, 1);
        setBaseCost(3);
    }

    @Override
    public void use() {
        bot(new MoveAction(owner, owner, 0, 0.2f));
        bot(new BlockAction(owner, owner, spell));
        bot(new ApplyStatusAction(new EnduranceStatus(value), owner, SkillTarget.OTHER, true));
    }

    @Override
    protected void upgradeCard() {}
}
