package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.rewards.SkillUpgradeReward;
import com.fastcat.labyrintale.rewards.SkillRewardUpgrade;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen;

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
        SkillSelectScreen s = new SkillSelectScreen(SkillUpgradeReward.SkillRewardType.UPGRADE, new SkillRewardUpgrade());
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
