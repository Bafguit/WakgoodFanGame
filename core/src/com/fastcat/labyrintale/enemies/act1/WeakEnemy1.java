package com.fastcat.labyrintale.enemies.act1;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;

public class WeakEnemy1 extends AbstractEnemy {

    private static final String ID = "WeakEnemy1";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 15;

    public WeakEnemy1() {
        super(ID, TYPE, HEALTH);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new StrikeE(this));
        temp.add(new BarrierE(this));
        temp.add(new FuryE(this));
        return temp;
    }
}
