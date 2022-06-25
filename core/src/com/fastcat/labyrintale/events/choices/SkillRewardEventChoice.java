package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.rewards.SkillReward;
import com.fastcat.labyrintale.rewards.SkillRewardNormal;
import com.fastcat.labyrintale.screens.skillselect.SkillSelectScreen;

public class SkillRewardEventChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

    private final AbstractEvent event;
    private final int toPage;

    public SkillRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event) {
        this(t, condition, event, -1);
    }

    public SkillRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
    }

    @Override
    protected void onSelect() {
        SkillSelectScreen s = new SkillSelectScreen(SkillReward.SkillRewardType.NORMAL, new SkillRewardNormal(AbstractLabyrinth.selection));
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
