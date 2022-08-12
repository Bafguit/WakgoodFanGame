package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AttackLowE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.UnstoppableE;
import com.fastcat.labyrintale.skills.enemy.WeakE;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Enemy3Weak3 extends AbstractEnemy {

    private static final String ID = "Enemy3Weak3";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 72;

    public Enemy3Weak3() {
        super(ID, TYPE, HEALTH);
    }

    @Override
    public void preBattle() {

    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new UnstoppableE(this);
        for(int i = 0; i < 2; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s4 = new AttackLowE(this);
        for(int i = 0; i < 5; i++) {
            s4.upgrade();
        }
        temp.add(s4);
        return temp;
    }
}
