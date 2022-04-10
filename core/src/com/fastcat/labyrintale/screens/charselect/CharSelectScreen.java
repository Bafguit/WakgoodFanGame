package com.fastcat.labyrintale.screens.charselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;

public class CharSelectScreen extends AbstractScreen {

    public CharSelectText charSelectText;
    public BackButton backButton;
    public NextToAdvisorButton nextButton;
    public CharButton[] chars = new CharButton[4];
    public CharButton[] aChars = new CharButton[8];

    public CharSelectScreen() {
        setBg(FileHandler.BG_CHARSELECT);
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
        AbstractPlayer.PlayerClass[] pc = AbstractPlayer.PlayerClass.values();
        for(int i = 0; i < 8; i++) {
            CharButton char0 = new CharButton(pc[i]);
            char0.setPosition(Gdx.graphics.getWidth() * 0.15f + Gdx.graphics.getWidth() * 0.1f * i - char0.sWidth / 2, Gdx.graphics.getHeight() * 0.125f);
            aChars[i] = char0;
        }
    }

    @Override
    public void update() {
        int cc = 0;
        for(int i = 0; i < 4; i++) {
            CharButton tc = chars[i];
            tc.update();
            if(tc.isOnLock) cc++;
        }
        for(int i = 0; i < 8; i++) {
            aChars[i].update();
        }

        if(cc == 4) {
            nextButton.enable();
            charSelectText.text = chars[0].charData.FLV[0] + " " + chars[1].charData.FLV[1] + " " + chars[2].charData.FLV[2] + " " + chars[3].charData.FLV[3];
        }
        else {
            nextButton.disable();
            charSelectText.text = CharSelectText.TEXT;
        }

        backButton.update();
        nextButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (CharButton aChar : chars) {
            aChar.render(sb);
        }
        for(int i = 0; i < 8; i++) {
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
        for (CharButton aChar : chars) {
            aChar.dispose();
        }
        for(int i = 0; i < 8; i++) {
            aChars[i].dispose();
        }
    }
}
