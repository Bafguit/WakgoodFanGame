package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
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
    public AttackAction.AttackType effect;
    public Sprite eImg;
    public AbstractEntity e;

    public StatusDamageAction(AbstractStatus s, AttackAction.AttackType effect) {
        super(null, 0.5f);
        status = s;
        this.effect = effect;
        eImg = AttackAction.getEffectImg(effect);
        e = s.owner;
    }

    public StatusDamageAction(AbstractStatus s, AttackAction.AttackType effect, boolean reduce, boolean remove) {
        this(s, effect);
        this.reduce = reduce;
        this.remove = remove;
    }

    public StatusDamageAction(AbstractStatus s, AttackAction.AttackType effect, boolean reduce, boolean remove, boolean isFast) {
        this(s, effect);
        if(isFast) duration = baseDuration = 0.2f;
        this.reduce = reduce;
        this.remove = remove;
    }

    public StatusDamageAction(AbstractStatus s, AbstractEntity e, AttackAction.AttackType effect, boolean reduce, boolean remove, boolean isFast) {
        this(s, effect);
        if(isFast) duration = baseDuration = 0.2f;
        this.reduce = reduce;
        this.remove = remove;
        this.e = e;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(status);
            if(e != null) {
                if (effect == AttackAction.AttackType.NONE) {
                    status.flash(e);
                }
                e.takeDamage(new AbstractEntity.DamageInfo(null, status.amount, AbstractEntity.DamageType.SPIKE));
            } else {
                if (effect == AttackAction.AttackType.NONE) {
                    status.flash(actor);
                } else {
                    for (AbstractEntity t : temp) {
                        //TODO ????????? ????????? ?????????????????? ??????
                        EffectHandler.add(new HitEffect(t.animX, t.animY + Gdx.graphics.getHeight() * 0.1f, eImg));
                    }
                }
                for (AbstractEntity e : temp) {
                    e.takeDamage(new AbstractEntity.DamageInfo(null, status.amount, AbstractEntity.DamageType.SPIKE));
                }
            }
            if (reduce) actor.applyStatus(status, -1);
            if (remove) actor.removeStatus(status.id);
        }
    }
}
