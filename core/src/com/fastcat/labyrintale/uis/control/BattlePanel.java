package com.fastcat.labyrintale.uis.control;

import static com.fastcat.labyrintale.handlers.FontHandler.COOLDOWN;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.uis.*;

public class BattlePanel implements Disposable {

  public static final Color hbc = new Color(0.4f, 0, 0, 1);
  private static final FontHandler.FontData fontHp = COOLDOWN;
  public static ShapeRenderer shr = new ShapeRenderer();
  public static EnergyPanel energy = new EnergyPanel();
  private final int w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
  public AbstractSkill selected;
  public SkillButtonPanel[] skill = new SkillButtonPanel[3];
  public SkillButtonPanel mSkill;
  public SkillButtonPanel pSkill;
  public SkillButtonPanel aSkill;
  public StatIcon[] stats = new StatIcon[8];
  public ItemPanel[] item = new ItemPanel[2];
  public AbstractPlayer curPlayer;
  public PlayerBigIcon cpIcon;
  public TurnView turnView;
  public float rx, ry, ex, ey;

  public BattlePanel() {
    item[0] = new ItemPanel();
    item[0].setPosition(w * 0.26f - item[0].sWidth / 2, h * 0.225f);
    item[1] = new ItemPanel();
    item[1].setPosition(w * 0.32f - item[1].sWidth / 2, h * 0.225f);
    aSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.ADVISOR);
    aSkill.setPosition(w * 0.58f - aSkill.sWidth, h * 0.075f);
    mSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.BASIC);
    mSkill.setPosition(w * 0.9f - mSkill.sWidth, h * 0.225f);
    pSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.BASIC);
    pSkill.setPosition(w * 0.84f - pSkill.sWidth, h * 0.225f);
    for (int i = 0; i < 3; i++) {
      SkillButtonPanel s = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
      s.setPosition(w * 0.9f - w * 0.08f * i - s.sWidth, h * 0.075f);
      skill[i] = s;
    }
    cpIcon = new PlayerBigIcon(AbstractLabyrinth.players[0]);
    cpIcon.setPosition(w * 0.13f - cpIcon.sWidth / 2, h * 0.22f - cpIcon.sHeight / 2);
    energy.setPosition(w * 0.23f - energy.sWidth / 2, h * 0.32f);
    rx = 440 * scale;
    ex = w * 0.3f;
    ey = aSkill.sHeight * 0.5f;
    ry = h * 0.075f + ey;
    int cnt = 0;
    for (int j = 1; j >= 0; j--) {
      for (int i = 0; i < 4; i++) {
        StatIcon s = new StatIcon(StatIcon.StatType.values()[cnt]);
        s.setPosition(550 * scale + w * 0.05f * i, h * 0.075f + h * 0.027f * j);
        stats[cnt++] = s;
      }
    }

    turnView = new TurnView();
  }

  public void update() {
    for (int i = 0; i < 3; i++) {
      skill[i].update();
    }
    mSkill.update();
    pSkill.update();
    for (int i = 0; i < 2; i++) {
      item[i].update();
    }
    aSkill.update();
    cpIcon.setPlayer(curPlayer);
    cpIcon.update();
    energy.update();
    for (int i = 0; i < 8; i++) {
      stats[i].entity = curPlayer;
      stats[i].update();
    }

    turnView.update();
  }

  public void render(SpriteBatch sb) {
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
        h * 0.135f + ey * 0.5f,
        ex,
        ey);

    for (int i = 0; i < 2; i++) {
      item[i].render(sb);
    }
    mSkill.render(sb);
    pSkill.render(sb);
    for (int i = 0; i < 3; i++) {
      skill[i].render(sb);
    }
    aSkill.render(sb);
    cpIcon.render(sb);
    energy.render(sb);
    for (int i = 0; i < 8; i++) {
      stats[i].render(sb);
    }

    if (!Labyrintale.playerInfoScreen.showing) {
      turnView.render(sb);
    }
  }

  public void setPlayer(AbstractPlayer p) {
    if (p.isAlive()) {
      curPlayer = p;
      for (int j = 0; j < 3; j++) {
        skill[j].skill = p.hand[2 - j];
      }
      aSkill.skill = AbstractLabyrinth.advisor.skill;
      mSkill.skill = p.moveTemp;
      pSkill.skill = p.pass;
      item[0].item = p.item[0];
      item[1].item = p.item[1];
    }
  }

  @Override
  public void dispose() {
    for (int i = 0; i < 3; i++) {
      skill[i].dispose();
    }
    mSkill.dispose();
    pSkill.dispose();
    for (int i = 0; i < 2; i++) {
      item[i].dispose();
    }
    aSkill.dispose();
    cpIcon.dispose();
    energy.dispose();
  }
}
