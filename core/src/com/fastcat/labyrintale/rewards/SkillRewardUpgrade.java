package com.fastcat.labyrintale.rewards;

import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomUpgradedSkillFromDeck;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.interfaces.GetSelectedSkill;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen;

public class SkillRewardUpgrade extends SkillUpgradeReward {

  public SkillRewardUpgrade(GetSelectedSkill gets) {
    this.gets = gets;
    Array<AbstractPlayer> ap = new Array<>(AbstractLabyrinth.players);
    for (int i = 0; i < ap.size; i++) {
      AbstractPlayer p = ap.get(i);
      if (p.isAlive()) {
        Array<AbstractSkill> t = new Array<>();
        t.add(getRandomUpgradedSkillFromDeck(p, true));
        group.add(t);
      }
    }
    setInfo("스킬 강화", "스킬을 강화합니다.");
  }

  @Override
  public void takeReward() {
    Labyrintale.addTempScreen(new SkillSelectScreen(SkillRewardType.UPGRADE, group, this, this));
  }
}
