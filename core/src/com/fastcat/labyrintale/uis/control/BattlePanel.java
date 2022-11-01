package com.fastcat.labyrintale.uis.control;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FontHandler.COOLDOWN;
import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.InputHandler.cancel;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.*;

public class BattlePanel implements Disposable {

  public static final Color hbc = new Color(0.4f, 0, 0, 1);
  private static final FontHandler.FontData fontHp = HP;
  public static ShapeRenderer shr = new ShapeRenderer();
  public static EnergyPanel energy = new EnergyPanel();
  private final int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
  public AbstractSkill selected;
  public SkillButtonPanel[] skill = new SkillButtonPanel[3];
  public SkillButtonPanel mSkill;
  public SkillButtonPanel pSkill;
  public StatIcon[] stats = new StatIcon[8];
  public ItemPanel passive;
  public ItemPanel[] item = new ItemPanel[2];
  public AbstractEntity curPlayer;
  public PlayerBigIcon cpIcon;
  public TurnView turnView;
  public float rx, ry, ex, ey;

  public BattlePanel() {
    float bottom = 61 * scale, mid = 187 * scale, panel = 82 * scale;
    passive = new ItemPanel();
    passive.setScale(0.6f);
    passive.setPosition(310 * scale, bottom);
    item[0] = new ItemPanel();
    item[0].setScale(0.6f);
    item[0].setPosition(425 * scale, bottom);
    item[1] = new ItemPanel();
    item[1].setScale(0.6f);
    item[1].setPosition(540 * scale, bottom);
    mSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.BASIC);
    mSkill.setScale(0.6f);
    mSkill.setPosition(673 * scale, bottom);
    pSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.BASIC);
    pSkill.setScale(0.6f);
    pSkill.setPosition(788 * scale, bottom);
    for (int i = 0; i < 3; i++) {
      SkillButtonPanel s = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
      s.setPosition((718 - 204 * i) * scale, mid);
      skill[i] = s;
    }
    cpIcon = new PlayerBigIcon(AbstractLabyrinth.players[0]);
    cpIcon.setPosition(panel, 473 * scale - cpIcon.sHeight);
    energy.setPosition(770 * scale, 490 * scale - energy.sHeight);
    rx = panel;
    ex = cpIcon.sWidth;
    ey = cpIcon.y - bottom;
    ry = bottom;
    int cnt = 0;
    for (int j = 1; j >= 0; j--) {
      for (int i = 0; i < 4; i++) {
        StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
        s.setPosition(310 * scale + w * 0.044f * i, 395 * scale + h * 0.027f * j);
        stats[cnt++] = s;
      }
    }

    turnView = new TurnView();
  }

  public void update() {
    Labyrintale.battleScreen.looking = new Array<>();
    for (int i = 0; i < 3; i++) {
      skill[i].update();
    }
    mSkill.update();
    pSkill.update();
    passive.update();
    for (int i = 0; i < 2; i++) {
      item[i].update();
    }
    setPlayer(Labyrintale.battleScreen.currentTurnEntity());
    turnView.update();
    cpIcon.setPlayer(curPlayer);
    cpIcon.update();
    energy.update();
    for (int i = 0; i < 8; i++) {
      stats[i].entity = curPlayer;
      stats[i].update();
    }
  }

  public void render(SpriteBatch sb) {
    cpIcon.render(sb);
    sb.end();
    shr.begin(ShapeRenderer.ShapeType.Filled);
    shr.setColor(hbc);
    shr.rect(rx, ry, ex, ey);
    shr.setColor(Color.SCARLET.cpy());
    shr.rect(
        rx, ry, Math.max(ex * ((float) curPlayer.health / (float) curPlayer.maxHealth), 0), ey);
    shr.end();
    sb.begin();
    FontHandler.renderCenter(
        sb,
        fontHp,
        curPlayer.health + "/" + curPlayer.maxHealth,
        rx,
        ry + ey / 2,
        ex,
        ey);

    passive.render(sb);
    for (int i = 0; i < 2; i++) {
      item[i].render(sb);
    }
    mSkill.render(sb);
    pSkill.render(sb);
    for (int i = 0; i < 3; i++) {
      skill[i].render(sb);
    }
    energy.render(sb);
    for (int i = 0; i < 8; i++) {
      stats[i].render(sb);
    }

    if (!Labyrintale.playerInfoScreen.showing) {
      turnView.render(sb);
    }
  }

  public void setPlayer(AbstractEntity p) {
    if (p != null && p.isAlive()) {
      curPlayer = p;
      if(p.isPlayer) {
        for (int j = 0; j < 3; j++) {
          skill[j].skill = p.hand[2 - j];
        }
        mSkill.skill = p.moveTemp;
        pSkill.skill = p.pass;
        passive.item = p.passive;
        item[0].item = p.item[0];
        item[1].item = p.item[1];
      } else {
        for (int j = 0; j < 3; j++) {
          skill[j].skill = null;
        }
        mSkill.skill = null;
        pSkill.skill = null;
        passive.item = null;
        item[0].item = null;
        item[1].item = null;
      }
    }
  }

  @Override
  public void dispose() {
    for (int i = 0; i < 3; i++) {
      skill[i].dispose();
    }
    mSkill.dispose();
    pSkill.dispose();
    passive.dispose();
    for (int i = 0; i < 2; i++) {
      item[i].dispose();
    }
    cpIcon.dispose();
    energy.dispose();
  }
}
