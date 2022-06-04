package com.fastcat.labyrintale.screens.logo;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.effects.LogoEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.deckview.BgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class LogoScreen extends AbstractScreen {

    public LogoImage logo;

    public LogoScreen() {
        cType = ControlPanel.ControlType.HIDE;
        setBg(FileHandler.ui.get("FADE"));
        logo = new LogoImage();
        effectHandler.effectList.add(new LogoEffect());
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {
        logo.render(sb);
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
