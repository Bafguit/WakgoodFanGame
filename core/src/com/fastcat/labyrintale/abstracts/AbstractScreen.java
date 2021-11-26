package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.EffectHandler;

public abstract class AbstractScreen implements Screen {

    public final Labyrintale game;
    public final EffectHandler effectHandler = new EffectHandler();

    public AbstractScreen() {
        this(Labyrintale.game);
    }

    public AbstractScreen(Labyrintale game) {
        this.game = game;
    }

    public abstract void update();

    public abstract void render(SpriteBatch sb);

    @Override
    public final void render(float delta) {
        this.render(this.game.sb);
        effectHandler.render(this.game.sb);
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
