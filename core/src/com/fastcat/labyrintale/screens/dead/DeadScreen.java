package com.fastcat.labyrintale.screens.dead;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class DeadScreen extends AbstractScreen {

  public DeadText logo;
  public MainButton mainButton;

  public DeadScreen(ScreenType type) {
    logo = new DeadText();
    if (type == ScreenType.DEAD) {
      logo.text = "개같이 멸망";
      setBg(FileHandler.getBg().get("BG_DEAD"));
    } else {
      logo.text = "해냈다 해냈어!";
      setBg(FileHandler.getBg().get("BG_WIN"));
    }
    mainButton = new MainButton();
    cType = ControlPanel.ControlType.HIDE;
  }

  @Override
  public void update() {
    mainButton.update();
    logo.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    mainButton.render(sb);
    logo.render(sb);
  }

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}

  public enum ScreenType {
    DEAD,
    WIN
  }
}
