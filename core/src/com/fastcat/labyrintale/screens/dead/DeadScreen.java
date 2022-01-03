package com.fastcat.labyrintale.screens.dead;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractScreen;

public class DeadScreen extends AbstractScreen {

    public DeadText logo;
    public MainButton mainButton;

    public DeadScreen(ScreenType type) {
        logo = new DeadText();
        if(type == ScreenType.WIN) {
            logo.text = "개같이 멸망";
        } else {
            logo.text = "해냈다 해냈어!";
        }
        mainButton = new MainButton();
    }

    @Override
    public void update() {
        mainButton.update();
        logo.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        mainButton.render(sb);
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

    public enum ScreenType{
        DEAD, WIN
    }
}
