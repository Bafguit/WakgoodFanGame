package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

public class ModifyMaxHealthAction extends AbstractAction {

  public int add;

  public ModifyMaxHealthAction(AbstractEntity actor, Array<AbstractEntity> target, int add) {
    super(actor, 0.5f);
    this.target = target;
    this.add = add;
  }

  public ModifyMaxHealthAction(AbstractEntity actor, AbstractEntity target, int add) {
    super(actor, 0.5f);
    this.target = new Array<>();
    this.target.add(target);
    this.add = add;
  }

  public ModifyMaxHealthAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int add) {
    super(actor, target, 0.5f);
    this.add = add;
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      if (target.size > 0) {
        for (AbstractEntity e : target) {
          e.modifyMaxHealth(add);
        }
      } else isDone = true;
    }
  }
}
