package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.ChargeE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.MaintainStatus;

public class Enemy4Weak2 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak2";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 70;

    public Enemy4Weak2() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 10;
        stat.debuRes = 40;
        stat.neutRes = 5;
        stat.moveRes = 10;
    }

    @Override
    public void preBattle() {
        block = 30;
        applyStatus(new MaintainStatus(this), this, 1, false);
        applyStatus(new AttackStatus(3), this, 3, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new ChargeE(this);
        for (int i = 0; i < 8; i++) {
            s.upgrade();
        }
        temp.add(s);
        temp.add(new GrowE(this).upgrade().upgrade());
        return temp;
    }
}
