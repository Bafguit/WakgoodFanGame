package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.UpDamageEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;

public class HealAction extends AbstractAction {

    public int heal;
    public boolean motion;

    public HealAction(AbstractEntity actor, Array<AbstractEntity> target, int heal) {
        super(actor, 0.5f);
        this.target = target;
        this.heal = heal;
        motion = true;
    }

    public HealAction(AbstractEntity actor, AbstractEntity target, int heal) {
        super(actor, 0.5f);
        this.target = new Array<>();
        this.target.add(target);
        this.heal = heal;
        motion = true;
    }

    public HealAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int heal) {
        super(actor, target, 0.5f);
        this.heal = heal;
        motion = true;
    }

    public HealAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int heal, boolean effect) {
        super(actor, target, 0.5f);
        this.heal = heal;
        motion = effect;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            if(target.size > 0) {
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    EffectHandler.add(new UpDamageEffect(te.ui.x + te.ui.sWidth / 2, te.ui.y + te.ui.sHeight * 0.35f, heal, CHARTREUSE, false));
                    te.heal(heal);
                }
                if (actor != null && motion) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "skill", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
            } else isDone = true;
        }
    }
}
