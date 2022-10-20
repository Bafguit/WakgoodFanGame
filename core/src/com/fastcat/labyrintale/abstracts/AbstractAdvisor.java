package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.AdvisorString;

public abstract class AbstractAdvisor implements Cloneable {

  public AdvisorClass cls;
  public AbstractSkill skill;
  public AdvisorString.AdvisorData data;
  public Sprite img;

  public AbstractAdvisor(AdvisorClass cls, AbstractSkill skill) {
    this.cls = cls;
    this.skill = skill;
    img = FileHandler.getAdvImg().get(cls);
    data = StringHandler.advisorString.get(cls.toString().toLowerCase());
  }

  public void onHire() {}

  public void onFire() {}

  @Override
  public AbstractAdvisor clone() {
    try {
      return (AbstractAdvisor) super.clone();
    } catch (CloneNotSupportedException e) {
      throw new AssertionError();
    }
  }

  public enum AdvisorClass {
    DOPA,
    SECRET,
    RUSEOK,
    HIKI,
    PUNG,
    CALLYCARLY,
    MANDU,
    JK,
    SOPHIA,
    DUKSU,
    HAKU,
    BUSINESS,
    FREETER,
    CARNAR
  }
}
