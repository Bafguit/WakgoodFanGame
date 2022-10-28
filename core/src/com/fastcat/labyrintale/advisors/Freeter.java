package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.FreeterSkill;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Freeter extends AbstractItem {

  private static final String ID = "freeter";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Freeter() {
    super(ID, null, RARITY);
  }

  public void atBattleStart() {
    bot(new ApplyStatusAction(new CourageStatus(2), null, AbstractSkill.SkillTarget.PLAYER_FIRST_TWO, false));
  }
}
