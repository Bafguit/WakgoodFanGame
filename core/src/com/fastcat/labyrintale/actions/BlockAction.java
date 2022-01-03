package com.fastcat.labyrintale.actions;

import com.badlogic.gdx.utils.Array;
import com.esotericsoftware.spine.AnimationState;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEffect;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;

public class BlockAction extends AbstractAction {

    public int block;

    public BlockAction(AbstractEntity actor, AbstractSkill.CardTarget target, int block) {
        super(actor, target, 0.5f);
        this.block = block;
    }

    @Override
    protected void updateAction() {
        if (duration == baseDuration){
            Array<AbstractEntity> t = getTargets(target);
            if(t.size > 0) {
                for (int i = 0; i < t.size; i++) {
                    AbstractEntity te = t.get(i);
                    te.gainBlock(block);
                }
            } else isDone = true;
        }
    }
}
