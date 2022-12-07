package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;

public class MitsuneHaku extends AbstractItem {

  private static final String ID = "haku";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public MitsuneHaku() {
    super(ID, null, RARITY);
  }

  @Override
  public void startOfRound() {
    bot(new ApplyStatusAction(new CourageStatus(2), null, AbstractSkill.SkillTarget.PLAYER_ALL, false));
  }
}