package com.fastcat.labyrintale.skills.player.basic;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.actions.MoveAction;
import com.fastcat.labyrintale.screens.battle.PlayerBattleView;

public class MoveP extends AbstractSkill {

  private static final String ID = "MoveP";
  private static final SkillType TYPE = SkillType.MOVE;
  private static final SkillRarity RARITY = SkillRarity.BASIC;
  private static final SkillTarget TARGET = SkillTarget.PLAYER;

  public MoveP(AbstractEntity e) {
    super(e, ID, TYPE, RARITY, TARGET);
    setBaseCost(0);
  }

  @Override
  public void use() {}

  @Override
  public void onTarget(AbstractEntity target) {
    top(new MoveAction(owner, owner, target.index));
  }

  @Override
  public boolean setTarget() {
    boolean can = false;
    if (owner != null) {
      if (owner.index > 0) {
        PlayerBattleView pv = battleScreen.players[owner.index - 1];
        if (pv.entity.isAlive()) {
          pv.isTarget = true;
          can = true;
        }
      }
      if (owner.index < 3) {
        PlayerBattleView pv = battleScreen.players[owner.index + 1];
        if (pv.entity.isAlive()) {
          pv.isTarget = true;
          can = true;
        }
      }
    }

    return can;
  }

  @Override
  protected boolean available() {
    boolean can = false;
    if (owner != null) {
      if (owner.index > 0) {
        if (AbstractLabyrinth.players[owner.index - 1].isAlive()) {
          can = true;
        }
      }
      if (owner.index < 3) {
        if (AbstractLabyrinth.players[owner.index + 1].isAlive()) {
          can = true;
        }
      }
    }
    return can;
  }

  @Override
  protected void upgradeCard() {}
}
