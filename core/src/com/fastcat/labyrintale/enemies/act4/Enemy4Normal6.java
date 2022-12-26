package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AllTwoE;
import com.fastcat.labyrintale.skills.enemy.FrailStrongE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy4Normal6 extends AbstractEnemy {

    private static final String ID = "Enemy4Normal6";
    private static final EnemyType TYPE = EnemyType.NORMAL;
    private static final int HEALTH = 60;

    public Enemy4Normal6() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 15;
        stat.debuRes = 10;
        stat.neutRes = 10;
        stat.moveRes = 10;
    }

    @Override
    public void preBattle() {
        applyStatus(
                new PunishStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 2 : 1),
                this,
                AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 2 : 1,
                false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new FrailStrongE(this).upgrade().upgrade();
        s.disposable = true;
        temp.add(s);
        AbstractSkill s1 = new AllTwoE(this);
        s1.upgrade();
        s1.upgrade();
        temp.add(s1.upgrade());
        temp.add(new FuryE(this).upgrade());
        return temp;
    }
}
