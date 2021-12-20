package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;

public abstract class AbstractPlayer extends AbstractEntity {

    public PlayerClass playerClass;

    public AbstractPlayer(String id, PlayerClass playerClass, int maxHealth, TextureAtlas atlas, FileHandle skel) {
        super(id, EntityType.PLAYER, 4, maxHealth, atlas, skel);
        this.playerClass = playerClass;
    }

    public void update() {

    }

    public enum PlayerClass {
        TEST, BASIC, WAKDU, SAJANG, BABY;
    }

    public static String getClassName(PlayerClass playerClass) {
        return playerClass.toString().toLowerCase();
    }
}
