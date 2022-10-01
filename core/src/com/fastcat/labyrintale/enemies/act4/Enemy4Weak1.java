package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.DoubleE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.HinderE;
import com.fastcat.labyrintale.skills.enemy.UnblockE;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy4Weak1 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak1";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 51;

    public Enemy4Weak1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public void preBattle() {
        applyStatus(new AttackStatus(3), this, 3, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new HinderE(this));
        temp.add(new DoubleE(this));
        temp.add(new GrowE(this));
        return temp;
    }
}
