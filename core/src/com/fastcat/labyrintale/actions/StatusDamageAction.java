package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

public class StatusDamageAction extends AbstractAction {

  public AbstractStatus status;
  public boolean reduce = false;
  public boolean remove = false;
  public boolean hasEffect;
  public AttackAction.AttackType effect;
  public Sprite eImg;
  public AbstractEntity e;

  public StatusDamageAction(AbstractStatus s, AttackAction.AttackType effect) {
    super(s.owner, 0.5f);
    status = s;
    this.effect = effect;
    hasEffect = effect != AttackAction.AttackType.NONE;
    eImg = AttackAction.getEffectImg(effect);
    if (s.target == AbstractSkill.SkillTarget.SELF) e = s.owner;
  }

  public StatusDamageAction(
      AbstractStatus s, AttackAction.AttackType effect, boolean reduce, boolean remove) {
    this(s, effect);
    this.reduce = reduce;
    this.remove = remove;
  }

  public StatusDamageAction(
      AbstractStatus s,
      AttackAction.AttackType effect,
      boolean reduce,
      boolean remove,
      boolean isFast) {
    this(s, effect);
    if (isFast) duration = baseDuration = 0.25f;
    this.reduce = reduce;
    this.remove = remove;
  }

  public StatusDamageAction(
      AbstractStatus s,
      AbstractEntity e,
      AttackAction.AttackType effect,
      boolean reduce,
      boolean remove,
      boolean isFast) {
    this(s, effect);
    if (isFast) duration = baseDuration = 0.25f;
    this.reduce = reduce;
    this.remove = remove;
    this.e = e;
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      if (e != null) {
        AttackAction.playAttackSfx(effect);
        if (!hasEffect) {
          status.flash(e);
        } else {
          EffectHandler.add(new HitEffect(e, eImg));
        }
        e.takeDamage(
            new AbstractEntity.DamageInfo(null, status.amount, AbstractEntity.DamageType.SPIKE));
      } else {
        Array<AbstractEntity> temp = AbstractSkill.getTargets(status);
        if (temp.size > 0) {
          AttackAction.playAttackSfx(effect);
          if (!hasEffect) {
            status.flash(actor);
          } else {
            for (AbstractEntity t : temp) {
              EffectHandler.add(new HitEffect(t, eImg));
            }
          }
          for (AbstractEntity e : temp) {
            e.takeDamage(
                new AbstractEntity.DamageInfo(
                    null, status.amount, AbstractEntity.DamageType.SPIKE));
          }
        }
      }
      if (reduce) actor.applyStatus(status, actor, -1, false, true);
      if (remove) actor.removeStatus(status.id);
    }
  }
}
