package com.fastcat.labyrintale.items.boss;

import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.status.AttackStatus;

public class Tree extends AbstractItem {

  private static final String ID = "Tree";
  private static final ItemRarity RARITY = ItemRarity.BOSS;

  public Tree(AbstractPlayer owner) {
    super(ID, owner, RARITY);
  }

  @Override
  public void onGain() {
    owner.modifyMaxHealth(10);
    owner.stat.speed += 5;
    owner.stat.debuRes -= 0.1f;
    owner.stat.neutRes -= 0.1f;
    owner.stat.moveRes -= 0.1f;
  }

  @Override
  public void onRemove() {
    owner.modifyMaxHealth(-10);
    owner.stat.speed -= 5;
    owner.stat.debuRes += 0.1f;
    owner.stat.neutRes += 0.1f;
    owner.stat.moveRes += 0.1f;
  }

  @Override
  public void atBattleStart() {
    flash();
    bot(
        new ApplyStatusAction(
            new AttackStatus(1), owner, AbstractSkill.SkillTarget.ENEMY_ALL, true));
  }
}
