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
  public AdvisorButton[] aAdvisor;

  public AdvisorSelectScreen() {
    this(false);
  }

  public AdvisorSelectScreen(boolean isStart) {
    advisorSelectText = new AdvisorSelectText();
    nextButton = new NextButton(this);
    addAdvisor(isStart);
  }

  private void addAdvisor(boolean start) {
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    Array<AbstractAdvisor> a;
    if (start) a = GroupHandler.AdvisorGroup.getStartAdvisor();
    else a = GroupHandler.AdvisorGroup.getAdvisors(4);
    int s = a.size;
    aAdvisor = new AdvisorButton[s];
    float ws = w / (s + 1);
    for (int i = 0; i < a.size; i++) {
      AdvisorButton adv = new AdvisorButton(a.get(i).clone(), this);
      adv.setPosition(ws * (i + 1) - adv.sWidth / 2, h * 0.6f);
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
    if (selected != null)
      AbstractLabyrinth.cPanel.infoPanel.setInfo(
          selected.advisor.data.NAME, selected.advisor.data.DESC);
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
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    for (AdvisorButton advisorButton : aAdvisor) {
      advisorButton.dispose();
    }
  }
}
