package com.fastcat.labyrintale.actions;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAction;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;

public class SelectTargetAction extends AbstractAction implements GetSelectedTarget {

    public GetSelectedTarget gets;
    public AbstractSkill.SkillTarget tar;

    public SelectTargetAction(GetSelectedTarget gets, AbstractSkill.SkillTarget target) {
        super(null, 1);
        this.gets = gets;
        this.tar = target;
    }

    @Override
    protected void updateAction() {
        if(duration == baseDuration) {
            Labyrintale.battleScreen.selectTarget(this, tar);
        } else {
            if(!isDone) duration = 10000;
        }
    }

    @Override
    public void onTargetSelected(AbstractEntity e) {
        isDone = true;
        Labyrintale.battleScreen.isSelecting = false;
        gets.onTargetSelected(e);
    }
}
