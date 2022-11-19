package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class AttackAction extends AbstractAction {

  private final Sprite img;
  public AttackType effect;
  public AbstractEntity.DamageInfo info;

  public AttackAction(AbstractEntity.DamageInfo info, AbstractEntity target, AttackType effect) {
    super(info.actor, target, 0.5f);
    this.info = info;
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(AbstractEntity.DamageInfo info, AbstractEntity target, AttackType effect, boolean fast) {
    super(info.actor, target, fast ? 0.25f : 0.5f);
    this.info = info;
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(AbstractEntity actor, AbstractEntity target, int damage, AttackType effect) {
    super(actor, target, 0.5f);
    info = new AbstractEntity.DamageInfo(actor, damage);
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(
      AbstractEntity actor, AbstractEntity target, int damage, AttackType effect, boolean fast) {
    super(actor, target, fast ? 0.25f : 0.5f);
    info = new AbstractEntity.DamageInfo(actor, damage);
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(
      AbstractEntity actor,
      AbstractEntity target,
      int damage,
      AbstractEntity.DamageType type,
      AttackType effect,
      boolean fast) {
    super(actor, target, fast ? 0.25f : 0.5f);
    info = new AbstractEntity.DamageInfo(actor, damage, type);
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(
      AbstractEntity actor, AbstractSkill.SkillTarget target, int damage, AttackType effect) {
    super(actor, target, 0.5f);
    info = new AbstractEntity.DamageInfo(actor, damage);
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(
      AbstractEntity actor,
      AbstractSkill.SkillTarget target,
      int damage,
      AttackType effect,
      boolean fast) {
    super(actor, target, fast ? 0.25f : 0.5f);
    info = new AbstractEntity.DamageInfo(actor, damage);
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public AttackAction(
      AbstractEntity actor,
      AbstractSkill.SkillTarget target,
      int damage,
      AbstractEntity.DamageType type,
      AttackType effect,
      boolean fast) {
    super(actor, target, fast ? 0.25f : 0.5f);
    info = new AbstractEntity.DamageInfo(actor, damage, type);
    this.effect = effect;
    img = getEffectImg(effect);
  }

  public static Sprite getEffectImg(AttackType t) {
    switch (t) {
      case SMASH:
        return FileHandler.getVfx().get("SMASH");
      case BURN:
        return FileHandler.getVfx().get("BURN");
      case SLASH_D:
        return FileHandler.getVfx().get("SLASH_D");
      case SLASH_V:
        return FileHandler.getVfx().get("SLASH_V");
      case LIGHT:
        return FileHandler.getVfx().get("HIT_LIGHT");
      case HEAVY:
        return FileHandler.getVfx().get("HIT_HEAVY");
      case LIGHTNING:
        return FileHandler.getVfx().get("LIGHTNING");
      case INFECTION:
        return FileHandler.getVfx().get("INFECTION");
      default:
        return FileHandler.getVfx().get("SLASH_H");
    }
  }

  public static void playAttackSfx(AttackType type) {
    switch (type) {
      case SMASH:
        SoundHandler.playSfx("SMASH");
        break;
      case BURN:
        SoundHandler.playSfx("BURN");
        break;
      case LIGHT:
        SoundHandler.playSfx("BLUNT_LIGHT");
        break;
      case HEAVY:
        SoundHandler.playSfx("BLUNT_HEAVY");
        break;
      case LIGHTNING:
        SoundHandler.playSfx("LIGHTNING");
        break;
      case INFECTION:
        SoundHandler.playSfx("POISON");
        break;
      default:
        SoundHandler.playSfx("SLASH");
        break;
    }
  }

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      if (target.size > 0) {
        playAttackSfx(effect);
        if (actor != null) {
          AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
          actor.state.addAnimation(0, "idle", true, 0.0F);
          e.setTimeScale(1.0f);
        }
        if (effect != AttackType.NONE) {
          for (AbstractEntity t : target) {
            if (t.isAlive()) EffectHandler.add(new HitEffect(t, img));
          }
        }
        for (int i = 0; i < target.size; i++) {
          AbstractEntity te = target.get(i);
          if (te.isAlive()) te.takeDamage(info);
        }
      } else isDone = true;
    }
  }

  public enum AttackType {
    NONE,
    LIGHT,
    HEAVY,
    LIGHTNING,
    INFECTION,
    SLASH_H,
    SLASH_V,
    SLASH_D,
    SMASH,
    BURN
  }
}
