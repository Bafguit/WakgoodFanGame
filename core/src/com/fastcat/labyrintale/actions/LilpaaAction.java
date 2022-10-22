package com.fastcat.labyrintale.actions;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.ALL;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.status.BurnStatus;

public class LilpaaAction extends AbstractAction {

  public boolean ps = false;
  public AbstractEffect effect;
  public AbstractEntity.DamageInfo info;
  public int burn;

  public LilpaaAction(AbstractSkill s) {
    super(s.owner, ALL, 2.0f);
    info = new AbstractEntity.DamageInfo(actor, s.attack);
    burn = s.value;
    // TODO 릴파파 이펙트 추가 요망
  }

  @Override
  protected void applySetting() {}

  @Override
  protected void updateAction() {
    if (duration == baseDuration) {
      SoundHandler.playSfx("LILPAA");
    } else if (duration < 1.1f && !ps) {
      if (actor != null) {
        AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
        actor.state.addAnimation(0, "idle", true, 0.0F);
        e.setTimeScale(1.0f);
      }
      ps = true;
      if (SettingHandler.setting.shake)
        Labyrintale.getScreenShake()
            .shake(ScreenShake.ShakeIntensity.MED, ScreenShake.ShakeDur.LONG, false);
      for (AbstractEntity t : target) {
        if (t != actor) EffectHandler.add(new HitEffect(t, FileHandler.getVfx().get("LIGHTNING")));
      }
      for (AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
        if (e.isAlive()) e.takeDamage(info);
      }
      for (AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
        if (e.isAlive()) e.applyStatus(new BurnStatus(burn), burn, true);
      }
    }
  }
}
