package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;

import static com.fastcat.labyrintale.abstracts.AbstractPlayer.PlayerClass.*;

public class CharSelectScreen extends AbstractScreen {

    public CharSelectText charSelectText;
    public BackButton backButton;
    public NextToAdvisorButton nextButton;
    public CharButton[] chars = new CharButton[4];
    public CharButton[] aChars = new CharButton[9];

    public CharSelectScreen(Labyrintale game) {
        super(game);
        charSelectText = new CharSelectText();
        backButton = new BackButton();
        nextButton = new NextToAdvisorButton();
        CharButton char1 = new CharButton();
        CharButton char2 = new CharButton();
        CharButton char3 = new CharButton();
        CharButton char4 = new CharButton();
        char1.setPosition(Gdx.graphics.getWidth() * 0.125f - char1.sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        char2.setPosition(Gdx.graphics.getWidth() * 0.375f - char2.sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        char3.setPosition(Gdx.graphics.getWidth() * 0.625f - char3.sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        char4.setPosition(Gdx.graphics.getWidth() * 0.875f - char4.sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        chars[0] = char1;
        chars[1] = char2;
        chars[2] = char3;
        chars[3] = char4;
        addChars();
    }

    private void addChars() {
        CharButton char1 = new CharButton(BASIC);
        char1.setPosition(Gdx.graphics.getWidth() * 0.1f - char1.sWidth / 2, Gdx.graphics.getHeight() * 0.1f);
        aChars[0] = char1;
        char1 = new CharButton(SAJANG);
        char1.setPosition(Gdx.graphics.getWidth() * 0.2f - char1.sWidth / 2, Gdx.graphics.getHeight() * 0.1f);
        aChars[1] = char1;

        for(int i = 2; i < 9; i++) {
            char1 = new CharButton(BASIC);
            char1.setPosition(Gdx.graphics.getWidth() * 0.1f * (i + 1) - char1.sWidth / 2, Gdx.graphics.getHeight() * 0.1f);
            aChars[i] = char1;
        }
    }

    @Override
    public void update() {
        int cc = 0;
        for(int i = 0; i < chars.length; i++) {
            CharButton tc = chars[i];
            tc.update();
            if(tc.isOnLock) cc++;
        }
        for(int i = 0; i < aChars.length; i++) {
            aChars[i].update();
        }

        if(cc == 4) nextButton.enable();
        else nextButton.disable();

        backButton.update();
        nextButton.update();
        charSelectText.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for(int i = 0; i < chars.length; i++) {
            chars[i].render(sb);
        }
        for(int i = 0; i < aChars.length; i++) {
            aChars[i].render(sb);
        }

        backButton.render(sb);
        nextButton.render(sb);
        charSelectText.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(int i = 0; i < chars.length; i++) {
            chars[i].dispose();
        }
        for(int i = 0; i < aChars.length; i++) {
            aChars[i].dispose();
        }
    }
}
