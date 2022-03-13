package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.deckview.BgImg;

public class RestScreen extends AbstractScreen {

    public BgImg bg = new BgImg();
    public int count;
    public RestButton[] buttons = new RestButton[2];
    public RestIcon[] icons = new RestIcon[2];
    public RestDesc[] desc = new RestDesc[2];

    public RestScreen() {
        count = 2;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < count; i++) {
            float tw = w / (count + 1) * (i + 1);

            RestButton b = buttons[i] = new RestButton(this, RestButton.RestType.HEAL);
            b.setPosition(tw - b.sWidth / 2, h * 0.7f - b.sHeight / 2);

            RestIcon c = icons[i] = new RestIcon(b, getImg(b.type));
            c.setPosition(tw - c.sWidth / 2, h * 0.85f - c.sHeight / 2);

            RestDesc d = desc[i] = new RestDesc("테스트");
            d.setPosition(tw - d.sWidth / 2, h * 0.55f - d.sHeight / 2);
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < count; i++) {
            buttons[i].update();
            icons[i].update();
            desc[i].update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for(int i = 0; i < count; i++) {
            buttons[i].render(sb);
            icons[i].render(sb);
            desc[i].render(sb);
        }
    }

    public static Sprite getImg(RestButton.RestType type) {
        switch (type) {
            case HEAL:
                return FileHandler.SKILL_HEAL; //TODO 이미지 변경
            case UPGRADE:
                return FileHandler.SKILL_LIGHT;
            default:
                return FileHandler.REWARD_CARD;
        }
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
