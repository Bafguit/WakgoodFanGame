package com.fastcat.labyrintale.skills.enemy;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.AttackAction;
import com.fastcat.labyrintale.handlers.ActionHandler;

public class SlashE extends AbstractSkill {

  private static final String ID = "SlashE";
  private static final SkillType TYPE = SkillType.ATTACK;
  private static final SkillRarity RARITY = SkillRarity.ENEMY;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 4;

  public SlashE(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseAttack(VALUE, 1);
  }

  @Override
  public void use() {
    ActionHandler.bot(new AttackAction(owner, TARGET, attack, AttackAction.AttackType.SLASH_V));
  }

  @Override
  protected void upgradeCard() {}
}
