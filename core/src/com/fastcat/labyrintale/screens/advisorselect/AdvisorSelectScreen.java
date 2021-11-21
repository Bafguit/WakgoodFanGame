package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractScreen;

import static com.fastcat.labyrintale.abstracts.AbstractAdvisor.AdvisorClass.*;

public class AdvisorSelectScreen extends AbstractScreen {

    public AdvisorSelectText advisorSelectText;
    public BackToCharButton backButton;
    public NextButton nextButton;
    public AdvisorButton advisor;
    public AdvisorButton[] aAdvisor = new AdvisorButton[9];

    public AdvisorSelectScreen() {
        advisorSelectText = new AdvisorSelectText();
        backButton = new BackToCharButton();
        nextButton = new NextButton();
        advisor = new AdvisorButton();
        advisor.setPosition(Gdx.graphics.getWidth() / 2 - advisor.sWidth / 2, Gdx.graphics.getHeight() * 0.3f);
        addAdvisor();
    }

    private void addAdvisor() {
        for(int i = 0; i < aAdvisor.length; i++) {
            AdvisorButton adv = new AdvisorButton(BURGER);
            adv.setPosition(Gdx.graphics.getWidth() * 0.1f * (i + 1) - adv.sWidth / 2, Gdx.graphics.getHeight() * 0.1f);
            aAdvisor[i] = adv;
        }
    }

    @Override
    public void update() {
        advisor.update();
        for(int i = 0; i < aAdvisor.length; i++) {
            aAdvisor[i].update();
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
        for(int i = 0; i < aAdvisor.length; i++) {
            aAdvisor[i].render(sb);
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

    }

    @Override
    public void dispose() {
        advisor.dispose();
        for(int i = 0; i < aAdvisor.length; i++) {
            aAdvisor[i].dispose();
        }
    }
}
