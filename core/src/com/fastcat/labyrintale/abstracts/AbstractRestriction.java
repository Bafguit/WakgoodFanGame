package com.fastcat.labyrintale.abstracts;

import java.io.Serializable;

public abstract class AbstractRestriction {

    public static final int LEVEL_SUPPORT = 0;
    public static final int LEVEL_EASY = 1;
    public static final int LEVEL_NORMAL = 2;
    public static final int LEVEL_HARD = 3;

    public String id;
    public String name;
    public String desc;
    public int level;

    public AbstractRestriction(String id, int level) {
        this.id = id;
        this.level = level;
    }

    public void onEnemySpawn(AbstractEnemy enemy) {

    }

    public void atBattleBegin() {

    }

    public void atBattleEnd() {

    }
}
