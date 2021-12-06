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
    public static final Sprite CARD_BG = new Sprite(new Texture("card.png"));
    public static final Sprite MENU_SELECT = new Sprite(new Texture("img/menuSelect.png"));
    public static final Sprite CHAR_SELECT = new Sprite(new Texture("img/charSelect.png"));
    public static final Sprite BACK = new Sprite(new Texture("img/back.png"));
    public static final Sprite NEXT = new Sprite(new Texture("img/next.png"));
    public static final Sprite WAK_BASIC = new Sprite(new Texture("img/wakdu/basic.png"));
    public static final Sprite WAK_BASIC_BG = new Sprite(new Texture("img/wakdu/basic_bg.png"));
    public static final Sprite WAK_SAJANG = new Sprite(new Texture("img/wakdu/sajang.png"));
    public static final Sprite WAK_SAJANG_BG = new Sprite(new Texture("img/wakdu/sajang_bg.png"));
    public static final Sprite WAK_BABY = new Sprite(new Texture("img/wakdu/baby.png"));
    public static final Sprite WAK_BABY_BG = new Sprite(new Texture("img/wakdu/baby_bg.png"));
    public static final Sprite ENTITY_POINT = new Sprite(new Texture("img/entityPoint.png"));
    public static final Sprite PLAYER_POINT = new Sprite(new Texture("img/playerPoint.png"));
    public static final TextureAtlas NEKO_ATLAS = new TextureAtlas("spine/test/char_5_neko.atlas");
    public static final FileHandle NEKO_JSON = Gdx.files.internal("spine/test/char_5_neko.json");

    public static final Sprite SKILL_STRIKE = new Sprite(new Texture("img/skill/Strike.png"));
    public static final Sprite SKILL_HEAL = new Sprite(new Texture("img/skill/Heal.png"));
    public static final Sprite SKILL_LIGHT = new Sprite(new Texture("img/skill/Light.png"));
    public static final Sprite SKILL_LOVE = new Sprite(new Texture("img/skill/Love.png"));
    public static final Sprite SKILL_POISON = new Sprite(new Texture("img/skill/Poison.png"));
    public static final Sprite SKILL_SHIELD = new Sprite(new Texture("img/skill/Shield.png"));

    public FileHandler() {

    }

    public static Texture getCardBg(PlayerClass playerClass) {
        return new Texture("spell_1024.png");
    }
}
