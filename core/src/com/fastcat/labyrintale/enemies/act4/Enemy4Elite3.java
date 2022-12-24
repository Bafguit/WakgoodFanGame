package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.AllTwoE;
import com.fastcat.labyrintale.skills.enemy.DecayE;
import com.fastcat.labyrintale.skills.enemy.FourE;
import com.fastcat.labyrintale.skills.enemy.FuryE;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy4Elite3 extends AbstractEnemy {

    private static final String ID = "Enemy4Elite3";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 107;

    public Enemy4Elite3() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 3;
        stat.critical = 15;
        stat.debuRes = 35;
        stat.neutRes = 10;
        stat.moveRes = 20;
    }

    @Override
    public void preBattle() {
        applyStatus(
                new PunishStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 3 : 2),
                this,
                AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 3 : 2,
                false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new FourE(this);
        temp.add(s.upgrade().upgrade());
        AbstractSkill s2 = new DecayE(this);
        s2.upgrade();
        temp.add(s2);
        temp.add(new AllTwoE(this).upgrade().upgrade().upgrade());
        temp.add(new FuryE(this));
        return temp;
    }
}
