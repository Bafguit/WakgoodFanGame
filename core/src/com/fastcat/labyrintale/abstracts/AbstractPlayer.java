package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.CharString;

public abstract class AbstractPlayer extends AbstractEntity {

    public final Color pColor;
    public final Color pColorW;
    public final Color pColorLG;
    public final Color pColorDG;
    public final PlayerClass playerClass;
    public String[] flavour;

    public AbstractPlayer(String id, int maxHealth, TextureAtlas atlas, FileHandle skel, Color c) {
        super(id, EntityType.PLAYER, 4, maxHealth, atlas, skel);
        this.playerClass = PlayerClass.valueOf(id.toUpperCase());
        CharString.CharData temp = StringHandler.charString.get(id);
        name = temp.NAME;
        desc = temp.DESC;
        flavour = temp.FLV;
        pColor = c.cpy();
        pColorW = c.cpy().mul(0.827f, 0.827f, 0.827f, 1);
        pColorLG = c.cpy().mul(0.663f, 0.663f, 0.663f, 1);
        pColorDG = c.cpy().mul(0.5f, 0.5f, 0.5f, 1);
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
