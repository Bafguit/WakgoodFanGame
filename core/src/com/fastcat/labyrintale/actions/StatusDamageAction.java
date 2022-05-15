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

import static com.fastcat.labyrintale.abstracts.AbstractSkill.SkillTarget.NONE;

public class StatusDamageAction extends AbstractAction {

    public AbstractStatus status;
    public boolean reduce = false;
    public boolean remove = false;
    public AttackAction.AttackType effect;
    public Sprite eImg;

    public StatusDamageAction(AbstractStatus s, AttackAction.AttackType effect) {
        super(s.owner, 0.5f);
        status = s;
        this.effect = effect;
        eImg = AttackAction.getEffectImg(effect);
    }

    public StatusDamageAction(AbstractStatus s, AttackAction.AttackType effect, boolean reduce, boolean remove) {
        this(s, effect);
        this.reduce = reduce;
        this.remove = remove;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            Array<AbstractEntity> temp = AbstractSkill.getTargets(status);
            if(effect == AttackAction.AttackType.NONE) {
                status.flash(actor);
            } else {
                for (AbstractEntity t : temp) {
                    //TODO 이미지 좌표로 자동입력되게 설정
                    EffectHandler.add(new HitEffect(t.animX, t.animY + Gdx.graphics.getHeight() * 0.1f, eImg));
                }
            }
            for(AbstractEntity e : temp) {
                e.takeDamage(new AbstractEntity.DamageInfo(actor, status.amount, AbstractEntity.DamageType.SPIKE));
            }
            if (reduce) actor.applyStatus(status, -1);
            if (remove) actor.removeStatus(status.id);
        }
    }
}
