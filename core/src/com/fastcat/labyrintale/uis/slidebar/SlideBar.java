package com.fastcat.labyrintale.uis.slidebar;

import static com.fastcat.labyrintale.handlers.InputHandler.mx;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class SlideBar extends AbstractUI {

  private final SlideLine line;
  private final SlideSideL sideL;
  private final SlideSideR sideR;
  private final SlideButton button;

  public int min, max, abs;
  public int pos; // 0 ~ 100;

  public SlideBar(float x, float y, int start) {
    this(x, y, 0, 100, start);
  }

  public SlideBar(float x, float y, int min, int max, int start) {
    super(FileHandler.getUi().get("SLIDE_A"), x, y);
    sideL = new SlideSideL();
    sideL.setPosition(x, y);
    line = new SlideLine(width);
    line.setPosition(x + sideL.sWidth, y);
    sideR = new SlideSideR();
    sideR.setPosition(x + sideL.sWidth + line.sWidth, y);
    this.min = min;
    this.max = max;
    abs = max - min;
    pos = start;
    button = new SlideButton();
    button.min = x;
    button.max = x + sWidth - button.sWidth;
    button.setPosition(x + ((sWidth - button.sWidth) * ((float) pos / abs)), y);
  }

  @Override
  protected void updateButton() {
    if (!clicking) button.overTrack = false;
    button.update();
    pos = MathUtils.floor((button.x - x) / (button.max - x) * abs);
    sideL.update();
    line.update();
    sideR.update();
  }

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      sb.setColor(Color.WHITE);
      sideL.render(sb);
      line.render(sb);
      sideR.render(sb);
      button.render(sb);
    }
  }

  @Override
  public void setParent(AbstractUI ui) {
    sideL.setParent(ui);
    line.setParent(ui);
    sideR.setParent(ui);
    button.setParent(ui);
  }

  @Override
  public void onClick() {
    button.overTrack = true;
  }

  public static class SlideButton extends AbstractUI {

    protected boolean overTrack = false;
    protected float min, max;

    public SlideButton() {
      super(FileHandler.getUi().get("SLIDE_B"));
      clickable = false;
    }

    @Override
    protected void updateButton() {
      if (overTrack) setPosition(MathUtils.clamp(mx - sWidth / 2, min, max), y);
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      if (enabled) {
        sb.setColor(Color.WHITE);
        sb.draw(img, x, y, sWidth, sHeight);
      }
    }
  }

  public static class SlideLine extends AbstractUI {
    public SlideLine(float width) {
      super(FileHandler.getUi().get("SLIDE_L"), -1000, -1000, width - 12, 40);
      clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      if (enabled) {
        sb.setColor(Color.WHITE);
        sb.draw(img, x, y, sWidth, sHeight);
      }
    }
  }

  public static class SlideSideL extends AbstractUI {
    public SlideSideL() {
      super(FileHandler.getUi().get("SLIDE_SL"));
      clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      if (enabled) {
        sb.setColor(Color.WHITE);
        sb.draw(img, x, y, sWidth, sHeight);
      }
    }
  }

  public static class SlideSideR extends AbstractUI {
    public SlideSideR() {
      super(FileHandler.getUi().get("SLIDE_SR"));
      clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      if (enabled) {
        sb.setColor(Color.WHITE);
        sb.draw(img, x, y, sWidth, sHeight);
      }
    }
  }
}
