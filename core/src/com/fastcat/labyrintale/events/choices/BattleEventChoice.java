package com.fastcat.labyrintale.events.choices;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.fadeOutAndChangeScreen;

import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.screens.battle.BattleScreen;

public class BattleEventChoice extends AbstractEvent.EventChoice {

  public AbstractRoom battle;

  public BattleEventChoice(String t, AbstractRoom battle) {
    this(t, battle, new AbstractEvent.EventCondition.True());
  }

  public BattleEventChoice(String t, AbstractRoom battle, AbstractEvent.EventCondition condition) {
    super(t, condition);
    this.battle = battle;
  }

  @Override
  protected void onSelect() {
    AbstractLabyrinth.currentFloor.currentRoom.enemies = battle.enemies;
    battleScreen = new BattleScreen(BattleScreen.BattleType.EVENT, false);
    SoundHandler.fadeOutAll();
    SoundHandler.addBattle();
    fadeOutAndChangeScreen(battleScreen);
  }
}
