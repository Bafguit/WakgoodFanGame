package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.advisorselect.AdvisorSelectScreen;

public class StartAdvisorChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

  public AbstractEvent event;
  public int page;

  public StartAdvisorChoice(String t, AbstractEvent event, int page) {
    super(t);
    this.event = event;
    this.page = page;
  }

  @Override
  protected void onSelect() {
    AdvisorSelectScreen s = new AdvisorSelectScreen(true);
    s.endTemp.add(this);
    Labyrintale.addTempScreen(s);
  }

  @Override
  public void atEndOfTempScreen() {
    event.setPage(page);
  }
}
