package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.handlers.FontHandler.renderLineLeft;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractEntity.EntityStat;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.strings.KeyString;

public class StatIcon extends AbstractUI {

  private final Sprite icon;
  public AbstractEntity entity;
  public StatType type;
  public int amount;
  public boolean isUp = false;

  private GetSelectedStat gets;

  public StatIcon(StatType type) {
    super(FileHandler.getUi().get("BORDER_SS"));
    this.type = type;
    icon = FileHandler.getUi().get("STAT_" + type.toString());
    KeyString.KeyData data = StringHandler.keyString.get(type.toString().toLowerCase());
    subTexts = new Array<>();
    subTexts.add(new SubText(data.NAME, data.DESC));
    fontData = FontHandler.SUB_NAME;
    clickable = false;
  }

  public StatIcon(AbstractEntity e, StatType type, GetSelectedStat get) {
    super(FileHandler.getUi().get("BORDER_SS"));
    this.type = type;
    icon = FileHandler.getUi().get("STAT_" + type.toString());
    KeyString.KeyData data = StringHandler.keyString.get(type.toString().toLowerCase());
    subTexts = new Array<>();
    subTexts.add(new SubText(data.NAME, data.DESC));
    fontData = FontHandler.SUB_NAME;
    entity = e;
    isUp = true;
    clickable = e.isAlive() && AbstractLabyrinth.sp > 0;
    gets = get;
  }

  @Override
  protected void updateButton() {
    if (isUp) clickable = entity.isAlive() && AbstractLabyrinth.sp > 0 && amount < 80;
    if (type == StatType.ATTACK) amount = entity.stat.attack;
    else if (type == StatType.SPELL) amount = entity.stat.spell;
    else if (type == StatType.SPEED) amount = entity.stat.capSpeed();
    else {
      if (type == StatType.CRITICAL) amount = entity.hasItem("TotoDeck") ? 20 : EntityStat.cap(entity.stat.critical);
      else if (type == StatType.MULTIPLY) amount = entity.stat.multiply;
      else if (type == StatType.MOVERES) amount = EntityStat.cap(entity.stat.moveRes);
      else if (type == StatType.DEBURES) amount = EntityStat.cap(entity.stat.debuRes);
      else if (type == StatType.NEUTRES) amount = EntityStat.neutCap(entity);
    }
  }

  @Override
  protected Array<SubText> getSubText() {
    return subTexts;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (isUp) {
        if (!clickable || (type != StatType.MULTIPLY && amount >= 80)) sb.setColor(Color.DARK_GRAY);
        else if (over) sb.setColor(Color.WHITE);
        else sb.setColor(Color.LIGHT_GRAY);
      } else sb.setColor(Color.WHITE);
      sb.draw(icon, x, y, sWidth, sHeight);
      sb.draw(img, x, y, sWidth, sHeight);
      if (entity != null) {
        String t = "";
        if (type == StatType.ATTACK || type == StatType.SPELL || type == StatType.SPEED) t += amount;
        else t = amount + "%";
        renderLineLeft(sb, fontData, t, x + sWidth, y + sHeight / 2, sWidth * 3, sHeight);
      }
    }
  }

  @Override
  public void onClick() {
    if (amount < 80) {
      if (type == StatType.CRITICAL) entity.stat.critical += 5;
      else if (type == StatType.MOVERES) entity.stat.moveRes += 5;
      else if (type == StatType.DEBURES) entity.stat.debuRes += 5;
      else if (type == StatType.NEUTRES) entity.stat.neutRes += 5;
    }
    AbstractLabyrinth.sp--;
    gets.statSelected(entity, type);
  }

  public enum StatType {
    ATTACK,
    SPELL,
    SPEED,
    MULTIPLY,
    CRITICAL,
    MOVERES,
    DEBURES,
    NEUTRES
  }
}
