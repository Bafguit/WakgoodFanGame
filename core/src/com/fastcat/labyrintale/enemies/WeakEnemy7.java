package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AttackLowE;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.UnstoppableE;

public class WeakEnemy7 extends AbstractEnemy {

    private static final String ID = "WeakEnemy7";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 50;

    public WeakEnemy7() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new AttackLowE(this);
        s1.upgrade();
        s1.upgrade();
        temp.add(s1);
        AbstractSkill s = new BarrierE(this);
        s.upgrade();
        temp.add(s);
        AbstractSkill s2 = new AttackLowE(this);
        s2.upgrade();
        s2.upgrade();
        temp.add(s2);
        temp.add(new GrowE(this));
        return temp;
    }
}
