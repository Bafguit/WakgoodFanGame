package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;

public class Enemy4Weak8 extends AbstractEnemy {

    private static final String ID = "Enemy4Weak8";
    private static final EnemyType TYPE = EnemyType.WEAK;
    private static final int HEALTH = 50;

    public Enemy4Weak8() {
        super(ID, TYPE, HEALTH);
        stat.speed = 1;
        stat.attack = 4;
        stat.spell = 4;
        stat.debuRes = 10;
        stat.neutRes = 20;
        stat.critical = 20;
        stat.moveRes = 20;
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        temp.add(new StrikeE(this));
        temp.add(new GuardE(this));
        temp.add(new UnblockE(this));
        temp.add(new GrowE(this));
        return temp;
    }
}
