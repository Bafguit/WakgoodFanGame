package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.RemoveStatusAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;

public class Purify extends AbstractSkill {

    private static final String ID = "Purify";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 2;

    public Purify(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
    }

    @Override
    public void use() {
        bot(new SelectTargetAction(this, target));
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        for(AbstractStatus s : e.status) {
            if(s != null && s.type == AbstractStatus.StatusType.DEBUFF) top(new RemoveStatusAction(s, true));
        }
        top(new HealAction(owner, e, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
