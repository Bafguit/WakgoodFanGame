package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.RandomXC;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.handlers.*;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.control.BattlePanel;
import com.fastcat.labyrintale.uis.control.ControlPanel;
import java.util.LinkedList;

public class BattleScreen extends AbstractScreen {

  public static final Color hbc = new Color(0.4f, 0, 0, 1);
  public static final Color bc = new Color(0.549f, 0.573f, 0.675f, 1);

  private final BgImg bgImg = new BgImg();
  public Sprite shield = FileHandler.getUi().get("SHIELD");
  public ShapeRenderer shr = new ShapeRenderer();
  public LinkedList<StatusButton>[] playerStatus = new LinkedList[4];
  public LinkedList<StatusButton>[] enemyStatus = new LinkedList[4];
  public SkillButton[] enemySkills = new SkillButton[4];
  public PlayerBattleView[] players = new PlayerBattleView[4];
  public EnemyBattleView[] enemies = new EnemyBattleView[4];
  public ShieldIcon[] pShield = new ShieldIcon[4];
  public ShieldIcon[] eShield = new ShieldIcon[4];
  public GetSelectedTarget gets;
  public boolean isSelecting = false;
  public boolean isEnemyTurn = false;
  public Array<AbstractEntity> looking;
  private Array<AbstractEntity> turn;
  private int turnIndex;
  public int round;
  public BattleType type;
  public float w, h, sw, sh;

  public BattleScreen() {
    this(BattleType.NORMAL, false);
  }

