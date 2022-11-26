package com.fastcat.labyrintale.screens.resultscreen;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.BuildInfo;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoDeckIcon;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoIcon;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoItemIcon;
import com.fastcat.labyrintale.screens.playerinfo.UpgradeStatButton;
import com.fastcat.labyrintale.screens.statselect.StatSelectScreen;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.PlayerIcon;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.Labyrintale.playerInfoScreen;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class ResultScreen extends AbstractScreen implements GetSelectedStat {

  public static final Color hbc = new Color(0.4f, 0, 0, 1);

  private final FontHandler.FontData fontName = ENERGY;
  private final FontHandler.FontData fontHp = INFO_HP_BORDER;
  private final FontHandler.FontData fontData = ENERGY;
  private final int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();

  public Sprite hb = FileHandler.getUi().get("HEALTH_BAR");
  public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
  public ShapeRenderer shr = new ShapeRenderer();
  public PlayerInfoDeckIcon[][] deck = new PlayerInfoDeckIcon[4][3];
  public PlayerInfoItemIcon[][] item = new PlayerInfoItemIcon[4][2];
  public PlayerInfoItemIcon[] passive = new PlayerInfoItemIcon[4];
  public StatIcon[][] stats = new StatIcon[4][8];
  public PlayerInfoIcon[] pIcons = new PlayerInfoIcon[4];
  public ResultText text;
  public BackToMainButton back;
  public ScreenshotButton shot;
  public ResultAdvisor adv;
  public DeadScreen.ScreenType dType;
  public String diff;
  public String time;
  public String ver;
  public String seed;
  public String score;

  public ResultScreen(DeadScreen.ScreenType type) {
    if (type == DeadScreen.ScreenType.DEAD) {
      setBg(FileHandler.getBg().get("BG_DEAD"));
    } else {
      setBg(FileHandler.getBg().get("BG_WIN"));
    }
    dType = type;
    AbstractLabyrinth.result = type;
    int c = 0;
    for (int g = 0; g < 2; g++) {
      for (int f = 0; f < 2; f++) {
        AbstractPlayer player = AbstractLabyrinth.players[c];
        for (int i = 0; i < 3; i++) {
          PlayerInfoDeckIcon b = new PlayerInfoDeckIcon(player.deck.get(i));
          b.setPosition(
              w * (0.175f + 0.46f * g + 0.06f * i) - b.sWidth / 2, h * (0.6f - 0.275f * f));
          deck[c][i] = b;
        }
        for (int i = 0; i < 2; i++) {
          PlayerInfoItemIcon b = new PlayerInfoItemIcon(player.item[i]);
          b.setPosition(
              w * (0.365f + 0.46f * g + 0.06f * i) - b.sWidth / 2, h * (0.6f - 0.275f * f));
          item[c][i] = b;
        }
        PlayerInfoItemIcon ps = new PlayerInfoItemIcon(player.passive);
        ps.setPosition(w * (0.305f + 0.46f * g) - ps.sWidth / 2, h * (0.71f - 0.275f * f));
        passive[c] = ps;
        int cnt = 0;
        for (int j = 3; j >= 0; j--) {
          for (int i = 1; i >= 0; i--) {
            StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
            s.setEntity(player);
            s.setPosition(
                w * (0.398f + 0.46f * g - 0.055f * i), h * (0.7f - 0.275f * f + 0.027f * j));
            stats[c][cnt++] = s;
          }
        }
        PlayerInfoIcon pc = new PlayerInfoIcon(c);
        pc.clickable = false;
        pc.setPosition(w * (0.125f + 0.46f * g) - pc.sWidth, h * (0.59f - 0.275f * f));
        pIcons[c] = pc;
        c++;
      }
    }
    adv = new ResultAdvisor();
    adv.item = AbstractLabyrinth.advisor;
    adv.setPosition(w * 0.1f, h * 0.15f);
    text = new ResultText(type);
    cType = ControlPanel.ControlType.HIDE;
    diff = "난이도: ";
    if(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.NORMAL) diff += "일반";
    else if(AbstractLabyrinth.diff == AbstractLabyrinth.Difficulty.HARD) diff += "어려움";
    else diff += "관";
    time = "소요 시간: " + AbstractLabyrinth.minute + "분 " + AbstractLabyrinth.second + "초";
    ver = "버전: " + BuildInfo.BUILD_VERSION;
    seed = "시드: " + AbstractLabyrinth.seed;
    score = "점수: " + AbstractLabyrinth.scoreHandle.score;
    shot = new ScreenshotButton();
    back = new BackToMainButton();
  }

  @Override
  public void update() {
    cType = ControlPanel.ControlType.HIDE;

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        deck[i][j].skill = AbstractLabyrinth.players[i].deck.get(j);
        deck[i][j].update();
      }
      for (int j = 0; j < 2; j++) {
        item[i][j].skill = AbstractLabyrinth.players[i].item[j];
        item[i][j].update();
      }
      for (int j = 0; j < 8; j++) {
        stats[i][j].entity = AbstractLabyrinth.players[i];
        stats[i][j].update();
      }
      passive[i].skill = AbstractLabyrinth.players[i].passive;
      passive[i].update();
      pIcons[i].index = AbstractLabyrinth.players[i].index;
      pIcons[i].update();
    }
    adv.update();
    text.update();
    shot.update();
    back.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    // spine
    // player.infoSpine.render(sb);

    // health bar

    // button
    text.render(sb);
    shot.render(sb);
    back.render(sb);
    adv.render(sb);
    int cnt = 0;
    for (int f = 0; f < 2; f++) {
      for (int g = 0; g < 2; g++) {
        AbstractPlayer player = AbstractLabyrinth.players[cnt++];
        sb.draw(hbb, w * (0.15f + 0.46f * f), h * (0.73f - 0.275f * g), w * 0.12f, h * 0.03f);
        sb.draw(hb,
                w * (0.15f + 0.46f * f),
                h * (0.73f - 0.275f * g), 0, 0, w * 0.12f, h * 0.03f,
                Math.max(((float) player.health) / ((float) player.maxHealth), 0), 1, 0);
        FontHandler.renderLineLeft(
                sb,
                fontName,
                player.name,
                w * (0.15f + 0.46f * f),
                h * (0.79f - 0.275f * g),
                w * 0.12f,
                50);
        FontHandler.renderCenter(
                sb,
                fontHp,
                player.health + "/" + player.maxHealth,
                w * (0.15f + 0.46f * f),
                h * (0.747f - 0.275f * g),
                w * 0.12f,
                h * 0.03f);
      }
    }

    renderCenter(sb, fontData, diff, w * 0.2f, h * 0.25f, w * 0.2f, h * 0.1f);
    renderCenter(sb, fontData, time, w * 0.4f, h * 0.25f, w * 0.2f, h * 0.1f);
    renderCenter(sb, fontData, ver, w * 0.2f, h * 0.18f, w * 0.2f, h * 0.1f);
    renderCenter(sb, fontData, seed, w * 0.4f, h * 0.18f, w * 0.2f, h * 0.1f);
    renderCenter(sb, fontData, score, w * 0.6f, h * 0.18f, w * 0.2f, h * 0.1f);

    for (int i = 0; i < 4; i++) {
      for (int j = 0; j < 3; j++) {
        deck[i][j].render(sb);
      }
      for (int j = 0; j < 2; j++) {
        item[i][j].render(sb);
      }
      for (int j = 0; j < 8; j++) {
        stats[i][j].render(sb);
      }
      passive[i].render(sb);
      pIcons[i].render(sb);
    }
  }

  @Override
  public void show() {
    SoundHandler.playMusic(dType == DeadScreen.ScreenType.WIN ? "WIN" : "LOSS", false, false);
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {}

  @Override
  public void statSelected(AbstractEntity entity, StatIcon.StatType stat) {}
}
