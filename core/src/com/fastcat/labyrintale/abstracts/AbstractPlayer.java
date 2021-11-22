package com.fastcat.labyrintale.abstracts;

public abstract class AbstractPlayer extends AbstractEntity {

    public PlayerClass playerClass;

    public AbstractPlayer(String id, PlayerClass playerClass, int maxHealth) {
        super(id, EntityType.PLAYER, maxHealth);
        this.playerClass = playerClass;
    }

    public void update() {

    }

    public enum PlayerClass {
        TEST, BASIC, SAJANG, BABY;
    }
}
