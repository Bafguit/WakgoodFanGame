package com.fastcat.labyrintale.events.choices;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.GroupHandler;
import com.fastcat.labyrintale.interfaces.AtEndOfTempScreen;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedItem;
import com.fastcat.labyrintale.screens.itemselect.ItemSelectScreen;
import com.fastcat.labyrintale.screens.shop.take.ShopTakeScreen;

public class ItemSelectEventChoice extends AbstractEvent.EventChoice implements GetSelectedItem, GetRewardDone {

  private final AbstractEvent event;
  private final int toPage;
  private final int amount;
  public AbstractItem item;
  public AbstractItem.ItemRarity rarity;
  public Array<AbstractUI.SubText> sub = new Array<>();

  public ItemSelectEventChoice(
      String t, int amount, AbstractEvent.EventCondition condition, AbstractEvent event, int page) {
    super(t, condition);
    this.amount = amount;
    this.event = event;
    toPage = page;
  }

  @Override
  public Array<AbstractUI.SubText> getSubText() {
    return sub;
  }

  @Override
  protected void onSelect() {
    Labyrintale.addTempScreen(new ItemSelectScreen(amount, this, this, false));
  }

  @Override
  public void itemSelected(AbstractItem item) {

  }

  @Override
  public void isRewardDone(boolean isDone) {
    if (toPage >= 0) {
      event.setPage(toPage);
    }
  }
}
