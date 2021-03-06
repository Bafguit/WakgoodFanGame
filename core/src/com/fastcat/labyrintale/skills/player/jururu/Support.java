package com.fastcat.labyrintale.skills.player.jururu;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.status.CourageStatus;

public class Support extends AbstractSkill {

    private static final String ID = "Support";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ALL;
    private static final int VALUE = 2;

    public Support(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE);
        cooltime = 3;
    }

    @Override
    public void use() {
        ActionHandler.bot(new BlockAction(this.owner, target, spell));
    }

    @Override
    public void onTarget(AbstractEntity e) {
        Array<AbstractEntity> temp = new Array<>();
        temp.add(owner);
        temp.add(e);
        top(new ApplyStatusAction(new CourageStatus(value), owner, temp, false));
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        for (PlayerView pv : Labyrintale.battleScreen.players) {
            if (pv.player.isAlive() && pv.player != owner) {
                pv.isTarget = true;
                can = true;
            }
        }
        if (can) return true;
        else {
            top(new ApplyStatusAction(new CourageStatus(value), owner, SkillTarget.SELF, false));
            return false;
        }
    }

    @Override
    protected void upgradeCard() {
        if (upgradeCount == 3) {
            cooltime = 2;
        } else {
            setBaseValue(value + 1);
        }
    }
}
