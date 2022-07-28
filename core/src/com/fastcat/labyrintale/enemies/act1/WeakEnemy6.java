package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class WeakEnemy6 extends AbstractEnemy {

    private static final String ID = "WeakEnemy6";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 46;

    public WeakEnemy6() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new GrowE(this);
        s.disposable = true;
        temp.add(s);
        temp.add(new UnstoppableE(this));
        return temp;
    }
}
