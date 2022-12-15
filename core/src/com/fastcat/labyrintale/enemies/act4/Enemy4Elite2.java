package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.ArmourStatus;

public class Enemy4Elite2 extends AbstractEnemy {

    private static final String ID = "Enemy4Elite2";
    private static final EnemyType TYPE = EnemyType.ELITE;
    private static final int HEALTH = 107;

    public Enemy4Elite2() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
        stat.speed = 2;
        stat.critical = 5;
        stat.debuRes = 15;
        stat.neutRes = 15;
        stat.moveRes = 50;
    }

    @Override
    public void preBattle() {
        applyStatus(new ArmourStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 15 : 10),
                this, AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 15 : 10, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new ProtectE(this);
        for (int i = 0; i < 7; i++) {
            s.upgrade();
        }
        temp.add(s);
        AbstractSkill s1 = new SlashE(this);
        for (int i = 0; i < 6; i++) {
            s1.upgrade();
        }
        temp.add(s1);
        temp.add(new ImpregE(this));
        temp.add(new GrowE(this));
        return temp;
    }
}
