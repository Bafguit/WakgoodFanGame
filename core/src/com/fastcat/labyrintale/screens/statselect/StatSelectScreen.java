package com.fastcat.labyrintale.screens.statselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.CloseTempScreenButton;
import com.fastcat.labyrintale.uis.StatIcon;

public class StatSelectScreen extends AbstractScreen implements GetSelectedStat {

  public BgImg bg = new BgImg();
  public StatSelectText statSelectText;
  public PlayerStat[][] player;
  public StatIcon[][][][] stats;
  public GetSelectedStat gets;
  public CloseTempScreenButton close;

  public StatSelectScreen() {
    statSelectText = new StatSelectText();
    // this.gets = gets;
    player = new PlayerStat[2][2];
    stats = new StatIcon[2][2][2][3];
    close = new CloseTempScreenButton(this);
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    int cnt = 0;
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        AbstractPlayer p = AbstractLabyrinth.players[cnt];
        PlayerStat temp = new PlayerStat(p);
        temp.setPosition(w * (0.375f * i + 0.175f), h * (0.675f - 0.175f * j));
        player[i][j] = temp;
        int sc = 4;
        for (int k = 0; k < 2; k++) {
          for (int l = 0; l < 2; l++) {
            StatIcon adv = new StatIcon(p, StatIcon.StatType.values()[sc], this);
            adv.setScale(2);
            adv.setPosition(
                temp.x + temp.sWidth * 1.1f + w * 0.08f * l,
                temp.y + (temp.sHeight - adv.sHeight) * 0.5f + h * 0.03f * (1 - k * 2));
            stats[i][j][k][l] = adv;
            sc++;
          }
        }
        cnt++;
      }
    }
  }

  @Override
  public void update() {
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          for (int l = 0; l < 2; l++) {
            stats[i][j][k][l].update();
          }
        }
      }
    }
    statSelectText.update();
    close.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    bg.render(sb);
    for (int i = 0; i < 2; i++) {
      for (int j = 0; j < 2; j++) {
        for (int k = 0; k < 2; k++) {
          for (int l = 0; l < 2; l++) {
            stats[i][j][k][l].render(sb);
          }
        }
        player[i][j].render(sb);
      }
    }
    statSelectText.render(sb);
    close.render(sb);
  }

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void statSelected(AbstractEntity entity, StatIcon.StatType stat) {}

  public static class PlayerStat extends AbstractUI.TempUI {

    public AbstractPlayer player;

    public PlayerStat(AbstractPlayer p) {
      super(FileHandler.getUi().get("BORDER_M"));
      player = p;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      if (enabled) {
        if (player.isAlive()) sb.setColor(Color.WHITE);
        else sb.setColor(Color.DARK_GRAY);
        sb.draw(player.img, x, y, sWidth, sHeight);
        sb.draw(img, x, y, sWidth, sHeight);
      }
    }
  }
}
