package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.effects.HitEffect;
import com.fastcat.labyrintale.handlers.EffectHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

public class BlockAction extends AbstractAction {

    public int block;

    public BlockAction(AbstractEntity actor, AbstractEntity target, int block) {
        super(actor, 0.5f);
        this.target = new Array<>();
        this.target.add(target);
        this.block = block;
    }

    public BlockAction(AbstractEntity actor, Array<AbstractEntity> target, int block) {
        super(actor, 0.5f);
        this.target = target;
        this.block = block;
    }

    public BlockAction(AbstractEntity actor, AbstractSkill.SkillTarget target, int block) {
        super(actor, target, 0.5f);
        this.block = block;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration) {
            if (target.size > 0) {
                for (AbstractEntity t : target) {
                    EffectHandler.add(new HitEffect(t.animX, t.animY + Gdx.graphics.getHeight() * 0.1f, FileHandler.getVfx().get("SHIELD")));
                }
                for (int i = 0; i < target.size; i++) {
                    AbstractEntity te = target.get(i);
                    te.gainBlock(actor != null ? actor.calculateSpell(block) : block);
                }
            } else isDone = true;
        }
    }
}
