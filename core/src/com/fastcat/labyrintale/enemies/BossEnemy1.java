package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class BossEnemy1 extends AbstractEnemy {

    private static final String ID = "BossEnemy1";
    private static final EnemyType TYPE = EnemyType.BOSS;
    private static final int HEALTH = 158;

    public BossEnemy1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new StrikeE(this);
        for(int i = 0; i < 5; i++) {
            s.upgrade();
        }
        temp.add(s);
        AbstractSkill s2 = new BarrierE(this);
        for(int i = 0; i < 5; i++) {
            s2.upgrade();
        }
        temp.add(s2);
        temp.add(new FrailStrongE(this));
        AbstractSkill s3 = new SlashE(this);
        s3.upgrade();
        s3.upgrade();
        temp.add(s3);
        temp.add(new GrowE(this));
        return temp;
    }
}
