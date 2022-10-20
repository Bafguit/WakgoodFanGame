package com.fastcat.labyrintale.screens.riskselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.RestrictionHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.RiskString;

public class RiskIcon extends AbstractUI {

  private RiskString.RiskData data;
  private Sprite[] icon;
  private RestrictionHandler.RiskType type;
  public int diff;

  public RiskIcon(RestrictionHandler.RiskType type, int a) {
    super(FileHandler.getUi().get("BORDER"));
    icon = new Sprite[4];
    icon[0] = img;
    for (int i = 1; i < 4; i++) {
      icon[i] = FileHandler.getRiskImg().get(type.name() + "_" + i);
    }
    this.type = type;
    diff = a;
    data = StringHandler.riskString.get(type.name());
    subTexts = new Array<>();
    if (diff > 0) {
      subTexts.add(new SubText(data.NAME, data.DESC[diff - 1]));
    }
  }

  @Override
  protected Array<SubText> getSubText() {
    subTexts.clear();
    if (diff > 0)
      subTexts.add(new SubText(data.NAME + " " + "I".repeat(diff), data.DESC[diff - 1]));
    return subTexts;
  }

  public RestrictionHandler.RiskType getType() {
    return type;
  }

  public void setDiff(int a) {
    diff = a;
  }

  @Override
  protected void updateButton() {
    overable = diff > 0;
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (overable) {
      sb.setColor(Color.WHITE);
      sb.draw(icon[diff], x, y, sWidth, sHeight);
    }
  }

  @Override
  protected void onClick() {
    SettingHandler.setting.risk.replace(type, 0);
  }
}
