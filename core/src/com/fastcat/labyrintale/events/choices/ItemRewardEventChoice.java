package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class ItemRewardEventChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

    private final AbstractEvent event;
    private final int toPage;

    public ItemRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event) {
        this(t, condition, event, -1);
    }

    public ItemRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
    }

    @Override
    protected void onSelect() {
        ShopTakeScreen s = new ShopTakeScreen(GroupHandler.ItemGroup.getRandomItem());
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
