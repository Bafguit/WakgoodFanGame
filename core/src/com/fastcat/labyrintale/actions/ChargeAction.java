package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class ChargeAction extends AbstractAction {

  private final Sprite img;
  private AbstractSkill skill;
  public AttackType effect;
  public AbstractEntity.DamageInfo info;

  public ChargeAction(AbstractSkill s, AbstractSkill.SkillTarget target) {
    super(s.owner, target, 0.25f);
    img = FileHandler.getVfx().get("SMASH");
    effect = AttackType.SMASH;
    skill = s;
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
        int block = 0;
        info = new AbstractEntity.DamageInfo(actor, skill.attack);
        playAttackSfx(effect);
        if (actor != null) {
          AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
          actor.state.addAnimation(0, "idle", true, 0.0F);
          e.setTimeScale(1.0f);
        }
        if (effect != AttackType.NONE) {
          for (AbstractEntity t : target) {
            EffectHandler.add(new HitEffect(t, img));
          }
        }
        for (int i = 0; i < target.size; i++) {
          AbstractEntity te = target.get(i);
          if (te.isAlive()) block += te.takeDamage(info);
        }
        ActionHandler.top(new BlockAction(actor, actor, block));
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
