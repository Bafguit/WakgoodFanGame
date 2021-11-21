package com.fastcat.labyrintale.abstracts;

public abstract class AbstractPlayer extends AbstractEntity {

    public PlayerClass playerClass;
    public AbstractSkill[] skills = new AbstractSkill[4];

    public AbstractPlayer(String id, PlayerClass playerClass, int attackValue, int spellValue, int maxHealth) {
        super(id, EntityType.PLAYER, 4, attackValue, spellValue, maxHealth);
        this.playerClass = playerClass;
    }

    public void update() {

    }

    public enum PlayerClass {
        TEST, BASIC, SAJANG;
    }
}
