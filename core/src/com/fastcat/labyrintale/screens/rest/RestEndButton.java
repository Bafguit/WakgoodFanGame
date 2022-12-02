package com.fastcat.labyrintale.screens.rest;

import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;

import com.badlogic.gdx.Gdx;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class RestEndButton extends AbstractUI {

  public RestEndButton() {
    super(FileHandler.getUi().get("BUTTON"));
    setPosition(Gdx.graphics.getWidth() * 0.98f - sWidth, Gdx.graphics.getHeight() * 0.4f);
    fontData = BUTTON;
    text = "계속";
  }

  @Override
  protected void onClick() {
    AbstractLabyrinth.endRoom();
    Labyrintale.returnToWay();
  }
}
