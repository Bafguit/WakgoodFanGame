package com.fastcat.labyrintale.screens.runview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.BuildInfo;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.interfaces.GetSelectedStat;
import com.fastcat.labyrintale.screens.dead.DeadScreen;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoDeckIcon;
import com.fastcat.labyrintale.screens.playerinfo.PlayerInfoItemIcon;
import com.fastcat.labyrintale.screens.resultscreen.BackToMainButton;
import com.fastcat.labyrintale.screens.resultscreen.ResultAdvisor;
import com.fastcat.labyrintale.screens.resultscreen.ResultText;
import com.fastcat.labyrintale.screens.resultscreen.ScreenshotButton;
import com.fastcat.labyrintale.uis.PlayerIcon;
import com.fastcat.labyrintale.uis.StatIcon;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.Objects;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.getPlayerInstance;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class RunViewScreen extends AbstractScreen {

  public static final Color hbc = new Color(0.4f, 0, 0, 1);

  private final FontData fontName = ENERGY;
  private final FontData fontHp = INFO_HP_BORDER;
  private final FontData fontData = ENERGY;
  private final int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();

  public ShapeRenderer shr = new ShapeRenderer();
  public RunViewDeckIcon[][] deck = new RunViewDeckIcon[4][3];
  public RunViewItemIcon[][] item = new RunViewItemIcon[4][2];
  public RunViewItemIcon[] passive = new RunViewItemIcon[4];
  public StatIcon[][] stats = new StatIcon[4][8];
  public RunViewPlayerIcon[] pIcons = new RunViewPlayerIcon[4];
  public NoRunsText noRuns;
  public ResultText text;
  public BackToMainRunButton back;
  public ScreenshotButton shot;
  public ResultAdvisor adv;
  public IndexButton left;
  public IndexButton right;
  public DeadScreen.ScreenType dType;
  public String diff;
  public String time;
  public String ver;
  public String seed;
  public String score;
  public int index = -1;

  public RunViewScreen() {
    setBg(FileHandler.getBg().get("BG_MAP"));
    left = new IndexButton(this, true);
    left.setPosition(w * 0.02f, h * 0.5f - left.sHeight / 2);
    right = new IndexButton(this, false);
    right.setPosition(w * 0.98f - right.sWidth, h * 0.5f - right.sHeight / 2);
    int c = 0;
    for (int g = 0; g < 2; g++) {
      for (int f = 0; f < 2; f++) {
        for (int i = 0; i < 3; i++) {
          RunViewDeckIcon b = new RunViewDeckIcon();
          b.setPosition(
              w * (0.175f + 0.46f * g + 0.06f * i) - b.sWidth / 2, h * (0.6f - 0.275f * f));
          deck[c][i] = b;
        }
        for (int i = 0; i < 2; i++) {
          RunViewItemIcon b = new RunViewItemIcon();
          b.setPosition(
              w * (0.365f + 0.46f * g + 0.06f * i) - b.sWidth / 2, h * (0.6f - 0.275f * f));
          item[c][i] = b;
        }
        RunViewItemIcon ps = new RunViewItemIcon();
        ps.setPosition(w * (0.305f + 0.46f * g) - ps.sWidth / 2, h * (0.71f - 0.275f * f));
        passive[c] = ps;
        int cnt = 0;
        for (int j = 3; j >= 0; j--) {
          for (int i = 1; i >= 0; i--) {
            StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
            s.setPosition(
                w * (0.398f + 0.46f * g - 0.055f * i), h * (0.7f - 0.275f * f + 0.027f * j));
            stats[c][cnt++] = s;
          }
        }
        RunViewPlayerIcon pc = new RunViewPlayerIcon();
        pc.clickable = false;
        pc.setPosition(w * (0.125f + 0.46f * g) - pc.sWidth, h * (0.59f - 0.275f * f));
        pIcons[c] = pc;
        c++;
      }
    }
    adv = new ResultAdvisor();
    adv.setPosition(w * 0.1f, h * 0.15f);
    text = new ResultText(DeadScreen.ScreenType.WIN);
    cType = ControlPanel.ControlType.HIDE;
    diff = "난이도: ";
    time = "소요 시간: ";
    ver = "버전: ";
    seed = "시드: ";
    score = "점수: ";
    shot = new ScreenshotButton();
    back = new BackToMainRunButton();
    noRuns = new NoRunsText();
    if(RunHandler.runs.size > 0) setIndex(true);
  }

  public void setIndex(boolean add) {
    if(add && index < RunHandler.runs.size - 1) index++;
    else if(!add && index > 0) index--;
    SaveHandler.SaveData data = RunHandler.runs.get(index);
    int c = 0;
    for (int g = 0; g < 2; g++) {
      for (int f = 0; f < 2; f++) {
        AbstractPlayer p;
        SaveHandler.PlayerData d = data.players[c];
        p = getPlayerInstance(AbstractPlayer.PlayerClass.valueOf(d.id.toUpperCase()));
        p.defineIndex(d.index);
        p.goodLuck = d.goodLuck;
        p.badLuck = d.badLuck;
        p.maxRes = d.maxRes;
        p.minRes = d.minRes;
        p.isDead = d.isDead;
        p.maxHealth = d.maxHealth;
        p.health = d.health;

        for (int j = 0; j < 2; j++) {
          AbstractItem it =
                  Objects.requireNonNull(GroupHandler.ItemGroup.idSort.get(d.item[j]).clone());
          it.setOwner(p);
          p.item[j] = it;
        }

        AbstractSkill[] ss = new AbstractSkill[3];
        for (int j = 0; j < 3; j++) {
          SaveHandler.SkillData sd = d.deck[j];
          AbstractSkill s =
                  Objects.requireNonNull(GroupHandler.SkillGroup.idSort.get(d.deck[j].id).clone());
          s.usedOnly = sd.usedOnly;
          s.owner = p;
          for (int k = 0; k < sd.upgradeCount; k++) {
            s.upgrade();
          }
          ss[j] = s;
        }
        p.deck = new Array<>(ss);
        p.stat = d.stat;
        for (int i = 0; i < 3; i++) {
          deck[c][i].setSkill(p.deck.get(i));
        }
        for (int i = 0; i < 2; i++) {
          item[c][i].setItem(p.item[i]);
        }
        passive[c].setItem(p.passive);
        int cnt = 0;
        for (int j = 3; j >= 0; j--) {
          for (int i = 1; i >= 0; i--) {
            stats[c][cnt++].setEntity(p);
          }
        }
        pIcons[c].setPlayer(p);
        c++;
      }
    }
    adv.item = GroupHandler.AdvisorGroup.getAdvisorInstance(AbstractAdvisor.AdvisorClass.valueOf(data.advisor.toUpperCase()));
    text = new ResultText(data.result);
    diff = "난이도: ";
    if(data.diff == AbstractLabyrinth.Difficulty.NORMAL) diff += "일반";
    else if(data.diff == AbstractLabyrinth.Difficulty.HARD) diff += "어려움";
    else diff += "관";
    time = "소요 시간: " + data.minute + "분 " + data.second + "초";
    ver = "버전: " + BuildInfo.BUILD_VERSION;
    seed = "시드: " + data.random.seed;
    score = "점수: " + data.scoreHandle.score;
  }

  @Override
  public void update() {
    if(RunHandler.runs.size > 0) {
      for (int i = 0; i < 4; i++) {
        for (int j = 0; j < 3; j++) {
          deck[i][j].update();
        }
        for (int j = 0; j < 2; j++) {
          item[i][j].update();
        }
        for (int j = 0; j < 8; j++) {
          stats[i][j].update();
        }
        passive[i].update();
        pIcons[i].update();
      }
      adv.update();
      text.update();
      shot.update();
    }
    back.update();
    left.update();
    right.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    if(RunHandler.runs.size > 0) {
      text.render(sb);
      shot.render(sb);
      adv.render(sb);
      sb.end();
      shr.begin(ShapeRenderer.ShapeType.Filled);
      int cnt = 0;
      for (int f = 0; f < 2; f++) {
        for (int g = 0; g < 2; g++) {
          AbstractPlayer player = pIcons[cnt++].p;
          shr.setColor(hbc);
          shr.rect(w * (0.15f + 0.46f * f), h * (0.73f - 0.275f * g), w * 0.12f, h * 0.03f);
          shr.setColor(Color.SCARLET.cpy());
          shr.rect(
                  w * (0.15f + 0.46f * f),
                  h * (0.73f - 0.275f * g),
                  Math.max(w * 0.12f * ((float) player.health / (float) player.maxHealth), 0),
                  h * 0.03f);
        }
      }
      shr.end();
      sb.begin();

      cnt = 0;
      for (int f = 0; f < 2; f++) {
        for (int g = 0; g < 2; g++) {
          AbstractPlayer player = pIcons[cnt++].p;
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
          renderCenter(sb, fontData, diff, w * 0.2f, h * 0.25f, w * 0.2f, h * 0.1f);
          renderCenter(sb, fontData, time, w * 0.4f, h * 0.25f, w * 0.2f, h * 0.1f);
          renderCenter(sb, fontData, ver, w * 0.2f, h * 0.18f, w * 0.2f, h * 0.1f);
          renderCenter(sb, fontData, seed, w * 0.4f, h * 0.18f, w * 0.2f, h * 0.1f);
          renderCenter(sb, fontData, score, w * 0.6f, h * 0.18f, w * 0.2f, h * 0.1f);
        }
      }

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
    } else {
      noRuns.render(sb);
    }
    back.render(sb);
    left.render(sb);
    right.render(sb);
  }

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}
}
