package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.events.choices.AtEndOfTempScreen;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public abstract class AbstractScreen implements Screen, AtEndOfTempScreen, Disposable {

    public final EffectHandler effectHandler = new EffectHandler();
    public ControlPanel.ControlType cType = ControlPanel.ControlType.BASIC;
    public ScreenType type = ScreenType.OTHER;
    private Sprite bg;
    public Array<AtEndOfTempScreen> endTemp = new Array<>();

    public abstract void update();

    public abstract void render(SpriteBatch sb);

    @Override
    public final void render(float delta) {
        if(bg != null) bg.draw(Labyrintale.game.sb);
        render(Labyrintale.game.sb);
        effectHandler.render(Labyrintale.game.sb);
    }

    public final void setBg(Sprite s) {
        bg = s;
        bg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public final void resize(int width, int height) {

    }

    @Override
    public final void pause() {

    }

    @Override
    public final void resume() {

    }

    @Override
    public final void atEndOfTempScreen() {
        for(AtEndOfTempScreen e : endTemp) {
            if(e != null) e.atEndOfTempScreen();
        }
    }

    @Override
    public void hide () {

    }

    @Override
    public void dispose() {
        effectHandler.removeAll();
    }

    public enum ScreenType {
        MAP, OTHER
    }
}
