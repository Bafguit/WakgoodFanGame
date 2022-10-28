package com.fastcat.labyrintale.skills.player.advisor;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class BusinessSkill extends AbstractItem {

  private static final String ID = "business";
  private static final ItemRarity RARITY = ItemRarity.ADVISOR;

  public BusinessSkill() {
    super(ID, null, RARITY);
  }

  public void atBattleStart() {
    bot(new ApplyStatusAction(new EnduranceStatus(2), owner, AbstractSkill.SkillTarget.PLAYER_ALL, false));
  }
}
