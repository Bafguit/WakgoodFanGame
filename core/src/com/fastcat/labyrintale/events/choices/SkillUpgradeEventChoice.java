package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.rewards.SkillRewardUpgrade;
import com.fastcat.labyrintale.screens.reward.skill.SkillRewardScreen;

public class SkillUpgradeEventChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

    private final AbstractEvent event;
    private final int toPage;

    public SkillUpgradeEventChoice(String t, AbstractEvent.EventCondition c, AbstractEvent event) {
        this(t, c, event, -1);
    }

    public SkillUpgradeEventChoice(String t, AbstractEvent.EventCondition c, AbstractEvent event, int page) {
        super(t, c);
        this.event = event;
        toPage = page;
    }

    @Override
    protected void onSelect() {
        SkillRewardScreen s = new SkillRewardScreen(SkillReward.SkillRewardType.UPGRADE, new SkillRewardUpgrade());
        s.endTemp.add(this);
        Labyrintale.addTempScreen(s);
    }

    @Override
    public void atEndOfTempScreen() {
        if(toPage >= 0) {
            event.setPage(toPage);
        }
    }
}
