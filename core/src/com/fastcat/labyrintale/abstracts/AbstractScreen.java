package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import lombok.Getter;

public abstract class AbstractScreen implements Screen, AtEndOfTempScreen, Disposable {

    public ControlPanel.ControlType cType = ControlPanel.ControlType.BASIC;
    public ScreenType type = ScreenType.OTHER;
    public Array<AtEndOfTempScreen> endTemp = new Array<>();
    public boolean playMusic = false;
    private Sprite bg;

    @Getter
    private final EffectHandler effectHandler = EffectHandler.newInstance();

    public final void updateAll() {
        update();
        effectHandler.update();
    }

    public abstract void update();

    public abstract void render(SpriteBatch sb);

    @Override
    public final void render(float delta) {
        if (bg != null) bg.draw(Labyrintale.game.sb);
        render(Labyrintale.game.sb);
        effectHandler.render(Labyrintale.game.sb);
    }

    public final void setBg(Sprite s) {
        bg = s;
        bg.setBounds(0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());
    }

    @Override
    public final void resize(int width, int height) {}

    public void onCreate() {}

    @Override
    public final void pause() {}

    @Override
    public final void resume() {}

    @Override
    public final void atEndOfTempScreen() {
        for (AtEndOfTempScreen e : endTemp) {
            if (e != null) e.atEndOfTempScreen();
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        effectHandler.removeAll();
    }

    public enum ScreenType {
        MAP,
        OTHER,
        LOAD
    }
}
