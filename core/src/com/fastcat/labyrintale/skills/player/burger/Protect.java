package com.fastcat.labyrintale.skills.player.burger;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.BlockAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;

public class Protect extends AbstractSkill {

  private static final String ID = "Protect";
  private static final SkillType TYPE = SkillType.DEFENCE;
  private static final SkillRarity RARITY = SkillRarity.STARTER;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;
  private static final int VALUE = 5;

  public Protect(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseSpell(VALUE, 1);
    setBaseCost(2);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity e) {
    Array<AbstractEntity> temp = new Array<>();
    temp.add(owner);
    temp.add(e);
    top(new BlockAction(owner, temp, spell));
  }

  @Override
  public boolean setTarget() {
    boolean can = false;
    for (PlayerBattleView pv : Labyrintale.battleScreen.players) {
      if (pv.entity.isAlive() && pv.entity != owner) {
        pv.isTarget = true;
        can = true;
      }
    }
    if (can) return true;
    else {
      top(new BlockAction(owner, SkillTarget.SELF, spell));
      return false;
    }
  }

  @Override
  protected void upgradeCard() {}
}
