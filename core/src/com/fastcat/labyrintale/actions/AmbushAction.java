package com.fastcat.labyrintale.actions;

import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class AmbushAction extends AbstractAction {

    public AbstractSkill skill;
    public AbstractEntity.DamageInfo info;

    public AmbushAction(AbstractSkill s) {
        super(s.owner, s.target, 0.5f);
        info = new AbstractEntity.DamageInfo(actor, s.attack);
        skill = s;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            SoundHandler.playSfx("ATTACK_TEST");
            if(target.size > 0) {
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    if(te.isAlive()) {
                        te.takeDamage(info);
                        skill.upgrade();
                    }
                }
                if(actor != null) {
                    AnimationState.TrackEntry e = actor.state.setAnimation(0, "hit", false);
                    actor.state.addAnimation(0, "idle", true, 0.0F);
                    e.setTimeScale(1.0f);
                }
            } else isDone = true;
        }
    }
}
