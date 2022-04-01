package com.fastcat.labyrintale.screens.rest;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.deckview.BgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class RestScreen extends AbstractScreen {

    public BgImg bg = new BgImg();
    public int count;
    public RestButton[] buttons = new RestButton[2];
    public RestIcon[] icons = new RestIcon[2];
    public RestDesc[] desc = new RestDesc[2];

    public RestScreen() {
        cType = ControlPanel.ControlType.BASIC;
        count = 2;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        float tw = w / (count + 1);

        RestButton b = buttons[0] = new RestButton(this, RestButton.RestType.HEAL);
        b.setPosition(tw - b.sWidth / 2, h * 0.73f - b.sHeight / 2);

        RestIcon c = icons[0] = new RestIcon(b, getImg(b.type));
        c.setPosition(tw - c.sWidth / 2, h * 0.85f - c.sHeight / 2);

        RestDesc d = desc[0] = new RestDesc("테스트");
        d.setPosition(tw - d.sWidth / 2, h * 0.6f - d.sHeight / 2);

        tw = w / (count + 1) * 2;

        RestButton b2 = buttons[1] = new RestButton(this, RestButton.RestType.UPGRADE);
        b2.setPosition(tw - b2.sWidth / 2, h * 0.73f - b2.sHeight / 2);

        RestIcon c2 = icons[1] = new RestIcon(b2, getImg(b2.type));
        c2.setPosition(tw - c2.sWidth / 2, h * 0.85f - c2.sHeight / 2);

        RestDesc d2 = desc[1] = new RestDesc("강화");
        d2.setPosition(tw - d2.sWidth / 2, h * 0.6f - d2.sHeight / 2);
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
