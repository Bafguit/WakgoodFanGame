package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.InfectionStatus;

public class UpgradePotion extends AbstractSkill {

    private static final String ID = "UpgradePotion";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.GOLD;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 1;

    public UpgradePotion(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE);
    }

    @Override
    public void use() {
        bot(new SelectTargetAction(this));
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        top(new ApplyStatusAction(new InfectionStatus(1), owner, e, true));
        top(new ApplyStatusAction(new AttackStatus(1), owner, e, true));
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
