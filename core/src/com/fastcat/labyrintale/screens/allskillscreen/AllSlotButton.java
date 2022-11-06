package com.fastcat.labyrintale.screens.allskillscreen;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.screens.slotselect.SlotSelectScreen;

public class AllSlotButton extends AbstractUI {

  private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
  private final FontHandler.FontData fd = FontHandler.CARD_BIG_DESC;
  public AbstractPlayer player;
  public AbstractSkill skill;
  public int index;
  public AllSkillScreen select;

  public AllSlotButton(AbstractPlayer player, int index, AllSkillScreen select) {
    super(FileHandler.getUi().get("SLOT_UP"));
    this.player = player;
    this.index = index;
    skill = player.deck.get(index);
    this.select = select;
  }

  @Override
  protected void updateButton() {
    if (over) {
      AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
    }
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (over) sb.setColor(Color.WHITE);
      else sb.setColor(Color.LIGHT_GRAY);
      sb.draw(skill.img, x, y, sWidth, sHeight);
      sb.setColor(Color.WHITE);
      if (!skill.passive) {
        sb.draw(cost, x - sWidth * 0.2f, y + sWidth * 0.7f, sWidth * 0.5f, sWidth * 0.5f);
        FontHandler.renderCenter(
            sb,
            fd,
            Integer.toString(skill.cost),
            x - sWidth * 0.05f,
            y + sWidth * 0.95f,
            sWidth * 0.2f,
            sWidth * 0.2f);
      }
    }
  }

  @Override
  protected void onOver() {}

  @Override
  protected void onClick() {
    select.slotSelected(player, index);
    Labyrintale.removeTempScreen(select);
  }
}