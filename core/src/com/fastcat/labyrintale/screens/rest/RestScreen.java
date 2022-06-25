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
    public RestEndButton end;

    public RestScreen() {
        cType = ControlPanel.ControlType.BASIC;
        count = 2;
        end = new RestEndButton();
        end.disable();
        boolean rev = false, item = false;
        for(AbstractPlayer p : AbstractLabyrinth.players) {
            if(!rev && !p.isAlive()) {
                rev = true;
                count++;
            }
            for(AbstractItem i : p.item) {
                if(!item && i.id.equals("삽 같은거")) { //TODO 아이템 만들어서 ID 넣기
                    item = true;
                    count++;
                }
            }
        }

        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        float tw = w / (count + 1), tww = tw;

        RestButton b = buttons[0] = new RestButton(this, RestButton.RestType.HEAL);
        b.setPosition(tw - b.sWidth / 2, h * 0.73f - b.sHeight / 2);

        RestIcon c = icons[0] = new RestIcon(b, getImg(b.type));
        c.setPosition(tw - c.sWidth / 2, h * 0.85f - c.sHeight / 2);

        RestDesc d = desc[0] = new RestDesc("휴식");
        d.setPosition(tw - d.sWidth / 2, h * 0.6f - d.sHeight / 2);

        tw += tww;

        RestButton b2 = buttons[1] = new RestButton(this, RestButton.RestType.UPGRADE);
        b2.setPosition(tw - b2.sWidth / 2, h * 0.73f - b2.sHeight / 2);

        RestIcon c2 = icons[1] = new RestIcon(b2, getImg(b2.type));
        c2.setPosition(tw - c2.sWidth / 2, h * 0.85f - c2.sHeight / 2);

        RestDesc d2 = desc[1] = new RestDesc("단련");
        d2.setPosition(tw - d2.sWidth / 2, h * 0.6f - d2.sHeight / 2);

        if(rev) {
            tw += tww;

            RestButton b3 = buttons[1] = new RestButton(this, RestButton.RestType.REVIVE);
            b2.setPosition(tw - b3.sWidth / 2, h * 0.73f - b3.sHeight / 2);

            RestIcon c3 = icons[1] = new RestIcon(b2, getImg(b2.type));
            c2.setPosition(tw - c3.sWidth / 2, h * 0.85f - c3.sHeight / 2);

            RestDesc d3 = desc[1] = new RestDesc("소생");
            d2.setPosition(tw - d3.sWidth / 2, h * 0.6f - d3.sHeight / 2);
        }

        if(item) {
            tw += tww;

            RestButton b3 = buttons[1] = new RestButton(this, RestButton.RestType.DISCOVER);
            b2.setPosition(tw - b3.sWidth / 2, h * 0.73f - b3.sHeight / 2);

            RestIcon c3 = icons[1] = new RestIcon(b2, getImg(b2.type));
            c2.setPosition(tw - c3.sWidth / 2, h * 0.85f - c3.sHeight / 2);

            RestDesc d3 = desc[1] = new RestDesc("탐색");
            d2.setPosition(tw - d3.sWidth / 2, h * 0.6f - d3.sHeight / 2);
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < count; i++) {
            buttons[i].update();
            icons[i].update();
            desc[i].update();
        }
        end.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for(int i = 0; i < count; i++) {
            buttons[i].render(sb);
            icons[i].render(sb);
            desc[i].render(sb);
        }
        end.render(sb);
    }

    public static Sprite getImg(RestButton.RestType type) {
        switch (type) {
            case HEAL:
                return FileHandler.skillImg.get("Heal"); //TODO 이미지 변경
            case UPGRADE:
                return FileHandler.skillImg.get("Light");
            default:
                return FileHandler.ui.get("REWARD_CARD");
        }
    }

    public void finishRest() {
        for(int i = 0; i < count; i++) {
            buttons[i].disable();
            icons[i].disable();
            desc[i].disable();
        }
        end.enable();
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
