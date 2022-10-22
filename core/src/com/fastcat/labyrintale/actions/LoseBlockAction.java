package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class LoseBlockAction extends AbstractAction {

  public LoseBlockAction(AbstractEntity actor, AbstractSkill.SkillTarget target) {
    super(actor, target, 0.5f);
  }

  @Override
  protected void updateAction() {
    if(duration == baseDuration) {
      if(target.size > 0) {
        for(AbstractEntity e : target) {
          if(e.isAlive() && e.block > 0) e.loseBlock(e.block);
        }
      }
    }
  }
}
