package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.ChargeE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class Enemy3Weak8 extends AbstractEnemy {

    private static final String ID = "Enemy3Weak8";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 37;

    public Enemy3Weak8() {
        super(ID, TYPE, HEALTH);
        stat.speed = 0;
        stat.debuRes = 5;
        stat.neutRes = 15;
        stat.critical = 15;
        stat.moveRes = 5;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new StrikeE(this);
        s.upgrade();
        temp.add(s);
        temp.add(new GrowE(this));
        temp.add(new ChargeE(this));
        AbstractSkill ss = new BarrierE(this);
        ss.upgrade();
        temp.add(ss);
        return temp;
    }
}
