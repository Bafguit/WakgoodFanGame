package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.actions.HealAction;
import com.fastcat.labyrintale.actions.SelectTargetAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;

public class Penance extends AbstractSkill {

    private static final String ID = "Penance";
    private static final SkillType TYPE = SkillType.ATTACK;
    private static final SkillRarity RARITY = SkillRarity.NORMAL;
    private static final SkillTarget TARGET = SkillTarget.ENEMY;
    private static final int VALUE = 2;

    private boolean enemySelect = false;

    public Penance(AbstractEntity e) {
        super(e, ID, TYPE, RARITY, TARGET);
        setBaseAttack(3, 1);
        setBaseSpell(VALUE, 1);
    }

    @Override
    public void use() {

    }

    @Override
    public void onTarget(AbstractEntity target) {
        if(enemySelect) {
            enemySelect = false;
            top(new AttackAction(owner, target, attack, AttackAction.AttackType.BURN, true));
        } else {
            enemySelect = true;
            top(new HealAction(owner, target, spell));
            top(new SelectTargetAction(this));
        }
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        if (enemySelect) {
            for (int i = 0; i < 4; i++) {
                EnemyView pv = Labyrintale.battleScreen.enemies[i];
                if (pv.enemy.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                    if (pv.enemy.hasStatus("Provoke")) break;
                }
            }
        } else {
            for (PlayerView pv : Labyrintale.battleScreen.players) {
                if (pv.player.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                }
            }
        }
        return can;
    }

    @Override
    protected void upgradeCard() {

    }
}
