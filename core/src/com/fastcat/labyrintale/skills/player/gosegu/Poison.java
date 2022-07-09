package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.status.InfectionStatus;

public class Poison extends AbstractSkill {

    private static final String ID = "Poison";
    private static final SkillType TYPE = SkillType.SCHEME;
    private static final SkillRarity RARITY = SkillRarity.SILVER;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 4;

    public Poison(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseValue(VALUE, 1);
        cooltime = 3;
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new InfectionStatus(value), owner, e, false));
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        for(int i = 0; i < 4; i++) {
            EnemyView pv = Labyrintale.battleScreen.enemies[i];
            if(pv.enemy.isAlive()) {
                pv.isTarget = true;
                can = true;
                if(pv.enemy.hasStatus("Provoke")) break;
            }
        }
        return can;
    }

    @Override
    protected void upgradeCard() {

    }
}
