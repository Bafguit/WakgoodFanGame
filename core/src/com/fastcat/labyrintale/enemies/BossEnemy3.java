package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class BossEnemy3 extends AbstractEnemy {

    private static final String ID = "BossEnemy3";
    private static final EnemyType TYPE = EnemyType.BOSS;
    private static final int HEALTH = 300;

    public BossEnemy3() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new PushE(this);
        for(int i = 0; i < 4; i++) {
            s.upgrade();
        }
        temp.add(s);
        AbstractSkill s2 = new DualAttackE(this);
        for(int i = 0; i < 4; i++) {
            s2.upgrade();
        }
        temp.add(s2);
        AbstractSkill ss = new BarrierE(this);
        for(int i = 0; i < 7; i++) {
            ss.upgrade();
        }
        temp.add(ss);
        AbstractSkill s3 = new WeakStrongE(this);
        s3.upgrade();
        temp.add(s3);
        temp.add(new AdjudgeE(this));
        AbstractSkill s4 = new StrikeE(this);
        for(int i = 0; i < 16; i++) {
            s4.upgrade();
        }
        temp.add(s4);
        AbstractSkill s5 = new GrowE(this);
        s5.upgrade();
        s5.upgrade();
        temp.add(new GrowE(this));
        return temp;
    }
}
