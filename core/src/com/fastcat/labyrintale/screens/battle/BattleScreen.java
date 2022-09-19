package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Sort;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.*;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.interfaces.GetSelectedTarget;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.control.BattlePanel;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import java.util.LinkedList;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.players;
import static com.fastcat.labyrintale.handlers.FontHandler.HP;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

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
            pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.49f);
            pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.515f);
            pv.player.newDeck();
            pv.player.beforeBattle();
            pv.player.ui = pv;
            players[i] = pv;

            EnemyBattleView ev = new EnemyBattleView(AbstractLabyrinth.currentFloor.currentRoom.enemies[i]);
            ev.setPosition(w * 0.575f + w * 0.1f * i - ev.sWidth / 2, h * 0.49f);
            ev.enemy.setAnimXY(w * 0.575f + w * 0.1f * i, h * 0.515f);
            ev.enemy.index = i;
            ev.enemy.newDeck();
            if (isLoad) {
                ev.enemy.isRandom = false;
                ev.enemy.isDead = true;
                ev.enemy.isDie = true;
            } else {
                ev.enemy.beforeBattle();
            }
            ev.enemy.shuffleHand();
            ev.enemy.ui = ev;
            enemies[i] = ev;

            ShieldIcon ps = new ShieldIcon(pv.player);
            ps.setPosition(pv.x - ps.sWidth * 0.4f, h * 0.49f - ps.sHeight * 0.35f);
            pShield[i] = ps;

            ShieldIcon es = new ShieldIcon(ev.enemy);
            es.setPosition(ev.x - es.sWidth * 0.4f, h * 0.49f - es.sHeight * 0.35f);
            eShield[i] = es;

            playerStatus[i] = new LinkedList<>();
            enemyStatus[i] = new LinkedList<>();

            SkillButton s3 = new SkillButton();
            s3.setScale(0.5f);
            s3.setPosition(w * 0.505f + w * 0.1f * i + ev.sWidth / 2 - s3.sWidth, h * 0.765f);
            s3.canClick = false;
            s3.subDown = true;
            enemySkills[i] = s3;
            setEnemy(enemies[i].enemy, i);
        }
        if (AbstractLabyrinth.advisor.cls == AbstractAdvisor.AdvisorClass.DUKSU) {
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
                AbstractPlayer tp = players[i].player;
                if (tp.isAlive()) {
                    cPanel.battlePanel.setPlayer(tp);
                    break;
                }
            }
        }

        if (cPanel.infoPanel.skill != null) {
            looking = AbstractSkill.getTargets(cPanel.infoPanel.skill);
        } else {
            looking = new Array<>();
        }

        for (int i = 0; i < 4; i++) {
            PlayerBattleView pv = players[i];
            EnemyBattleView ev = enemies[i];

            SkillButton ss = enemySkills[i];
            ss.skill = ev.enemy.hand[0];
            ss.overable = AbstractLabyrinth.bleak < 100;
            ss.update();
            ss.setPosition(ev.enemy.animX - w * 0.07f + ev.sWidth / 2 - ss.sWidth, h * 0.765f);

            if (pv.player.isAlive()) {
                AbstractPlayer pp = pv.player;
                LinkedList<StatusButton> s = playerStatus[i];
                int size = s.size(), pSize = pp.status.size();
                if(pSize > size) {
                    for(int j = size; j < pSize; j++) {
                        StatusButton stb = new StatusButton();
                        s.add(stb);
                    }
                } else if(pSize < size) {
                    for(int j = pSize; j < size; j++) {
                        s.removeLast();
                    }
                }
                for(int j = 0; j < s.size(); j++) {
                    StatusButton ts = s.get(j);
                    ts.status = pp.status.get(j);
                    int line = j / 4;
                    int num = j % 4;
                    ts.setPosition(pp.animX + w * (0.012f + 0.019f * num) - pv.sWidth / 2, h * 0.457f - (w * 0.019f * line));
                    ts.update();
                }
            }
            if (ev.enemy.isAlive()) {
                AbstractEnemy pp = ev.enemy;
                LinkedList<StatusButton> s = enemyStatus[i];
                int size = s.size(), pSize = pp.status.size();
                if(pSize > size) {
                    for(int j = size; j < pSize; j++) {
                        StatusButton stb = new StatusButton();
                        s.add(stb);
                    }
                } else if(pSize < size) {
                    for(int j = pSize; j < size; j++) {
                        s.removeLast();
                    }
                }
                for(int j = 0; j < s.size(); j++) {
                    StatusButton ts = s.get(j);
                    ts.status = pp.status.get(j);
                    int line = j / 4;
                    int num = j % 4;
                    ts.setPosition(pp.animX + w * (-0.068f + 0.019f * num) + ev.sWidth / 2 - ts.sWidth, h * 0.457f - (w * 0.019f * line));
                    ts.update();
                }
            }
        }

        for (int i = 0; i < 4; i++) {
            PlayerBattleView pv = players[i];
            pv.isLooking = looking.contains(pv.player, false);
            pv.update();
            EnemyBattleView ev = enemies[i];
            ev.isLooking = looking.contains(ev.enemy, false);
            ev.update();
            pShield[i].e = pv.player;
            eShield[i].e = ev.enemy;
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
                if (!enemies[i].isTarget) enemies[i].render(sb);
                if (!players[i].isTarget) players[i].render(sb);
            }
            bgImg.render(sb);
            for (int i = 3; i >= 0; i--) {
                if (enemies[i].isTarget) enemies[i].render(sb);
                if (players[i].isTarget) players[i].render(sb);
            }
        } else {
            for (int i = 3; i >= 0; i--) {
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
            float tw = tp.sWidth, th = tp.sHeight;
            float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
            float ex = te.enemy.animX - te.sWidth / 2, ey = te.enemy.animY - h * 0.025f;
            if (!tp.player.isDead) {
                boolean isBlock = tp.player.block > 0;
                if (isBlock) {
                    shr.setColor(bc);
                    shr.rect(px + tw * 0.075f, py - th * 0.01f, tw * 0.85f, th * 0.07f);
                }
                shr.setColor(hbc);
                shr.rect(px + tw * 0.1f, py, tw * 0.8f, th * 0.05f);
                shr.setColor(Color.SCARLET);
                shr.rect(px + tw * 0.1f, py, Math.max(tw * 0.8f * ((float) tp.player.health / (float) tp.player.maxHealth), 0), th * 0.05f);
            }
            if (!te.enemy.isDead) {
                boolean isBlock = te.enemy.block > 0;
                if (isBlock) {
                    shr.setColor(bc);
                    shr.rect(ex + tw * 0.075f, ey - th * 0.01f, tw * 0.85f, th * 0.07f);
                }
                shr.setColor(hbc);
                shr.rect(ex + tw * 0.1f, ey, tw * 0.8f, th * 0.05f);
                shr.setColor(Color.SCARLET);
                shr.rect(ex + tw * 0.1f, ey, Math.max(tw * 0.8f * ((float) te.enemy.health / (float) te.enemy.maxHealth), 0), th * 0.05f);
            }
        }
        shr.end();
        sb.begin();
        for (int i = 0; i < 4; i++) {
            PlayerBattleView tp = players[i];
            EnemyBattleView te = enemies[i];
            float tw = tp.sWidth, th = tp.sHeight;
            float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
            float ex = te.enemy.animX - te.sWidth / 2, ey = te.enemy.animY - h * 0.025f;
            if (!tp.player.isDead) {
                renderCenter(sb, HP, tp.player.health + "/" + tp.player.maxHealth, px, py + tp.sHeight * 0.06f / 2, tw, tp.sHeight * 0.05f);
            }
            if (!te.enemy.isDead) {
                renderCenter(sb, HP, te.enemy.health + "/" + te.enemy.maxHealth, ex, ey + te.sHeight * 0.06f / 2, tw, te.sHeight * 0.05f);
            }

            ShieldIcon ps = pShield[i];
            pShield[i].setPosition(px - ps.sWidth * 0.4f, h * 0.49f - ps.sHeight * 0.35f);
            pShield[i].render(sb);
            eShield[i].setPosition(ex - ps.sWidth * 0.4f, h * 0.49f - ps.sHeight * 0.35f);
            eShield[i].render(sb);
        }
        for (int i = 0; i < 4; i++) {
            if(players[i].player.isAlive()) {
                for (StatusButton b : playerStatus[i]) {
                    b.render(sb);
                }
            }
            if (enemies[i].enemy.isAlive()) {
                for (StatusButton b : enemyStatus[i]) {
                    b.render(sb);
                }
                if (AbstractLabyrinth.bleak < 100) enemySkills[i].render(sb);
            }
        }
    }

    public void resetTurn() {
        Array<AbstractEntity> temp = new Array<>();
        for (AbstractPlayer p : AbstractLabyrinth.players) {
            if(p.isAlive()) temp.add(p);
        }
        for(AbstractEnemy e : AbstractLabyrinth.currentFloor.currentRoom.enemies) {
            if(e.isAlive()) temp.add(e);
        }

        Sort.instance().sort(temp, (o1, o2) -> {
            int i = o2.stat.speed - o1.stat.speed;
            if(i == 0) {
                i = o1.index - o2.index;
                if(i == 0) {
                    if(o1.isPlayer) return -1;
                    else return 1;
                }
            }
            return i;
        });

        setTurn(temp);
    }

    public void setTurn(Array<AbstractEntity> t) {
        turn = t;
        turnIndex = -1;
        nextTurn();
    }

    public Array<AbstractEntity> getTurns() {
        return turn;
    }

    public void nextTurn() {
        turnIndex++;
        if(turnIndex == turn.size) {
            resetTurn();
        } else {
            AbstractEntity t = currentTurnEntity();
            ActionHandler.bot(new TurnStartAction(t));
            if (t.isPlayer) cPanel.battlePanel.setPlayer((AbstractPlayer) t);
            else ActionHandler.bot(new EnemyTurnAction((AbstractEnemy) t));
        }
    }

    public AbstractEntity currentTurnEntity() {
        if(turn != null) return turn.get(turnIndex);
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

    public void setEnemy(AbstractEnemy e, int index) {
        enemies[index].enemy = e;
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }

    public enum BattleType {
        NORMAL, EVENT
    }
}
