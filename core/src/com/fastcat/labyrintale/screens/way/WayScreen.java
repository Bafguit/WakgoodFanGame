package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractWay;

public class WayScreen extends AbstractScreen {

    public int wayCount;
    public AbstractWay way;

    public WayScreen(AbstractWay w) {
        way = w;
        wayCount = way.rooms.length;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

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
