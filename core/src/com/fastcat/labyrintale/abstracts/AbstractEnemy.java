package com.fastcat.labyrintale.abstracts;

public abstract class AbstractEnemy extends AbstractEntity {

    public EnemyType type;

    public AbstractEnemy(String id, EnemyType type, int maxHealth) {
        super(id, EntityType.ENEMY, maxHealth);
        this.type = type;
    }

    public enum EnemyType {
        WEAK, STRONG, ELITE, BOSS
    }
}
