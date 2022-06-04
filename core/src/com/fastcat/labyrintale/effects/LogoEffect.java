package com.fastcat.labyrintale.effects;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.handlers.InputHandler;

public class LogoEffect extends AbstractEffect {
    public LogoEffect() {
        super(0, 0, 4f);
    }

    @Override
    protected void renderEffect(SpriteBatch sb) {
        if(InputHandler.isLeftClick && !isDone) isDone = true;
        if(isDone) Labyrintale.fadeOutAndChangeScreen(Labyrintale.mainMenuScreen, 2);
    }
}
