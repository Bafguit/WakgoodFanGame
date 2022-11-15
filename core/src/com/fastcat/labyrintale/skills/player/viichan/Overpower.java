package com.fastcat.labyrintale.skills.player.viichan;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AmbushAction;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.actions.UpgradeAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.SpeedPlusStatus;

public class Overpower extends AbstractSkill {

  private static final String ID = "Overpower";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY_FIRST;

  public Overpower(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(4, 2);
  }

  @Override
  public void use() {
    bot(new AttackAction(owner, target, attack, AttackAction.AttackType.SLASH_H));
    bot(new UpgradeAction(this));
  }

  @Override
  protected void upgradeCard() {}
}
