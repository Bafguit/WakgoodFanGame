package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.BarrierE;
import com.fastcat.labyrintale.skills.enemy.ChargeE;
import com.fastcat.labyrintale.skills.enemy.GrowE;
import com.fastcat.labyrintale.skills.enemy.StrikeE;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.ImmuneStatus;

public class Enemy4Weak7 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak7";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 50;

    public Enemy4Weak7() {
        super(ID, TYPE, HEALTH);
        stat.speed = 2;
        stat.debuRes = 10;
        stat.neutRes = 15;
        stat.critical = 20;
        stat.moveRes = 20;
    }

    @Override
    public void preBattle() {
        applyStatus(new AttackStatus(2));
        applyStatus(new ImmuneStatus(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.COFFIN ? 3 : 2));
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        AbstractSkill s = new StrikeE(this);
        s.upgrade();
        temp.add(s);
        temp.add(new GrowE(this).upgrade());
        temp.add(new ChargeE(this).upgrade().upgrade());
        AbstractSkill ss = new BarrierE(this);
        ss.upgrade();
        temp.add(ss);
        return temp;
    }
}
