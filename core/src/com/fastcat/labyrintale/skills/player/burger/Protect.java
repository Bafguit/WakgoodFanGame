package com.fastcat.labyrintale.skills.player.burger;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class Protect extends AbstractSkill {

    private static final String ID = "Protect";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.STARTER;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 3;

    public Protect(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {
        bot(new SelectTargetAction(this, target));
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        Array<AbstractEntity> temp = new Array<>();
        temp.add(owner);
        if(e != owner) temp.add(e);
        top(new BlockAction(this.owner, temp, spell));
    }

    @Override
    protected void upgradeCard() {

    }
}
