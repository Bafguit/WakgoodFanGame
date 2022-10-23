package com.fastcat.labyrintale.items.starter;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.CourageStatus;
import com.fastcat.labyrintale.status.ResistMinusStatus;

public class OldPistol extends AbstractItem {

  private static final String ID = "OldPistol";
  private static final ItemRarity RARITY = ItemRarity.STARTER;

  public OldPistol(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.stat.moveRes += 0.2f;
  }

  @Override
  public void onRemove() {
    owner.stat.multiply -= 0.2f;
  }

  public void onMove(AbstractEntity source) {
    bot(new ApplyStatusAction(new CourageStatus(2), owner, AbstractSkill.SkillTarget.ENEMY_ALL, true));
  }
}
