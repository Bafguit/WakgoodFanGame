package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy4Normal5 extends AbstractEnemy {

    private static final String ID = "Enemy4Normal5";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 62;

    public Enemy4Normal5() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 2;
        stat.critical = 15;
        stat.debuRes = 15;
        stat.neutRes = 15;
        stat.moveRes = 15;
    }

    @Override
    public void preBattle() {
        applyStatus(
                new PunishStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3),
                this,
                AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3,
                false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s3 = new DoubleE(this);
        for (int i = 0; i < 4; i++) {
            s3.upgrade();
        }
        temp.add(s3);
        temp.add(new FuryE(this).upgrade());
        return temp;
    }
}
