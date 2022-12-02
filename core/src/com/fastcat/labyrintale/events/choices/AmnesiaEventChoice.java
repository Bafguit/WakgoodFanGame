package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.screens.allskill.AllSkillScreen;

public class AmnesiaEventChoice extends AbstractEvent.EventChoice implements GetSelectedSlot {

  private final AbstractEvent event;
  private final int toPage;
  private final AbstractSkill skill;

  public AmnesiaEventChoice(
      String t, AbstractSkill s, AbstractEvent.EventCondition c, AbstractEvent event, int page) {
    super(t, c);
    this.event = event;
    toPage = page;
    skill = s;
  }

  @Override
  protected void onSelect() {
    Labyrintale.addTempScreen(
        new AllSkillScreen(this));
  }

  @Override
  public void slotSelected(AbstractPlayer player, int index) {
    skill.owner = player;
    player.deck.set(index, skill);
    if (toPage >= 0) {
      event.setPage(toPage);
    }
  }
}
