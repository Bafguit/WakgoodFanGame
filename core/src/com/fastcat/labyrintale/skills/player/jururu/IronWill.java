package com.fastcat.labyrintale.skills.player.jururu;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class IronWill extends AbstractSkill {

    private static final String ID = "IronWill";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 3;

    public IronWill(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        Array<AbstractEntity> temp = new Array<>();
        temp.add(owner);
        temp.add(e);
        top(new ApplyStatusAction(new EnduranceStatus(value), owner, temp, false));
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        for (PlayerBattleView pv : Labyrintale.battleScreen.players) {
            if (pv.player.isAlive() && pv.player != owner) {
                pv.isTarget = true;
                can = true;
            }
        }
        if (can) return true;
        else {
            top(new ApplyStatusAction(new EnduranceStatus(value), owner, SkillTarget.SELF, false));
            return false;
        }
    }

    @Override
    protected void upgradeCard() {

    }
}
