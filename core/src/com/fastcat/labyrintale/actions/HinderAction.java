package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.status.SpeedMinusStatus;

public class HinderAction extends AbstractAction {

    public AbstractEntity.DamageInfo info;
    public int cool;

    public HinderAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int damage, int cool) {
        super(actor, target, 0.5f);
        info = new AbstractEntity.DamageInfo(actor, damage);
        this.cool = cool;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                AttackAction.playAttackSfx(AttackAction.AttackType.LIGHTNING);
                if (actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
                if (SettingHandler.setting.shake)
                    Labyrintale.getScreenShake()
                            .shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t, FileHandler.getVfx().get("LIGHTNING")));
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if (te.isAlive()) te.takeDamage(info);
                }
                for (AbstractEntity e : target) {
                    e.applyStatus(new SpeedMinusStatus(cool), actor, cool);
                }
            } else isDone = true;
        }
    }
}
