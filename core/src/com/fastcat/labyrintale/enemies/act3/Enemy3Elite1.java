package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.CorrosE;
import com.fastcat.labyrintale.skills.enemy.EncourageE;
import com.fastcat.labyrintale.skills.enemy.HinderAllE;

public class Enemy3Elite1 extends AbstractEnemy {

    private static final String ID = "Enemy3Elite1";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 81;

    public Enemy3Elite1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 10;
        stat.debuRes = 30;
        stat.neutRes = 10;
        stat.moveRes = 10;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new HinderAllE(this);
        for (int i = 0; i < 4; i++) {
            s.upgrade();
        }
        temp.add(s);
        AbstractSkill s2 = new CorrosE(this);
        for (int i = 0; i < 1; i++) {
            s2.upgrade();
        }
        temp.add(s2);
        AbstractSkill s1 = new HinderAllE(this);
        for (int i = 0; i < 4; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s3 = new EncourageE(this);
        s3.upgrade();
        temp.add(s3);
        return temp;
    }
}
