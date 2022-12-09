package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.FrailE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class WeakEnemy4 extends AbstractEnemy {

    private static final String ID = "WeakEnemy4";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 32;

    public WeakEnemy4() {
        super(ID, TYPE, HEALTH);
        stat.speed = 2;
        stat.debuRes = 20;
        stat.neutRes = 10;
        stat.critical = 5;
        stat.moveRes = 5;
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
