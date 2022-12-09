package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.skills.enemy.WeakE;

public class Enemy2Weak8 extends AbstractEnemy {

    private static final String ID = "Enemy2Weak8";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 31;

    public Enemy2Weak8() {
        super(ID, TYPE, HEALTH);
        stat.speed = 0;
        stat.debuRes = 5;
        stat.neutRes = 10;
        stat.critical = 5;
        stat.moveRes = 20;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new StrikeE(this);
        s.upgrade();
        temp.add(s);
        AbstractSkill ss = new StrikeE(this);
        ss.upgrade();
        temp.add(ss);
        temp.add(new GrowE(this));
        temp.add(new WeakE(this));
        return temp;
    }
}
