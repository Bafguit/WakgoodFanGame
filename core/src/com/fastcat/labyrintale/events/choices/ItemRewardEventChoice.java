package com.fastcat.labyrintale.events.choices;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class ItemRewardEventChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

    private final AbstractEvent event;
    private final int toPage;
    public AbstractItem item;
    public AbstractItem.ItemRarity rarity;

    public ItemRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event) {
        this(t, condition, event, -1);
    }

    public ItemRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
    }

    public ItemRewardEventChoice(String t, AbstractItem.ItemRarity rarity, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
        this.rarity = rarity;
    }

    public ItemRewardEventChoice(String t, AbstractItem item, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
        this.item = item;
    }

    @Override
    protected void onSelect() {
        ShopTakeScreen s;
        if (item != null) s = new ShopTakeScreen(item);
        else if (rarity != null) s = new ShopTakeScreen(GroupHandler.ItemGroup.getRandomItemByRarity(rarity));
        else s = new ShopTakeScreen(GroupHandler.ItemGroup.getRandomItem());
        s.endTemp.add(this);
        Labyrintale.addTempScreen(s);
    }

    @Override
    public void atEndOfTempScreen() {
        if (toPage >= 0) {
            event.setPage(toPage);
        }
    }
}
