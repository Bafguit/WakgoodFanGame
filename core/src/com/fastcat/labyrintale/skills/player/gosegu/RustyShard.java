package com.fastcat.labyrintale.skills.player.gosegu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.status.InfectionStatus;

public class RustyShard extends AbstractSkill {

    private static final String ID = "RustyShard";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.BRONZE;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 2;

    public RustyShard(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(3);
        setBaseValue(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity e) {
        top(new ApplyStatusAction(new InfectionStatus(value), owner, e, true));
        top(new AttackAction(owner, e, attack, AttackAction.AttackType.LIGHT));
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
