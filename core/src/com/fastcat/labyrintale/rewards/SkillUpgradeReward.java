package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.interfaces.GetSelectedSkill;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen;

public abstract class SkillUpgradeReward extends AbstractReward implements GetSelectedSkill {

    public Array<Array<AbstractSkill>> group = new Array<>();
    public GetSelectedSkill gets;

    public SkillUpgradeReward() {
        super(RewardType.EXP);
    }

    public enum SkillRewardType {
        NORMAL, UPGRADE
    }

    @Override
    public final void skillSelected(SkillSelectScreen.SkillSelectGroup skill) {
        int index = 0;
        AbstractSkill ts = skill.toSkill.skill;
        for(int i = 0; i < skill.player.deck.size; i++) {
            if(skill.player.deck.get(i).id.equals(ts.id)) {
                index = i;
            }
        }
        skill.player.gainSkill(index, skill.selected);
        if(gets != null) gets.skillSelected(skill);
        Labyrintale.removeTempScreen(SkillSelectScreen.SkillSelectGroup.screen);
    }
}
