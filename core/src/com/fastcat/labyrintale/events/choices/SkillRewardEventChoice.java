package com.fastcat.labyrintale.events.choices;

import static com.fastcat.labyrintale.handlers.GroupHandler.SkillGroup.getRandomSkill;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class SkillRewardEventChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

    private final AbstractEvent event;
    private final int toPage;
    private boolean cancel = true;

    public SkillRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
    }

    public SkillRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event, int page, boolean cancel) {
        super(t, condition);
        this.event = event;
        toPage = page;
        this.cancel = cancel;
    }

    @Override
    protected void onSelect() {
        ShopTakeScreen s = new ShopTakeScreen(
                getRandomSkill(AbstractLabyrinth.players[AbstractLabyrinth.publicRandom.random(0, 3)]));
        s.endTemp.add(this);
        s.canCancel = cancel;
        Labyrintale.addTempScreen(s);
    }

    @Override
    public void atEndOfTempScreen() {
        if (toPage >= 0) {
            event.setPage(toPage);
        }
    }
}
