package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.handlers.UnlockHandler;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;
import java.util.HashMap;

public class ItemRewardEventChoice extends AbstractEvent.EventChoice implements AtEndOfTempScreen {

    private final AbstractEvent event;
    private final int toPage;
    public AbstractItem item;
    public AbstractItem.ItemRarity rarity;
    public Array<AbstractUI.SubText> sub = new Array<>();

    public ItemRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event) {
        this(t, condition, event, -1);
    }

    public ItemRewardEventChoice(String t, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
    }

    public ItemRewardEventChoice(
            String t,
            AbstractItem.ItemRarity rarity,
            AbstractEvent.EventCondition condition,
            AbstractEvent event,
            int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
        this.rarity = rarity;
    }

    public ItemRewardEventChoice(
            String t, AbstractItem item, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
        super(t, condition);
        this.event = event;
        toPage = page;
        this.item = item;
        if (item.sub != null) sub.add(new AbstractUI.SubText(item.name, item.sub));
    }

    @Override
    public Array<AbstractUI.SubText> getSubText() {
        return sub;
    }

    @Override
    protected void onSelect() {
        ShopTakeScreen s;
        if (item != null) {
            if (item.rarity == AbstractItem.ItemRarity.SPECIAL) {
                HashMap<String, Boolean> temp = UnlockHandler.achvs.get(UnlockHandler.Unlocks.ITEM);
                if (!temp.get(item.id)) temp.replace(item.id, true);
            }
            s = new ShopTakeScreen(item);
        } else if (rarity != null) s = new ShopTakeScreen(GroupHandler.ItemGroup.getRandomItemByRarity(rarity));
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
