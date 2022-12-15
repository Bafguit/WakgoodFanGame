package com.fastcat.labyrintale.enemies.act2;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy2Elite1 extends AbstractEnemy {

    private static final String ID = "Enemy2Elite1";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 63;

    public Enemy2Elite1() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 1;
        stat.critical = 25;
        stat.debuRes = 15;
        stat.neutRes = 5;
        stat.moveRes = 5;
    }

    @Override
    public void preBattle() {
        applyStatus(new PunishStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 2 : 1),
                this, AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 2 : 1, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new InduceE(this));
        AbstractSkill s = new FourE(this);
        s.upgrade();
        temp.add(s);
        AbstractSkill s1 = new DualAttackE(this);
        for (int i = 0; i < 4; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        temp.add(new GrowE(this));
        return temp;
    }
}
