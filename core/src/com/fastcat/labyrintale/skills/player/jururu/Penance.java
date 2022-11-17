package com.fastcat.labyrintale.skills.player.jururu;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.screens.battle.EnemyBattleView;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;
import com.fastcat.labyrintale.status.BurnStatus;
import com.fastcat.labyrintale.status.ResistMinusStatus;

public class Penance extends AbstractSkill {

  private static final String ID = "Penance";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.ENEMY;
  private static final int VALUE = 1;

  public Penance(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(4, 1);
    setBaseValue(VALUE, 1);
    setBaseCost(1);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity target) {
    top(new PenanceAction(this, target));
    top(new ApplyStatusAction(new ResistMinusStatus(value), owner, target, true));
  }
  @Override
  protected void upgradeCard() {}
}
