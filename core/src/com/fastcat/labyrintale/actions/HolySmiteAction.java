package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.*;

public class HolySmiteAction extends AbstractAction {

    public AbstractEntity.DamageInfo info;

    public HolySmiteAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int damage) {
        super(actor, target, 0.5f);
        info = new AbstractEntity.DamageInfo(actor, damage);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                AttackAction.playAttackSfx(AttackAction.AttackType.LIGHTNING);
                if (actor != null) {
                    actor.animation.setAndIdle("attack");
                }
                if (SettingHandler.setting.shake)
                    Labyrintale.getScreenShake()
                            .shake(ScreenShake.ShakeIntensity.LOW, ScreenShake.ShakeDur.SHORT, false);
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t, FileHandler.getVfx().get("LIGHTNING")));
                }
                int dmg = 0;
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if (te.isAlive()) dmg += te.takeDamage(info);
                }
                if (dmg > 0) ActionHandler.top(new BlockAction(null, AbstractSkill.SkillTarget.PLAYER_ALL, dmg));
            } else isDone = true;
        }
    }
}
