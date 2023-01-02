package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

public class HighAttackAction extends AbstractAction {

    public int damage;

    public HighAttackAction(AbstractEntity actor, int damage, boolean isFast) {
        super(
                actor,
                actor != null && actor.isPlayer
                        ? AbstractSkill.SkillTarget.ENEMY_HIGH_HP
                        : AbstractSkill.SkillTarget.PLAYER_HIGH_HP,
                isFast ? 0.25f : 0.5f);
        this.damage = damage;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                AttackAction.playAttackSfx(AttackAction.AttackType.HEAVY);
                if (actor != null) {
                    actor.animation.setAndIdle("attack");
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
