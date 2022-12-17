package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AllTwoE;
import com.fastcat.labyrintale.skills.enemy.FuryE;

public class Enemy4Normal7 extends AbstractEnemy {

    private static final String ID = "Enemy4Normal7";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 60;

    public Enemy4Normal7() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 4;
        stat.critical = 10;
        stat.debuRes = 10;
        stat.neutRes = 10;
        stat.moveRes = 10;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new AllTwoE(this);
        s1.upgrade();
        s1.upgrade();
        temp.add(s1);
        temp.add(new FuryE(this).upgrade());
        return temp;
    }
}
