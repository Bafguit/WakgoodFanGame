package com.fastcat.labyrintale.enemies.act4;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEnemy;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.enemy.*;
import com.fastcat.labyrintale.status.AttackStatus;
import com.fastcat.labyrintale.status.ImmuneStatus;
import com.fastcat.labyrintale.status.PunishStatus;

public class BossEnemy4 extends AbstractEnemy {

    private static final String ID = "BossEnemy4";
    private static final EnemyType TYPE = EnemyType.BOSS;
    private static final int HEALTH = 365;

    public BossEnemy4() {
        super(ID, TYPE, HEALTH);
        isRandom = false;
    }

    @Override
    public void preBattle() {
        applyStatus(new AttackStatus(2), 2, false);
        applyStatus(new ImmuneStatus(5), 5, false);
    }

    @Override
    public Array<AbstractSkill> getStartingDeck() {
        Array<AbstractSkill> temp = new Array<>();
        /////////////////////////
        AbstractSkill s = new AdjudgeE(this);
        for(int i = 0; i < 10; i++) {
            s.upgrade();
        }
        s.disposable = true;
        temp.add(s);
        /////////////////////////
        AbstractSkill s2 = new StrikeE(this);
        for(int i = 0; i < 10; i++) {
            s2.upgrade();
        }
        temp.add(s2);
        /////////////////////////
        AbstractSkill s6 = new DecayE(this);
        s6.upgrade();
        s6.upgrade();
        temp.add(s6);
        /////////////////////////
        temp.add(new InduceE(this));
        /////////////////////////
        AbstractSkill ss = new DoubleE(this);
        for(int i = 0; i < 3; i++) {
            ss.upgrade();
        }
        temp.add(ss);
        /////////////////////////
        temp.add(new RecapE(this));
        /////////////////////////
        temp.add(new HinderAllE(this));
        /////////////////////////
        temp.add(new RestrictE(this));
        /////////////////////////
        AbstractSkill s4 = new GrowE(this);
        temp.add(s4);
        return temp;
    }

    @Override
    public void atEndOfRound() {

    }
}
