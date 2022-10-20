package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.skills.player.basic.MoveP;
import com.fastcat.labyrintale.skills.player.basic.PassTurn;
import com.fastcat.labyrintale.strings.CharString;

public abstract class AbstractPlayer extends AbstractEntity {

  public final Color pColor;
  public final Color pColorW;
  public final Color pColorLG;
  public final Color pColorDG;
  public final PlayerClass playerClass;
  public Sprite imgPanel;

  public AbstractPlayer(String id, int maxHealth, Color c) {
    super(
        id, 3, maxHealth, FileHandler.getAtlas().get(id), FileHandler.getSkeleton().get(id), true);
    this.playerClass = PlayerClass.valueOf(id.toUpperCase());

    CharString.CharData temp = StringHandler.charString.get(id);
    name = temp.NAME;
    desc = temp.DESC;
    pColor = c.cpy();
    pColorW = c.cpy().mul(0.827f, 0.827f, 0.827f, 1);
    pColorLG = c.cpy().mul(0.663f, 0.663f, 0.663f, 1);
    pColorDG = c.cpy().mul(0.5f, 0.5f, 0.5f, 1);
    move = new MoveP(this);
    pass = new PassTurn(this);
    setImage(
        FileHandler.getCharImg().get(playerClass),
        FileHandler.getCharImgBig().get(playerClass),
        FileHandler.getCharBgImg().get(playerClass));
    imgTiny = FileHandler.getCharImgTiny().get(playerClass);
    imgPanel = FileHandler.getCharPanelImg().get(playerClass);
    Array<AbstractItem> t = getStartingItem();
    for (int j = 0; j < 2; j++) {
      AbstractItem it = t.get(j);
      it.onGain();
      item[j] = it;
    }
    passive = getPassive();
  }

  public static String getClassName(PlayerClass playerClass) {
    return playerClass.toString().toLowerCase();
  }

  @Override
  public void newDeck() {
    hand = new AbstractSkill[3];
    for (int i = 0; i < 3; i++) {
      hand[i] = deck.get(i).clone();
    }
    moveTemp = move.clone();
    pass = new PassTurn(this);
  }

  public void gainItem(AbstractItem i, int index) {
    item[index].onRemove();
    i.owner = this;
    item[index] = i;
    item[index].onGain();
  }

  public boolean hasSlot() {
    int i = AbstractLabyrinth.maxSkillUp;
    return slot[0] < i || slot[1] < i || slot[2] < i;
  }

  public abstract Array<AbstractItem> getStartingItem();

  public abstract AbstractItem getPassive();

  public enum PlayerClass {
    WAK,
    INE,
    VIICHAN,
    LILPA,
    BURGER,
    GOSEGU,
    JURURU,
    MANAGER
  }
}
