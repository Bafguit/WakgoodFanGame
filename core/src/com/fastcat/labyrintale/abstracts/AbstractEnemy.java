package com.fastcat.labyrintale.abstracts;

public abstract class AbstractEnemy extends AbstractEntity {

    public AbstractEnemy(String id, EnemyType type, int attackValue, int spellValue, int maxHealth) {
        super(id, EntityType.ENEMY, getHandSize(type), attackValue, spellValue, maxHealth);
    }

    private static int getHandSize(EnemyType type) {
        switch (type) {
            case ELITE:
                return 2;
            case BOSS:
                return 3;
            default:
                return 1;
        }
    }

    public enum EnemyType {
        WEAK, STRONG, ELITE, BOSS
    }
}
