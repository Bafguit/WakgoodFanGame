package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

public class LowAttackAction extends AbstractAction {

    public int damage;

    public LowAttackAction(AbstractEntity actor, int damage, boolean isFast) {
        super(
                actor,
                actor != null && actor.isPlayer
                        ? AbstractSkill.SkillTarget.ENEMY_LOW_HP
                        : AbstractSkill.SkillTarget.PLAYER_LOW_HP,
                isFast ? 0.25f : 0.5f);
        this.damage = damage;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                AttackAction.playAttackSfx(AttackAction.AttackType.HEAVY);
                if (actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t, FileHandler.getVfx().get("HIT_HEAVY")));
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if (te.isAlive()) te.takeDamage(new AbstractEntity.DamageInfo(actor, damage));
                }
            }
        }
    }
}
