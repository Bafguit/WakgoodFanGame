package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class RunViewDeckIcon extends AbstractUI {

  private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
  private final FontHandler.FontData up = FontHandler.UPGRADE;
  public AbstractSkill skill;

  public RunViewDeckIcon() {
    super(FileHandler.getUi().get("BORDER"));
    clickable = false;
    fontData = FontHandler.SUB_NAME;
  }

  public void setSkill(AbstractSkill skill) {
    this.skill = skill;
  }

  @Override
  protected Array<SubText> getSubText() {
    return skill != null ? skill.key : null;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled && skill != null) {
      sb.setColor(Color.WHITE);
      if (showImg) sb.draw(skill.img, x, y, sWidth, sHeight);
      sb.draw(img, x, y, sWidth, sHeight);

      if (!skill.passive) {
        sb.draw(cost, x - sWidth * 0.2f, y + sWidth * 0.7f, sWidth * 0.5f, sWidth * 0.5f);
        FontHandler.renderCenter(
            sb,
            fontData,
            Integer.toString(skill.cost),
            x - sWidth * 0.05f,
            y + sWidth * 0.95f,
            sWidth * 0.2f,
            sWidth * 0.2f);
      }
      if(skill.upgradeCount > 0) {
        FontHandler.renderLineRight(
                sb,
                up,
                "+" + skill.upgradeCount,
                x + sWidth * 0.75f, y + sWidth * 0.2f,
                sWidth * 0.2f,
                sWidth * 0.2f);
      }
    }
  }
}
