package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AllTwoE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.UnblockE;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy3Normal1 extends AbstractEnemy {

    private static final String ID = "Enemy3Normal1";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 61;

    public Enemy3Normal1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new AllTwoE(this);
        s1.upgrade();
        s1.upgrade();
        temp.add(s1);
        temp.add(new FuryE(this));
        return temp;
    }
}
