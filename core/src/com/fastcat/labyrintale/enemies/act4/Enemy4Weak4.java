package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AttackLowE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.skills.enemy.WeakE;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Enemy4Weak4 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak4";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 55;

    public Enemy4Weak4() {
        super(ID, TYPE, HEALTH);
        stat.speed = 4;
        stat.critical = 5;
        stat.debuRes = 40;
        stat.neutRes = 5;
        stat.moveRes = 5;
    }

    @Override
    public void preBattle() {
        applyStatus(
                new ImmuneStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3),
                this,
                AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3,
                false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s1 = new AttackLowE(this).upgrade();
        for (int i = 0; i < 4; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        AbstractSkill s4 = new AttackLowE(this).upgrade();
        for (int i = 0; i < 4; i++) {
            s4.upgrade();
        }
        temp.add(s4);
        AbstractSkill s2 = new FuryE(this);
        s2.upgrade();
        s2.upgrade();
        temp.add(s2);
        AbstractSkill s3 = new WeakE(this);
        s3.upgrade().upgrade();
        temp.add(s3);
        return temp;
    }
}
