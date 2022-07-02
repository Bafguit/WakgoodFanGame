package com.fastcat.labyrintale.rewards;

import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedItem;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class ItemReward extends AbstractReward implements GetSelectedItem {

    private final AbstractItem item;

    public ItemReward(AbstractItem item) {
        super(RewardType.ITEM);
        this.item = item;
        setInfo(item.name, item.desc);
        img = item.img;
    }

    public ItemReward(ItemRewardType type) {
        this(generateItem(type));
    }

    private static AbstractItem generateItem(ItemRewardType type) {
        if(type == ItemRewardType.NORMAL) {
            return GroupHandler.ItemGroup.getRandomItem();
        } else if(type == ItemRewardType.BRONZE) {
            return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.BRONZE);
        } else if(type == ItemRewardType.SILVER) {
            return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.SILVER);
        } else if (type == ItemRewardType.GOLD) {
            return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.GOLD);
        } else {
            return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.BOSS);
        }
    }

    @Override
    public void takeReward() {
        Labyrintale.addTempScreen(new ShopTakeScreen(item, this));
    }

    @Override
    public void itemSelected(AbstractItem item) {

    }

    public enum ItemRewardType {
        NORMAL, BRONZE, SILVER, GOLD, BOSS
    }
}
