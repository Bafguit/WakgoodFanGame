package com.fastcat.labyrintale.screens.resultscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import java.io.File;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;

import static com.fastcat.labyrintale.handlers.FontHandler.MAIN_MENU_BORDER;

public class ScreenshotButton extends AbstractUI {

  private String date;

  public ScreenshotButton() {
    super(FileHandler.getUi().get("NEXT"));
    setPosition(Gdx.graphics.getWidth() * 0.83f - sWidth, Gdx.graphics.getHeight() * 0.05f);
    fontData = MAIN_MENU_BORDER;
    text = "스크린샷";
    showImg = false;
    setDate("");
  }

  public void setDate(String s) {
    date = s;
  }

  @Override
  protected void onClick() {
    Pixmap pixmap = Pixmap.createFromFrameBuffer(0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
    ByteBuffer pixels = pixmap.getPixels();

// This loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
    int size = Gdx.graphics.getBackBufferWidth() * Gdx.graphics.getBackBufferHeight() * 4;
    for (int i = 3; i < size; i += 4) {
      pixels.put(i, (byte) 255);
    }

    new File("screenshots").mkdir();
    PixmapIO.writePNG(Gdx.files.local("screenshots/screenshot_" + date + ".png"), pixmap, Deflater.DEFAULT_COMPRESSION, true);
    pixmap.dispose();
    text = "스크린샷 저장됨";
  }
}
