package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public abstract class AbstractScreen implements Screen {

    public final EffectHandler effectHandler = new EffectHandler();
    public ControlPanel.ControlType cType = ControlPanel.ControlType.HIDE;
    private Sprite bg;

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
}
