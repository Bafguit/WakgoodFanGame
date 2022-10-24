package com.fastcat.labyrintale.items.silver;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.EnduranceStatus;

public class FabricMail extends AbstractItem {

  private static final String ID = "FabricMail";
  private static final ItemRarity RARITY = ItemRarity.SILVER;

  public FabricMail(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(5);
    owner.stat.moveRes += 5;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-5);
    owner.stat.moveRes -= 5;
  }

  public int onDamaged(AbstractEntity attacker, int damage, AbstractEntity.DamageType type) {
    flash();
    bot(new ApplyStatusAction(new EnduranceStatus(1), owner, AbstractSkill.SkillTarget.SELF, false));
    return damage;
  }
}
