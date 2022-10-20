package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class HealAction extends AbstractAction {

  public int heal;
  public boolean motion;

  public HealAction(AbstractEntity actor, Array<AbstractEntity> target, int heal) {
    super(actor, 0.5f);
    this.target = target;
    this.heal = heal;
    motion = true;
  }

  public HealAction(AbstractEntity actor, AbstractEntity target, int heal) {
    super(actor, 0.5f);
    this.target = new Array<>();
    this.target.add(target);
    this.heal = heal;
    motion = true;
  }

  public HealAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int heal) {
    super(actor, target, 0.5f);
    this.heal = heal;
    motion = true;
  }

  public HealAction(
      AbstractEntity actor, AbstractSkill.SkillTarget target, int heal, boolean motion) {
    super(actor, target, 0.5f);
    this.heal = heal;
    this.motion = motion;
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      if (target.size > 0) {
        SoundHandler.playSfx("HEAL");
        for (AbstractEntity e : target) {
          e.heal(actor != null ? actor.calculateSpell(heal) : heal);
        }
      } else isDone = true;
    }
  }
}
