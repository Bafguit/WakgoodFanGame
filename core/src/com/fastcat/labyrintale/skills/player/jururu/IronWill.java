package com.fastcat.labyrintale.skills.player.jururu;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.ApplyStatusAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;
import com.fastcat.labyrintale.status.EnduranceStatus;
import com.fastcat.labyrintale.status.NeutResPlusStatus;

public class IronWill extends AbstractSkill {

  private static final String ID = "IronWill";
  private static final SkillType TYPE = SkillType.SCHEME;
  private static final SkillRarity RARITY = SkillRarity.NORMAL;
  private static final SkillTarget TARGET = SkillTarget.PLAYER_ALL;
  private static final int VALUE = 2;

  public IronWill(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseValue(VALUE, 1);
  }

  @Override
  public void use() {
    bot(new ApplyStatusAction(new EnduranceStatus(value), owner, SkillTarget.SELF, true));
    bot(new ApplyStatusAction(new NeutResPlusStatus(value), owner, SkillTarget.SELF, true));
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
      top(new ApplyStatusAction(new EnduranceStatus(value), owner, SkillTarget.SELF, false));
      return false;
    }
  }

  @Override
  protected void upgradeCard() {}
}
