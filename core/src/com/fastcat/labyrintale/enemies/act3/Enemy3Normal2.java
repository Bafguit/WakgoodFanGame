package com.fastcat.labyrintale.enemies.act3;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy3Normal2 extends AbstractEnemy {

    private static final String ID = "Enemy3Normal2";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 70;

    public Enemy3Normal2() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 20;
        stat.debuRes = 10;
        stat.neutRes = 20;
        stat.moveRes = 10;
    }

    @Override
    public void preBattle() {
        applyStatus(new PunishStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 3 : 2),
                this, AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 3 : 2, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new CorrosE(this);
        for (int i = 0; i < 4; i++) {
            s.upgrade();
        }
        s.disposable = true;
        temp.add(s);
        AbstractSkill s2 = new StrikeE(this);
        for (int i = 0; i < 8; i++) {
            s2.upgrade();
        }
        AbstractSkill s3 = new DoubleE(this);
        for (int i = 0; i < 2; i++) {
            s3.upgrade();
        }
        temp.add(s3);
        temp.add(new FuryE(this).upgrade());
        return temp;
    }
}
