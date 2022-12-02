package com.fastcat.labyrintale.screens.event;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractEvent;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class EventImage extends AbstractUI {

  public AbstractEvent event;
  public FontHandler.FontData fontName = EVENT_TITLE;
  public FontHandler.FontData fontDesc = EVENT_DESC;
  public float nx, ny, nw, nh, dx, dy, dw, dh;

  public EventImage(AbstractEvent e) {
    super(FileHandler.getUi().get("EVENT"));
    setPosition(626 * scale, 686 * scale);
    event = e;
    overable = false;
    nx = 1290 * scale;
    dx = 1095 * scale;
    ny = 1169 * scale;
    dy = 1099 * scale;
    nw = 404 * scale;
    dw = 706 * scale;
    nh = 58 * scale;
    dh = 215 * scale;
  }

  @Override
  protected void updateButton() {}

  @Override
  protected void renderUi(SpriteBatch sb) {
    if (enabled) {
      if (event != null) {
        // sb.setColor(Color.WHITE);
        sb.draw(event.img, x, y, sWidth, sHeight);
        renderCenter(sb, fontName, event.name, nx, ny, nw, nh);
        renderColorLeft(sb, fontDesc, event.desc, dx, dy, dw);
      }
      // sb.draw(img, x, y, sWidth, sHeight);
    }
  }
}
