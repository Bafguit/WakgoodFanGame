package com.fastcat.labyrintale.advisors;

import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.skills.player.advisor.JkSkill;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class Hyeji extends AbstractItem {

  private static final String ID = "jk";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public Hyeji() {
    super(ID, null, RARITY);
  }

  @Override
  public void onGain() {
    AbstractLabyrinth.maxSkillUp++;
  }

  @Override
  public void onRemove() {
    AbstractLabyrinth.maxSkillUp--;
  }
}
