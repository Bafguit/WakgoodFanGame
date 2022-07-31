package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class BossEnemy2 extends AbstractEnemy {

    private static final String ID = "BossEnemy2";
    private static final EnemyType TYPE = EnemyType.BOSS;
    private static final int HEALTH = 258;

    public BossEnemy2() {
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
        AbstractSkill s3 = new FrailStrongE(this);
        s3.upgrade();
        temp.add(s3);
        AbstractSkill sss = new AdjudgeE(this);
        sss.upgrade();
        sss.upgrade();
        temp.add(sss);
        AbstractSkill s4 = new StrikeE(this);
        for(int i = 0; i < 16; i++) {
            s4.upgrade();
        }
        temp.add(s4);
        AbstractSkill s5 = new GrowE(this);
        s5.upgrade();
        s5.upgrade();
        temp.add(s5);
        return temp;
    }

    @Override
    public void atEndOfRound() {

    }
}
