package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;

public final class LogHandler {

  private final String tag;

  public LogHandler(String tag) {
    this.tag = tag;
  }

  public void log(String msg) {
    Gdx.app.log(tag, msg);
  }

  public void info(String msg) {
    log(msg);
  }

  public void error(String msg) {
    Gdx.app.error(tag, msg);
  }

  public void debug(String msg) {
    Gdx.app.debug(tag, msg);
  }
}
