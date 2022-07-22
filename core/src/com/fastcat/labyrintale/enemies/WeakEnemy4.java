package com.fastcat.labyrintale.enemies;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;

public class WeakEnemy4 extends AbstractEnemy {

    private static final String ID = "WeakEnemy4";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 35;

    public WeakEnemy4() {
        super(ID, TYPE, HEALTH);
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
        temp.add(new FrailE(this));
        return temp;
    }
}
