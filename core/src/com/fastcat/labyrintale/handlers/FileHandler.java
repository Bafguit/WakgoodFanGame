package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.TextureAtlas;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

import java.io.File;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class FileHandler {

    public static final Sprite FADE = new Sprite(new Texture("img/fade.png"));
    public static final Texture CARD_BG = new Texture("card.png");
    public static final Texture CARD_PANEL = new Texture("cardPanel.png");
    public static final Texture MENU_SELECT = new Texture("img/menuSelect.png");
    public static final Texture CHAR_SELECT = new Texture("img/charSelect.png");
    public static final Texture BACK = new Texture("img/back.png");
    public static final Texture NEXT = new Texture("img/next.png");
    public static final Texture WAK_BASIC = new Texture("img/wakdu/basic.png");
    public static final Texture WAK_BASIC_BG = new Texture("img/wakdu/basic_bg.png");
    public static final Texture WAK_SAJANG = new Texture("img/wakdu/sajang.png");
    public static final Texture WAK_SAJANG_BG = new Texture("img/wakdu/sajang_bg.png");
    public static final Texture WAK_BABY = new Texture("img/wakdu/baby.png");
    public static final Texture WAK_BABY_BG = new Texture("img/wakdu/baby_bg.png");
    public static final Texture ENTITY_POINT = new Texture("img/entityPoint.png");
    public static final Texture PLAYER_POINT = new Texture("img/playerPoint.png");
    public static final TextureAtlas NEKO_ATLAS = new TextureAtlas("spine/test/char_5_neko.atlas");
    public static final FileHandle NEKO_JSON = Gdx.files.internal("spine/test/char_5_neko.json");

    public FileHandler() {

    }

    public static Texture getCardBg(PlayerClass playerClass) {
        return new Texture("spell_1024.png");
    }
}
