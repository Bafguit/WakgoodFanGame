package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
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

    public AbstractUI.TempUI hb = new AbstractUI.TempUI(FileHandler.getUi().get("HEALTH_BAR"));
    public Sprite hbb = FileHandler.getUi().get("HEALTH_BACK");
    public AbstractUI.TempUI ground = new AbstractUI.TempUI(FileHandler.getUi().get("GROUND"));
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
    public int neutResCount = 0;
    public Array<AbstractEntity> looking;
    private Array<AbstractEntity> turn;
    private int turnIndex;
    public int round;
    public BattleType type;
    public float w, h;

    public BattleScreen() {
        this(BattleType.NORMAL, false);
    }

    public BattleScreen(BattleType type, boolean isLoad) {
        cType = ControlPanel.ControlType.BATTLE;
        cPanel.battlePanel = new BattlePanel();
        this.type = type;
        AbstractLabyrinth.prepare();
        setBg(AbstractLabyrinth.curBg);
        ground.setPosition(0, 0);
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        for (int i = 0; i < 4; i++) {
            PlayerBattleView pv = new PlayerBattleView(AbstractLabyrinth.players[i]);
            pv.entity.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.515f - 40 * scale);
            pv.setPosition(pv.entity.animX - pv.sWidth / 2, h * 0.49f - 40 * scale);
            pv.entity.newDeck();
            pv.entity.beforeBattle();
            pv.entity.ui = pv;
            players[i] = pv;

            EnemyBattleView ev = new EnemyBattleView(AbstractLabyrinth.currentFloor.currentRoom.enemies[i]);
            ev.entity.setAnimXY(w * 0.575f + w * 0.1f * i, h * 0.515f - 40 * scale);
            ev.setPosition(ev.entity.animX - ev.sWidth / 2, h * 0.49f - 40 * scale);
            ev.entity.index = i;
            ev.entity.newDeck();
            if (isLoad) {
                ev.entity.isDead = true;
                ev.entity.isDie = true;
            } else {
                DifficultyHandler.getInstance().onEnemySpawn(ev.entity);
                for (AbstractPlayer p : AbstractLabyrinth.players) {
                    if (p.playerClass == AbstractPlayer.PlayerClass.LILPA) {
                        ev.entity.stat.debuRes -= 15;
                        break;
                    }
                    if (p.hasItem("IceFrag")) {
                        ev.entity.stat.speed -= 3;
                    }
                }
                ev.entity.beforeBattle();
            }
            if (!isLoad) ev.entity.shuffleHand();
            ev.entity.ui = ev;
            enemies[i] = ev;

            ShieldIcon ps = new ShieldIcon(pv.entity);
            ps.setPosition(pv.x - ps.sWidth * 0.4f, h * 0.49f - ps.sHeight * 0.35f - 40 * scale);
            pShield[i] = ps;

            ShieldIcon es = new ShieldIcon(ev.entity);
            es.setPosition(ev.x - es.sWidth * 0.4f, h * 0.49f - es.sHeight * 0.35f - 40 * scale);
            eShield[i] = es;

            playerStatus[i] = new LinkedList<>();
            enemyStatus[i] = new LinkedList<>();

            SkillButton s3 = new SkillButton();
            s3.setScale(0.75f);
            s3.setPosition(w * 0.505f + w * 0.1f * i + ev.sWidth / 2 - s3.sWidth, h * 0.765f - 40 * scale);
            s3.canClick = false;
            // s3.subDown = AbstractUI.SubText.SubWay.DOWN;
            enemySkills[i] = s3;
            setEnemy(enemies[i].entity, i);
        }
        DifficultyHandler.getInstance().atBattleStart();
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
            looking = AbstractSkill.getTargets(cPanel.infoPanel.skill);
        }
        for (int i = 0; i < 4; i++) {
            PlayerBattleView pv = players[i];
            EnemyBattleView ev = enemies[i];

            SkillButton ss = enemySkills[i];
            ss.skill = ev.entity.hand[0];
            ss.overable = true;
            ss.update();
            ss.setPosition(ev.entity.animX - ss.sWidth / 2, h * 0.765f - 40 * scale);

            if (pv.entity.isAlive()) {
                AbstractEntity pp = pv.entity;
                LinkedList<StatusButton> s = playerStatus[i];
                int size = s.size(), pSize = pp.status.size(), ww = -20 * pv.statSize + 20;
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
                            pv.entity.animX + (ww + 40 * num) * scale - ts.sWidth / 2,
                            h * 0.46f - (40 * line * scale) - 40 * scale);
                    ts.update();
                }
            }
            if (ev.entity.isAlive()) {
                AbstractEntity pp = ev.entity;
                LinkedList<StatusButton> s = enemyStatus[i];
                int size = s.size(), pSize = pp.status.size(), ww = -20 * ev.statSize + 20;
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
                            ev.entity.animX + (ww + 40 * num) * scale - ts.sWidth / 2,
                            h * 0.46f - (40 * line * scale) - 40 * scale);
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
            ShieldIcon sp = pShield[i];
            ShieldIcon se = eShield[i];
            sp.e = pv.entity;
            se.e = ev.entity;
            sp.update();
            se.update();
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
                if (!players[i].isTarget) players[i].render(sb);
                if (!enemies[3 - i].isTarget) enemies[3 - i].render(sb);
            }
            bgImg.render(sb);
            for (int i = 3; i >= 0; i--) {
                if (players[i].isTarget) players[i].render(sb);
                if (enemies[3 - i].isTarget) enemies[3 - i].render(sb);
            }
        } else {
            for (int i = 3; i >= 0; i--) {
                if (i != ci) players[i].render(sb);
            }
            players[ci].render(sb);
            for (int i = 0; i < 4; i++) {
                enemies[i].render(sb);
            }
        }

        sb.draw(ground.img, 0, 0, Gdx.graphics.getWidth(), Gdx.graphics.getHeight());

        for (int i = 0; i < 4; i++) {
            PlayerBattleView tp = players[i];
            EnemyBattleView te = enemies[i];
            float px = tp.entity.animX - 80 * scale, py = tp.entity.animY - h * 0.025f;
            float ex = te.entity.animX, ey = te.entity.animY - h * 0.025f, ew;
            if (AbstractLabyrinth.currentFloor.currentRoom.type == AbstractRoom.RoomType.BOSS) {
                ew = 359 * scale;
            } else {
                ew = 160 * scale;
            }
            ex -= ew / 2;
            if (!tp.entity.isDead) {
                sb.draw(hbb, px, py, 160 * scale, 22 * scale);
                sb.draw(
                        hb.img,
                        px,
                        py,
                        0,
                        0,
                        160 * scale,
                        22 * scale,
                        Math.max(((float) tp.entity.health) / ((float) tp.entity.maxHealth), 0),
                        1,
                        0);
                renderCenter(
                        sb,
                        tp.entity.isNeut || !tp.entity.isAlive() ? HP_N : FontHandler.HP,
                        tp.entity.health + "/" + tp.entity.maxHealth,
                        px,
                        py + hb.sHeight / 2,
                        160 * scale,
                        hb.sHeight);
            }
            if (!te.entity.isDead) {
                sb.draw(hbb, ex, ey, ew, 22 * scale);
                sb.draw(
                        hb.img,
                        ex,
                        ey,
                        0,
                        0,
                        ew,
                        22 * scale,
                        Math.max(((float) te.entity.health) / ((float) te.entity.maxHealth), 0),
                        1,
                        0);
                renderCenter(
                        sb,
                        te.entity.isNeut || !te.entity.isAlive() ? HP_N : FontHandler.HP,
                        te.entity.health + "/" + te.entity.maxHealth,
                        ex,
                        ey + hb.sHeight / 2,
                        ew,
                        hb.sHeight);
            }
        }

        for (int i = 0; i < 4; i++) {
            PlayerBattleView tp = players[i];
            EnemyBattleView te = enemies[i];

            if (!tp.entity.isDead) {
                tp.renderLook(sb);
            }

            if (!te.entity.isDead) {
                te.renderLook(sb);
            }

            ShieldIcon ps = pShield[i];
            if (ps.e.isAlive() && ps.e.block > 0) {
                ps.setPosition(ps.e.animX - ps.sWidth / 2, 737 * scale - 40 * scale);
                ps.render(sb);
            }

            ShieldIcon es = eShield[i];
            if (es.e.isAlive() && es.e.block > 0) {
                es.setPosition(es.e.animX - es.sWidth / 2, 737 * scale - 40 * scale);
                es.render(sb);
            }
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
        // Sort priority: origSpeed+rand(0-7) -> origSpeed -> random
        Array<TurnSpeedData> speedData = new Array<>();
        for (PlayerBattleView p : players) {
            if (p.entity.isAlive())
                speedData.add(new TurnSpeedData(
                        p.entity.stat.capSpeed(), AbstractLabyrinth.publicRandom.random(0, 7), p.entity));
        }
        for (EnemyBattleView e : enemies) {
            if (e.entity.isAlive())
                speedData.add(new TurnSpeedData(
                        e.entity.stat.capSpeed(), AbstractLabyrinth.publicRandom.random(0, 7), e.entity));
        }

        TurnSpeedData[] items = new TurnSpeedData[speedData.size];
        for (int i = 0; i < speedData.size; i++) {
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

        Sort.instance().sort(speedData, (data1, data2) -> {
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
