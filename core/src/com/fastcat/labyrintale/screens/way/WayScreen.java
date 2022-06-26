package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractChoice;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractWay;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class WayScreen extends AbstractScreen {

    public BgImg bg = new BgImg();
    public int wayCount;
    public AbstractWay way;
    public WaySelectButton[] buttons;
    public WayIcon[] icons;
    public WayDesc[] desc;

    public WayScreen() {
        this(AbstractLabyrinth.currentFloor.currentWay);
    }

    public WayScreen(AbstractWay wy) {
        way = wy;
        cType = ControlPanel.ControlType.BASIC;
        wayCount = way.choices.length;
        buttons = new WaySelectButton[wayCount];
        icons = new WayIcon[wayCount];
        desc = new WayDesc[wayCount];
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < wayCount; i++) {
            float tw = w / (wayCount + 1) * (i + 1);
            AbstractChoice ch = way.choices[i];

            WaySelectButton b = buttons[i] = new WaySelectButton(this, ch);
            b.setPosition(tw - b.sWidth / 2, h * 0.73f - b.sHeight / 2);

            //TODO 음산함 60 이상이면 물음표 표시
            WayIcon c = icons[i] = new WayIcon(b, ch.img);
            c.setPosition(tw - c.sWidth / 2, h * 0.85f - c.sHeight / 2);

            WayDesc d = desc[i] = new WayDesc(ch.desc);
            d.setPosition(tw - d.sWidth / 2, h * 0.7f - d.sHeight / 2);
        }
    }

    @Override
    public void update() {
        for(int i = 0; i < wayCount; i++) {
            buttons[i].update();
            icons[i].update();
            desc[i].update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for(int i = 0; i < wayCount; i++) {
            buttons[i].render(sb);
            icons[i].render(sb);
            desc[i].render(sb);
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
