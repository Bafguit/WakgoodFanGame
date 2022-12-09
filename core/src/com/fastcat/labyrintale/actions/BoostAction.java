package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public class BoostAction extends AbstractAction {

    public BoostAction(AbstractEntity actor, AbstractEntity target) {
        super(actor, 0.5f);
        this.target = new Array<>();
        this.target.add(target);
    }

    public BoostAction(AbstractEntity actor, Array<AbstractEntity> target) {
        super(actor, 0.5f);
        this.target = target;
    }

    public BoostAction(AbstractEntity actor, AbstractSkill.SkillTarget target) {
        super(actor, target, 0.5f);
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                SoundHandler.playSfx("BLOCK");
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t, FileHandler.getVfx().get("SHIELD")));
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    te.gainBlock(te.block);
                }
            } else isDone = true;
        }
    }
}
