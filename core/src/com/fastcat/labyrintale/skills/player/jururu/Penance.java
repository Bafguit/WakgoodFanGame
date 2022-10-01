package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.screens.battle.EnemyBattleView;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;

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
            nextTurn = true;
            top(new HealAction(owner, target, spell));
            top(new SelectTargetAction(this));
        }
    }

    @Override
    protected void beforeOnTarget() {
        if(enemySelect) {
            top(new NextTurnAction());
            top(new TurnEndAction(owner));
        }
    }

    @Override
    public boolean setTarget() {
        boolean can = false;
        if (enemySelect) {
            for (int i = 0; i < 4; i++) {
                EnemyBattleView pv = Labyrintale.battleScreen.enemies[i];
                if (pv.enemy.isAlive()) {
                    pv.isTarget = true;
                    can = true;
                    if (pv.enemy.hasStatus("Provoke")) break;
                }
            }
        } else {
            for (PlayerBattleView pv : Labyrintale.battleScreen.players) {
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
