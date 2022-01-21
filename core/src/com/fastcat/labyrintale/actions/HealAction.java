package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.UpTextEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;

import static com.badlogic.gdx.graphics.Color.CHARTREUSE;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class HealAction extends AbstractAction {

    public int heal;

    public HealAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int heal) {
        super(actor, target, 0.5f);
        this.heal = heal;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            if(target.size > 0) {
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    EffectHandler.add(new UpTextEffect(te.ui.x + te.ui.sWidth / 2, te.ui.y + te.ui.sHeight * 0.35f, heal, CHARTREUSE, false));
                    te.heal(heal);
                }
                if (actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "DoubleHit1", false);
                    actor.state.addAnimation(0, "Standby", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
            } else isDone = true;
        }
    }
}
