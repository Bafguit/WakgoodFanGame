package com.fastcat.labyrintale.rewards;

import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomSkill;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class SkillReward extends AbstractReward {

  public AbstractSkill skill;

  public SkillReward(AbstractSkill s) {
    super(RewardType.SKILL);
    skill = s;
    setInfo(skill.name, skill.desc);
    img = skill.img;
  }

  public SkillReward() {
    this(getRandomSkill(AbstractLabyrinth.players[AbstractLabyrinth.publicRandom.random(0, 3)]));
  }

  @Override
  public void takeReward() {
    Labyrintale.addTempScreen(new ShopTakeScreen(skill));
  }
}
