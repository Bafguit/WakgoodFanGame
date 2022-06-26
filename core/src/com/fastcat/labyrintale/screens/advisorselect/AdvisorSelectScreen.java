package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.uis.BgImg;

public class AdvisorSelectScreen extends AbstractScreen {

    public BgImg bg = new BgImg();
    public AdvisorSelectText advisorSelectText;
    public NextButton nextButton;
    public AdvisorButton selected;
    public AdvisorButton[] aAdvisor = new AdvisorButton[4];

    public AdvisorSelectScreen() {
        advisorSelectText = new AdvisorSelectText();
        nextButton = new NextButton(this);
        addAdvisor();
    }

    private void addAdvisor() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        Array<AbstractAdvisor> a = GroupHandler.AdvisorGroup.staticShuffle(GroupHandler.AdvisorGroup.sort);
        for(int i = 0; i < 4; i++) {
            AdvisorButton adv = new AdvisorButton(a.get(i).clone(), this);
            adv.setPosition(w * 0.2f * (i + 1) - adv.sWidth / 2, h * 0.6f);
            aAdvisor[i] = adv;
        }
    }

    @Override
    public void update() {
        for (AdvisorButton advisorButton : aAdvisor) {
            advisorButton.update();
        }
        nextButton.update();
        advisorSelectText.update();
        if(selected != null) AbstractLabyrinth.cPanel.infoPanel.setInfo(selected.advisor.data.NAME, selected.advisor.data.DESC);
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (AdvisorButton advisorButton : aAdvisor) {
            advisorButton.render(sb);
        }
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
        for (AdvisorButton advisorButton : aAdvisor) {
            advisorButton.dispose();
        }
    }
}
