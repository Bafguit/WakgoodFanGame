package com.fastcat.labyrintale.skills.player.burger;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;

public class KeepOnLine extends AbstractSkill {

    private static final String ID = "KeepOnLine";
    private static final SkillType TYPE = SkillType.DEFENCE;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.PLAYER;
    private static final int VALUE = 4;

    public KeepOnLine(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseSpell(VALUE, 2);
    }

    @Override
    public void use() {}

    @Override
    public void onTarget(AbstractEntity e) {
        top(new BlockAction(owner, owner, spell));
        int i = owner.index < e.index ? e.index - 1 : e.index;
        if (i != owner.index && i >= 0) {
            top(new MoveAction(owner, owner, i, 0.3f));
        }
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        for (PlayerBattleView pv : Labyrintale.battleScreen.players) {
            if (pv.entity.isAlive() && pv.entity != owner) {
                pv.isTarget = true;
                can = true;
            }
        }
        if (can) return true;
        else {
            top(new BlockAction(owner, owner, spell));
            return false;
        }
    }

    @Override
    protected void upgradeCard() {}
}
