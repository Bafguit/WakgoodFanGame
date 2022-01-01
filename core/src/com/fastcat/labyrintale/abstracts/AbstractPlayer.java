package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.CharString;

public abstract class AbstractPlayer extends AbstractEntity {

    public PlayerClass playerClass;
    public String[] flavour;

    public AbstractPlayer(String id, int maxHealth, TextureAtlas atlas, FileHandle skel) {
        super(id, EntityType.PLAYER, 4, maxHealth, atlas, skel);
        this.playerClass = PlayerClass.valueOf(id.toUpperCase());
        CharString.CharData temp = StringHandler.charString.get(id);
        name = temp.NAME;
        desc = temp.DESC;
        flavour = temp.FLV;
    }

    public void update() {

    }

    public enum PlayerClass {
        WAK, MANAGER, INE, VIICHAN, LILPA, BURGER, GOSEGU, JURURU
    }

    public static String getClassName(PlayerClass playerClass) {
        return playerClass.toString().toLowerCase();
    }
}
