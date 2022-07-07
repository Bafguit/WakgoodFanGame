package com.fastcat.labyrintale.skills.player.burger;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.screens.battle.PlayerView;

public class SuperSave extends AbstractSkill {

    private static final String ID = "SuperSave";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 6;

    public SuperSave(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE);
        cooltime = 3;
    }

    @Override
    public void use() {
        bot(new SelectTargetAction(this));
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        top(new BlockAction(this.owner, e, spell));
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        for(PlayerView pv : Labyrintale.battleScreen.players) {
            if(pv.player.isAlive()) {
                pv.isTarget = true;
                can = true;
            }
        }
        return can;
    }

    @Override
    protected void upgradeCard() {

    }
}
