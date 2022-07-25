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
        if(isFast) duration = baseDuration = 0.25f;
        this.reduce = reduce;
        this.remove = remove;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            Array<AbstractEntity> temp = AbstractSkill.getTargets(status);
            if (effect == AttackAction.AttackType.NONE) {
                status.flash(e);
            } else {
                for (AbstractEntity t : temp) {
                    //TODO 이미지 좌표로 자동입력되게 설정
                    EffectHandler.add(new HitEffect(t.animX, t.animY + Gdx.graphics.getHeight() * 0.1f, eImg));
                }
            }
            for (AbstractEntity e : temp) {
                e.takeDamage(new AbstractEntity.DamageInfo(null, status.amount, AbstractEntity.DamageType.SPIKE));
            }
            if (reduce) e.applyStatus(status, -1);
            if (remove) e.removeStatus(status.id);
        }
    }
}
