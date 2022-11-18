package com.fastcat.labyrintale.rewards;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedItem;
import com.fastcat.labyrintale.screens.itemselect.ItemSelectScreen;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class ItemReward extends AbstractReward implements GetSelectedItem {

  public final ItemRewardType type;
  public AbstractItem item;
  public AbstractItem[] items;

  public ItemReward(ItemRewardType type) {
    super(RewardType.ITEM);
    this.type = type;
    if(type == ItemRewardType.BOSS) {
      items = GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.BOSS, 3).toArray(AbstractItem.class);
      img = FileHandler.getUi().get("DISCARD");
      setInfo("보스 아이템", "보스 아이템 3개 중 하나를 선택해 획득합니다.");
    } else {
      item = generateItem(type);
      img = item.img;
      setInfo(item.name, item.desc);
    }
  }

  private static AbstractItem generateItem(ItemRewardType type) {
    if (type == ItemRewardType.BRONZE) {
      return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.BRONZE);
    } else if (type == ItemRewardType.SILVER) {
      return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.SILVER);
    } else if (type == ItemRewardType.GOLD) {
      return GroupHandler.ItemGroup.getRandomItemByRarity(AbstractItem.ItemRarity.GOLD);
    } else {
      return GroupHandler.ItemGroup.getRandomItem();
    }
  }

  @Override
  public void takeReward() {
    if(type == ItemRewardType.BOSS) Labyrintale.addTempScreen(new ItemSelectScreen(items, this, this, true));
    else Labyrintale.addTempScreen(new ShopTakeScreen(item, this, this));
  }

  @Override
  public void itemSelected(AbstractItem item) {}

  public enum ItemRewardType {
    NORMAL,
    BRONZE,
    SILVER,
    GOLD,
    BOSS
  }
}
