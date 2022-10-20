package com.fastcat.labyrintale.abstracts;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.publicRandom;

import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.GroupHandler;

public abstract class AbstractEnemy extends AbstractEntity {

  public EnemyType type;
  public Array<AbstractSkill> drawPile;
  public Array<AbstractSkill> discardPile;
  public Array<AbstractSkill> disposablePile;
  public boolean isRandom = true;

  public AbstractEnemy(String id, EnemyType type, int maxHealth) {
    super(
        id, 1, maxHealth, FileHandler.getAtlas().get(id), FileHandler.getSkeleton().get(id), false);
    this.type = type;
    drawPile = new Array<>();
    discardPile = new Array<>();
    disposablePile = new Array<>();
    stat.debuRes = 0.05f;
    stat.neutRes = 0.05f;
    stat.critical = 0.05f;
    stat.moveRes = 0.05f;
    stat.multiply = 0.5f;
    img = FileHandler.getUi().get("E_IMAGE");
  }

  @Override
  public void newDeck() {
    hand = new AbstractSkill[handSize];
    drawPile.clear();
    discardPile.clear();
    disposablePile.clear();
    drawPile = new Array<>(deck);
  }

  public final void shuffleHand() {
    for (int i = 0; i < handSize; i++) {
      AbstractSkill s = hand[i];
      if (s != null) {
        if (s.usedOnce) disposablePile.add(hand[i]);
        else discardPile.add(hand[i]);
      }
    }
    hand = new AbstractSkill[handSize];
    if (drawPile.size < handSize && discardPile.size > 0) {
      drawPile.addAll(discardPile);
      discardPile.clear();
    }
    if (isRandom) GroupHandler.SkillGroup.staticShuffle(drawPile, publicRandom);
    for (int i = 0; i < handSize; i++) {
      if (i < drawPile.size) {
        AbstractSkill s = drawPile.removeIndex(0);
        hand[i] = s;
      } else break;
    }
  }

  public void atEndOfRound() {}

  public enum EnemyType {
    WEAK,
    NORMAL,
    ELITE,
    BOSS
  }
}
