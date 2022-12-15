package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.DoubleE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.ArmourStatus;

public class Enemy2Elite2 extends AbstractEnemy {

    private static final String ID = "Enemy2Elite2";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 58;

    public Enemy2Elite2() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 1;
        stat.critical = 20;
        stat.debuRes = 20;
        stat.neutRes = 5;
        stat.moveRes = 20;
    }

    @Override
    public void preBattle() {
        applyStatus(new ArmourStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 15 : 10),
                this, AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 15 : 10, false);
        block = 10;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new DoubleE(this);
        for (int i = 0; i < 3; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s2 = new StrikeE(this);
        for (int i = 0; i < 4; i++) {
            s2.upgrade();
        }
        temp.add(s2);
        AbstractSkill s3 = new FuryE(this);
        s3.upgrade();
        s3.upgrade();
        temp.add(s3);
        return temp;
    }
}
