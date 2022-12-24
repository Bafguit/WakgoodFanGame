package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.CounterE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.LureStatus;
import com.fastcat.labyrintale.status.SpikeStatus;

public class Enemy4Weak7 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak7";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 42;

    public Enemy4Weak7() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 0;
        stat.critical = 5;
        stat.debuRes = 40;
        stat.neutRes = 10;
        stat.moveRes = 10;
    }

    @Override
    public void preBattle() {
        applyStatus(new LureStatus(), this, 1, false);
        applyStatus(new SpikeStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 2 : 1),
                this, AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 2 : 1,
                false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new StrikeE(this);
        for (int i = 0; i < 6; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        temp.add(new GrowE(this).upgrade());
        return temp;
    }
}