  public BattleScreen(BattleType type, boolean isLoad) {
    cType = ControlPanel.ControlType.BATTLE;
    cPanel.battlePanel = new BattlePanel();
    this.type = type;
    AbstractLabyrinth.prepare();
    setBg(FileHandler.getBg().get("BG_WAY"));
    w = Gdx.graphics.getWidth();
    h = Gdx.graphics.getHeight();
    sw = shield.getWidth() * InputHandler.scale;
    sh = shield.getHeight() * InputHandler.scale;
    for (int i = 0; i < 4; i++) {
      PlayerBattleView pv = new PlayerBattleView(AbstractLabyrinth.players[i]);
      pv.entity.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.515f);
      pv.setPosition(pv.entity.animX - pv.sWidth / 2, h * 0.49f);
      pv.entity.newDeck();
      pv.entity.beforeBattle();
      pv.entity.ui = pv;
      players[i] = pv;

      EnemyBattleView ev =
          new EnemyBattleView(AbstractLabyrinth.currentFloor.currentRoom.enemies[i]);
      ev.entity.setAnimXY(w * 0.575f + w * 0.1f * i, h * 0.515f);
      ev.setPosition(ev.entity.animX - ev.sWidth / 2, h * 0.49f);
      ev.entity.index = i;
      ev.entity.newDeck();
      if (isLoad) {
        ev.entity.isDead = true;
        ev.entity.isDie = true;
      } else {
        DifficultyHandler.getInstance().onEnemySpawn(ev.entity);
        for(AbstractPlayer p : AbstractLabyrinth.players) {
          if(p.playerClass == AbstractPlayer.PlayerClass.LILPA) {
            ev.entity.stat.debuRes -= 15;
            break;
          }
        }
        ev.entity.beforeBattle();
      }
      if(!isLoad) ev.entity.shuffleHand();
      ev.entity.ui = ev;
      enemies[i] = ev;

      ShieldIcon ps = new ShieldIcon(pv.entity);
      ps.setPosition(pv.x - ps.sWidth * 0.4f, h * 0.49f - ps.sHeight * 0.35f);
      pShield[i] = ps;

      ShieldIcon es = new ShieldIcon(ev.entity);
      es.setPosition(ev.x - es.sWidth * 0.4f, h * 0.49f - es.sHeight * 0.35f);
      eShield[i] = es;

      playerStatus[i] = new LinkedList<>();
      enemyStatus[i] = new LinkedList<>();

      SkillButton s3 = new SkillButton();
      s3.setScale(0.75f);
      s3.setPosition(w * 0.505f + w * 0.1f * i + ev.sWidth / 2 - s3.sWidth, h * 0.765f);
      s3.canClick = false;
      //s3.subDown = AbstractUI.SubText.SubWay.DOWN;
      enemySkills[i] = s3;
      setEnemy(enemies[i].entity, i);
    }
    DifficultyHandler.getInstance().atBattleStart();
    if (AbstractLabyrinth.advisor.id.equals("duksu")) {
      for (AbstractPlayer p : AbstractLabyrinth.players) {
        p.hand[0].upgrade();
      }
    }
    for (int i = 0; i < 4; i++) {
      AbstractPlayer p = AbstractLabyrinth.players[i];
      if (p.isAlive()) {
        cPanel.battlePanel.setPlayer(p);
        break;
      }
    }
    turn = new Array<>();
    round = 0;
    if (isLoad) {
      ActionHandler.top(new VictoryAction(0));
    } else {
      resetTurn();
      ActionHandler.top(new StartBattleAction());
    }
  }

  @Override
  public void update() {
    if (!cPanel.battlePanel.curPlayer.isAlive()) {
      for (int i = 0; i < 4; i++) {
        AbstractEntity tp = players[i].entity;
        if (tp.isAlive()) {
          cPanel.battlePanel.setPlayer(tp);
          break;
        }
      }
    }

    if (cPanel.infoPanel.skill != null) {
      Labyrintale.battleScreen.looking = AbstractSkill.getTargets(cPanel.infoPanel.skill);
    }
    for (int i = 0; i < 4; i++) {
      PlayerBattleView pv = players[i];
      EnemyBattleView ev = enemies[i];

      SkillButton ss = enemySkills[i];
      ss.skill = ev.entity.hand[0];
      ss.overable = true;
      ss.update();
      ss.setPosition(ev.entity.animX - ss.sWidth / 2, h * 0.765f);

      if (pv.entity.isAlive()) {
        AbstractEntity pp = pv.entity;
        LinkedList<StatusButton> s = playerStatus[i];
        int size = s.size(), pSize = pp.status.size();
        if (pSize > size) {
          for (int j = size; j < pSize; j++) {
            StatusButton stb = new StatusButton();
            s.add(stb);
          }
        } else if (pSize < size) {
          for (int j = pSize; j < size; j++) {
            s.removeLast();
          }
        }
        for (int j = 0; j < s.size(); j++) {
          StatusButton ts = s.get(j);
          ts.status = pp.status.get(j);
          int line = j / pv.statSize;
          int num = j % pv.statSize;
          ts.setPosition(
              pv.x + w * 0.019f * num + pv.sWidth * 0.11f,
              h * 0.457f - (w * 0.019f * line));
          ts.update();
        }
      }
      if (ev.entity.isAlive()) {
        AbstractEntity pp = ev.entity;
        LinkedList<StatusButton> s = enemyStatus[i];
        int size = s.size(), pSize = pp.status.size();
        if (pSize > size) {
          for (int j = size; j < pSize; j++) {
            StatusButton stb = new StatusButton();
            s.add(stb);
          }
        } else if (pSize < size) {
          for (int j = pSize; j < size; j++) {
            s.removeLast();
          }
        }
        for (int j = 0; j < s.size(); j++) {
          StatusButton ts = s.get(j);
          ts.status = pp.status.get(j);
          int line = j / ev.statSize;
          int num = j % ev.statSize;
          ts.setPosition(
              ev.x + w * 0.019f * num + ev.sWidth * 0.11f,
              h * 0.457f - (w * 0.019f * line));
          ts.update();
        }
      }
    }

    for (int i = 0; i < 4; i++) {
      PlayerBattleView pv = players[i];
      pv.isLooking = looking.contains(pv.entity, false);
      pv.update();
      EnemyBattleView ev = enemies[i];
      ev.isLooking = looking.contains(ev.entity, false);
      ev.update();
      pShield[i].e = pv.entity;
      eShield[i].e = ev.entity;
    }

    for (int i = 0; i < 4; i++) {
      AbstractLabyrinth.players[i].update();
      AbstractEnemy e = AbstractLabyrinth.currentFloor.currentRoom.enemies[i];
      if (e != null) e.update();
    }
  }

  @Override
  public void render(SpriteBatch sb) {
    int ci = cPanel.battlePanel.curPlayer.index;
    if (isSelecting) {
      for (int i = 3; i >= 0; i--) {
        if (!enemies[3 - i].isTarget) enemies[3 - i].render(sb);
        if (!players[i].isTarget) players[i].render(sb);
      }
      bgImg.render(sb);
      for (int i = 3; i >= 0; i--) {
        if (enemies[3 - i].isTarget) enemies[3 - i].render(sb);
        if (players[i].isTarget) players[i].render(sb);
      }
    } else {
      for (int i = 0; i < 4; i++) {
        enemies[i].render(sb);
      }
      for (int i = 3; i >= 0; i--) {
        if (i != ci) players[i].render(sb);
      }
      players[ci].render(sb);
    }

    sb.end();
    shr.begin(ShapeRenderer.ShapeType.Filled);
    for (int i = 0; i < 4; i++) {
      PlayerBattleView tp = players[i];
      EnemyBattleView te = enemies[i];
      float tw = tp.sWidth, th = tp.sHeight, ew = te.sWidth;
      float px = tp.entity.animX - tp.sWidth / 2, py = tp.entity.animY - h * 0.025f;
      float ex = te.entity.animX - te.sWidth / 2, ey = te.entity.animY - h * 0.025f;
      if (!tp.entity.isDead) {
        boolean isBlock = tp.entity.block > 0;
        if (isBlock) {
          shr.setColor(bc);
          shr.rect(px + tw * 0.075f, py - th * 0.01f, tw * 0.85f, th * 0.07f);
        }
        shr.setColor(hbc);
        shr.rect(px + tw * 0.1f, py, tw * 0.8f, th * 0.05f);
        shr.setColor(Color.SCARLET);
        shr.rect(
            px + tw * 0.1f,
            py,
            Math.max(tw * 0.8f * ((float) tp.entity.health / (float) tp.entity.maxHealth), 0),
            th * 0.05f);
      }
      if (!te.entity.isDead) {
        boolean isBlock = te.entity.block > 0;
        float thh = th * 0.01f;
        if (isBlock) {
          shr.setColor(bc);
          shr.rect(ex + ew * 0.1f - thh, ey - thh, ew * 0.8f + thh * 2, th * 0.07f);
        }
        shr.setColor(hbc);
        shr.rect(ex + ew * 0.1f, ey, ew * 0.8f, th * 0.05f);
        shr.setColor(Color.SCARLET);
        shr.rect(
            ex + ew * 0.1f,
            ey,
            Math.max(ew * 0.8f * ((float) te.entity.health / (float) te.entity.maxHealth), 0),
            th * 0.05f);
      }
    }
    shr.end();
    sb.begin();
    for (int i = 0; i < 4; i++) {
      PlayerBattleView tp = players[i];
      EnemyBattleView te = enemies[i];
      float tw = tp.sWidth, ew = te.sWidth;
      float px = tp.entity.animX - tw / 2, py = tp.entity.animY - h * 0.025f;
      float ex = te.entity.animX - ew / 2, ey = te.entity.animY - h * 0.025f;
      if (!tp.entity.isDead) {
        renderCenter(
            sb,
            HP,
            tp.entity.health + "/" + tp.entity.maxHealth,
            px,
            py + tp.sHeight * 0.06f / 2,
            tw,
            tp.sHeight * 0.05f);
      }
      if (!te.entity.isDead) {
        renderCenter(
            sb,
            HP,
            te.entity.health + "/" + te.entity.maxHealth,
            ex,
            ey + te.sHeight * 0.06f / 2,
            ew,
            te.sHeight * 0.05f);
      }

      ShieldIcon ps = pShield[i];
      pShield[i].setPosition(px + tw * 0.075f - ps.sWidth * 0.65f, h * 0.49f - ps.sHeight * 0.35f);
      pShield[i].render(sb);
      ShieldIcon es = eShield[i];
      eShield[i].setPosition(ex + ew * 0.075f - es.sWidth * 0.65f, h * 0.49f - es.sHeight * 0.35f);
      eShield[i].render(sb);
    }
    for (int i = 0; i < 4; i++) {
      if (players[i].entity.isAlive()) {
        for (StatusButton b : playerStatus[i]) {
          b.render(sb);
        }
      }
      if (enemies[i].entity.isAlive()) {
        for (StatusButton b : enemyStatus[i]) {
          b.render(sb);
        }
        enemySkills[i].render(sb);
      }
    }
  }

  public static class TurnSpeedData {
    public final int originalSpeed;
    public final int randomSpeed;
    public final AbstractEntity entity;

    public TurnSpeedData(int originalSpeed, int randomSpeed, AbstractEntity entity) {
      this.originalSpeed = originalSpeed;
      this.randomSpeed = randomSpeed;
      this.entity = entity;
    }
  }

  public void resetTurn() {
    //Sort priority: origSpeed+rand(0-7) -> origSpeed -> random
    Array<TurnSpeedData> speedData = new Array<>();
    for (PlayerBattleView p : players) {
      if (p.entity.isAlive())
        speedData.add(new TurnSpeedData(p.entity.stat.capSpeed(), AbstractLabyrinth.publicRandom.random(0, 7), p.entity));
    }
    for (EnemyBattleView e : enemies) {
      if (e.entity.isAlive())
        speedData.add(new TurnSpeedData(e.entity.stat.capSpeed(), AbstractLabyrinth.publicRandom.random(0, 7), e.entity));
    }

    TurnSpeedData[] items = new TurnSpeedData[speedData.size];
    for(int i = 0; i < speedData.size; i++) {
      items[i] = speedData.get(i);
    }
    for (int i = speedData.size - 1; i >= 0; --i) {
      int ii = AbstractLabyrinth.publicRandom.random(i);
      TurnSpeedData temp = items[i];
      items[i] = items[ii];
      items[ii] = temp;
    }
    speedData.clear();
    speedData.addAll(items);

    Sort.instance()
        .sort(speedData, (data1, data2) -> {
          int originalSpeedDiff = data2.originalSpeed - data1.originalSpeed;
          int randomAppliedSpeedDiff = originalSpeedDiff + data2.randomSpeed - data1.randomSpeed;
          return randomAppliedSpeedDiff == 0 ? originalSpeedDiff : randomAppliedSpeedDiff;
        });

    Array<AbstractEntity> sortedEntities = new Array<>();
    for (TurnSpeedData aSpeedData : speedData) {
      sortedEntities.add(aSpeedData.entity);
    }

    for (AbstractEntity e : sortedEntities) {
      if (e.isPlayer) {
        cPanel.battlePanel.setPlayer(e);
        break;
      }
    }
    turn = sortedEntities;
    turnIndex = -1;
    ActionHandler.bot(new RoundStartAction(++round));
    nextTurn();
    cPanel.battlePanel.turnView.setNewTurns(turn);
  }

  public Array<AbstractEntity> getTurns() {
    return turn;
  }

  public void nextTurn() {
    if (turnIndex == turn.size - 1) {
      ActionHandler.bot(new RoundEndAction());
    } else {
      turnIndex++;
      AbstractEntity t = currentTurnEntity();
      if (t.isAlive()) ActionHandler.bot(new TurnStartAction(t));
      else nextTurn();
    }
  }

  public int getCurrentTurn() {
    return turnIndex;
  }

  public AbstractEntity currentTurnEntity() {
    if (turn != null && turn.size > 0) return turn.get(turnIndex);
    else return null;
  }

  public void endPlayerTurn() {
    ActionHandler.bot(new EndPlayerTurnAction());
    isEnemyTurn = true;
  }

  public void selectTarget(GetSelectedTarget gets) {
    this.gets = gets;
    isSelecting = true;
  }

  public void setEnemy(AbstractEntity e, int index) {
    enemies[index].entity = e;
  }

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {}

  public enum BattleType {
    NORMAL,
    EVENT
  }
}
