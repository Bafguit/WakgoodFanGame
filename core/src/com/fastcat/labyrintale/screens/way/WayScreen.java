package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractWay;
import com.fastcat.labyrintale.screens.deckview.BgImg;

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
        wayCount = way.choices.length;
        buttons = new WaySelectButton[wayCount];
        icons = new WayIcon[wayCount];
        desc = new WayDesc[wayCount];
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < wayCount; i++) {
            float tw = w / (wayCount + 1) * (i + 1);
            WayIcon c = icons[i] = new WayIcon(way.img);
            c.setPosition(tw - c.sWidth / 2, h * 0.85f - c.sHeight / 2);

            WayDesc d = desc[i] = new WayDesc(way.desc);
            d.setPosition(tw - d.sWidth / 2, h * 0.55f - d.sHeight / 2);

            WaySelectButton b = buttons[i] = new WaySelectButton(this, c, d, way.choices[i]);
            b.setPosition(tw - b.sWidth / 2, h * 0.7f - b.sHeight / 2);
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
