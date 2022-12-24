package com.fastcat.labyrintale.screens.rest;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.GifBg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class RestScreen extends AbstractScreen {

    public final GifBg fire;
    public int count;
    public RestButton[] buttons;
    public RestDesc[] desc;
    public AbstractUI.TempUI[] chars = new AbstractUI.TempUI[4];
    public RestEndButton end;
    public int alive = 0;

    public RestScreen() {
        setBg(FileHandler.getBg().get("BG_REST_LIGHT"));
        fire = new GifBg(FileHandler.getBg().get("BG_REST_BAG"), "FIRE");
        cType = ControlPanel.ControlType.BASIC;
        count = 2;
        end = new RestEndButton();
        end.disable();
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            if (!p.isAlive()) {
                count++;
                break;
            }
        }
        for (int i = 0; i < 4; i++) {
            if (AbstractLabyrinth.players[i].isAlive()) alive++;
            chars[i] = new AbstractUI.TempUI(FileHandler.getUi().get("CAMP"));
        }

        chars[0].setPosition(670 * scale, 600 * scale);
        chars[0].img.setFlip(false, false);
        chars[1].setPosition(920 * scale, 654 * scale);
        chars[1].img.setFlip(false, false);
        chars[2].setPosition(1224 * scale, 654 * scale);
        chars[2].img.setFlip(true, false);
        chars[3].setPosition(1474 * scale, 600 * scale);
        chars[3].img.setFlip(true, false);

        for (int i = 0; i < 4; i++) {
            AbstractPlayer p = AbstractLabyrinth.players[i];
            chars[i].img = alive > 2 ? p.camp : p.upset;
        }

        buttons = new RestButton[count];
        desc = new RestDesc[count];

        float w = Gdx.graphics.getWidth(), h = 1440 * InputHandler.scale;
        float tw = w / 6, tww = w / 3;
        int cnt = 0;

        RestButton b = buttons[cnt] = new RestButton(this, RestButton.RestType.HEAL);
        b.setPosition(tw - b.sWidth / 2, h * 0.7f - b.sHeight / 2);

        RestDesc d = desc[cnt] = new RestDesc("휴식", b);
        d.setPosition(tw - d.sWidth / 2, b.y - 100 * InputHandler.scale);

        if (count > 2) {
            tw += tww;
            cnt++;

            RestButton b3 = buttons[cnt] = new RestButton(this, RestButton.RestType.REVIVE);
            b3.setPosition(tw - b3.sWidth / 2, h * 0.77f - b3.sHeight / 2);

            RestDesc d3 = desc[cnt] = new RestDesc("소생", b3);
            d3.setPosition(tw - d3.sWidth / 2, b3.y - 100 * InputHandler.scale);
        } else {
            tw += tww;
        }

        tw += tww;
        cnt++;

        RestButton b2 = buttons[cnt] = new RestButton(this, RestButton.RestType.UPGRADE);
        b2.setPosition(tw - b2.sWidth / 2, h * 0.7f - b2.sHeight / 2);

        RestDesc d2 = desc[cnt] = new RestDesc("단련", b2);
        d2.setPosition(tw - d2.sWidth / 2, b2.y - 100 * InputHandler.scale);
    }

    @Override
    public void update() {
        for (int i = 0; i < 4; i++) {
            AbstractPlayer p = AbstractLabyrinth.players[i];
            AbstractUI.TempUI u = chars[i];
            u.img = alive > 2 ? p.camp : p.upset;
            u.showImg = p.isAlive();
            u.img.setFlip(i > 1, false);
            u.update();
        }
        for (int i = 0; i < count; i++) {
            buttons[i].update();
            desc[i].update();
        }
        end.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (int i = 0; i < count; i++) {
            buttons[i].render(sb);
            desc[i].render(sb);
        }
        for (int i = 0; i < 4; i++) {
            chars[i].render(sb);
        }
        fire.render(sb);
        end.render(sb);
    }

    public void finishRest() {
        for (int i = 0; i < count; i++) {
            buttons[i].disable();
            desc[i].disable();
        }
        end.enable();
    }

    @Override
    public void show() {
        SoundHandler.addWay().stop = false;
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {}
}
