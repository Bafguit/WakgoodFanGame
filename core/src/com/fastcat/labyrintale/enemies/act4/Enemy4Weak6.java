package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.HostStatus;
import com.fastcat.labyrintale.status.SelfBombStatus;

public class Enemy4Weak6 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak6";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 22;

    public Enemy4Weak6() {
        super(ID, TYPE, HEALTH);
        stat.speed = 4;
        stat.critical = 5;
        stat.debuRes = 35;
        stat.neutRes = -100;
        stat.moveRes = 5;
    }

    @Override
    public void preBattle() {
        applyStatus(
                new HostStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3),
                this,
                AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3,
                false);
        applyStatus(new SelfBombStatus(3), this, 3, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new StrikeE(this);
        s1.upgrade();
        s1.upgrade();
        s1.upgrade();
        temp.add(s1);
        AbstractSkill s2 = new StrikeE(this);
        s2.upgrade();
        s2.upgrade();
        s1.upgrade();
        temp.add(s2);
        AbstractSkill s3 = new FuryE(this);
        s3.upgrade();
        s3.upgrade();
        s1.upgrade();
        temp.add(s3);
        return temp;
    }
}
