package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.*;

public class AttackAction extends AbstractAction {

    public AbstractEntity monoTarget;
    public AttackType effect;
    public AbstractEntity.DamageInfo info;

    private Sprite img;

    public AttackAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int damage, AttackType effect) {
        super(actor, target, 0.5f);
        info = new AbstractEntity.DamageInfo(actor, damage);
        this.effect = effect == null ? AttackType.NONE : effect;
        img = getEffectImg(effect);
    }

    public AttackAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int damage, AttackType effect, boolean fast) {
        super(actor, target, fast ? 0.1f : 0.5f);
        info = new AbstractEntity.DamageInfo(actor, damage);
        this.effect = effect == null ? AttackType.NONE : effect;
        img = getEffectImg(effect);
    }

    public AttackAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int damage, AbstractEntity.DamageType type, AttackType effect) {
        super(actor, target, 0.5f);
        info = new AbstractEntity.DamageInfo(actor, damage, type);
        this.effect = effect == null ? AttackType.NONE : effect;
        img = getEffectImg(effect);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            if(target.size > 0) {
                SoundHandler.playSfx("ATTACK_TEST");
                for(AbstractEntity t : target) {
                    //TODO 이미지 좌표로 자동입력되게 설정
                    EffectHandler.add(new HitEffect(t.animX, t.animY + Gdx.graphics.getHeight() * 0.1f, img, 1.5f));
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if(te.isAlive()) te.takeDamage(info);
                }
                if(actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "attack", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
            } else isDone = true;
        }
    }

    private Sprite getEffectImg(AttackType t) {
        return FileHandler.vfx.get("HIT_LIGHT");
    }

    public enum AttackType {
        NONE, LIGHT, HEAVY, LIGHTNING
    }
}
