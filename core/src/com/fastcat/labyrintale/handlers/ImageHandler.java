package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.*;

public class ImageHandler {

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

    public ImageHandler() {

    }

    public static Texture getCardBg(PlayerClass playerClass) {
        return new Texture("spell_1024.png");
    }
}
