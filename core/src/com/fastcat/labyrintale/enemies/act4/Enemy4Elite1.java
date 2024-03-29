package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class Enemy4Elite1 extends AbstractEnemy {

    private static final String ID = "Enemy4Elite1";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 200;

    public Enemy4Elite1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 0;
        stat.critical = 20;
        stat.debuRes = 30;
        stat.neutRes = 20;
        stat.moveRes = 5;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new StrikeE(this);
        for (int i = 0; i < 10; i++) {
            s.upgrade();
        }
        s.disposable = true;
        temp.add(s);
        AbstractSkill s2 = new FrailStrongE(this).upgrade();
        for (int i = 0; i < 1; i++) {
            s2.upgrade();
        }
        s2.disposable = true;
        temp.add(s2);
        AbstractSkill s1 = new HinderAllE(this);
        for (int i = 0; i < 6; i++) {
            s1.upgrade();
        }
        s1.disposable = true;
        temp.add(s1);
        AbstractSkill s3 = new DecayE(this).upgrade();
        s3.upgrade();
        s3.disposable = true;
        temp.add(s3);
        AbstractSkill s4 = new UnstoppableE(this);
        for (int i = 0; i < 3; i++) {
            s4.upgrade();
        }
        temp.add(s4);
        temp.add(new FuryE(this).upgrade().upgrade().upgrade());
        return temp;
    }
}
