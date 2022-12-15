package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.PunishStatus;

public class Enemy4Weak3 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak3";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 34;

    public Enemy4Weak3() {
        super(ID, TYPE, HEALTH);
        stat.speed = 4;
        stat.critical = 30;
        stat.debuRes = 5;
        stat.neutRes = 20;
        stat.moveRes = 5;
    }

    @Override
    public void preBattle() {
        applyStatus(new PunishStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3),
                this, AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 4 : 3, false);
        applyStatus(new AttackStatus(3), this, 3, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new AttackLowE(this).upgrade().upgrade().upgrade());
        temp.add(new AttackHighE(this).upgrade().upgrade().upgrade());
        temp.add(new CounterE(this).upgrade());
        return temp;
    }
}
