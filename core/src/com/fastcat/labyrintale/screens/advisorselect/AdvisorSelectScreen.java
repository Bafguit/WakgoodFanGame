package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass.*;

public class AdvisorSelectScreen extends AbstractScreen {

    public AdvisorSelectText advisorSelectText;
    public BackToCharButton backButton;
    public NextButton nextButton;
    public AdvisorButton advisor;
    public AdvisorButton[] aAdvisor = new AdvisorButton[14];

    public AdvisorSelectScreen() {
        setBg(FileHandler.BG_CHARSELECT);
        advisorSelectText = new AdvisorSelectText();
        backButton = new BackToCharButton();
        nextButton = new NextButton();
        advisor = new AdvisorButton();
        advisor.setPosition(Gdx.graphics.getWidth() * 0.5f - advisor.sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        addAdvisor();
    }

    private void addAdvisor() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        AbstractAdvisor.AdvisorClass[] ac = AbstractAdvisor.AdvisorClass.values();
        for(int i = 0; i < 7; i++) {
            AdvisorButton adv = new AdvisorButton(ac[i]);
            adv.setPosition(w * (0.2f + 0.1f * i) - adv.sWidth / 2, h * 0.25f);
            aAdvisor[i] = adv;
        }
        for(int i = 0; i < 7; i++) {
            AdvisorButton adv = new AdvisorButton(ac[i + 7]);
            adv.setPosition(w * (0.2f + 0.1f * i) - adv.sWidth / 2, h * 0.1f);
            aAdvisor[i + 7] = adv;
        }
    }

    @Override
    public void update() {
        advisor.update();
        for (AdvisorButton advisorButton : aAdvisor) {
            advisorButton.update();
        }
        if(advisor.isOnLock) nextButton.enable();
        else nextButton.disable();
        backButton.update();
        nextButton.update();
        advisorSelectText.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        advisor.render(sb);
        for (AdvisorButton advisorButton : aAdvisor) {
            advisorButton.render(sb);
        }
        backButton.render(sb);
        nextButton.render(sb);
        advisorSelectText.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {
        Labyrintale.advisorSelectScreen.nextButton.disable();
        Labyrintale.advisorSelectScreen.backButton.onHide();
        Labyrintale.advisorSelectScreen.nextButton.onHide();
    }

    @Override
    public void dispose() {
        advisor.dispose();
        for (AdvisorButton advisorButton : aAdvisor) {
            advisorButton.dispose();
        }
    }
}
