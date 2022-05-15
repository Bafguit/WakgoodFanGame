package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.actions.PlayerTurnStartAction;
import com.fastcat.labyrintale.handlers.ActionHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;
import com.fastcat.labyrintale.uis.control.BattlePanel;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FileHandler.bg;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class BattleScreen extends AbstractScreen {

    public static final Array<AbstractEntity> DEF_LOOK = new Array<>();
    public static final Color hbc = new Color(0.4f, 0, 0, 1);
    public static final Color bc = new Color(0.549f, 0.573f, 0.675f, 1);

    public Sprite shield = FileHandler.ui.get("SHIELD");
    public ShapeRenderer shr = new ShapeRenderer();
    public StatusButton[][] playerStatus = new StatusButton[4][5];
    public StatusButton[][] enemyStatus = new StatusButton[4][5];
    public SkillButton[] enemySkills = new SkillButton[4];
    public PlayerView[] players = new PlayerView[4];
    public EnemyView[] enemies = new EnemyView[4];
    public boolean isEnemyTurn = false;
    public Array<AbstractEntity> looking;
    public float w, h, sw, sh;

    public BattleScreen() {
        cType = ControlPanel.ControlType.BATTLE;
        cPanel.battlePanel = new BattlePanel();
        //SoundHandler.playMusic("BATTLE_1", 0.3f, true, true);
        AbstractLabyrinth.prepare();
        setBg(bg.get("BG_BATTLE"));
        w = Gdx.graphics.getWidth();
        h = Gdx.graphics.getHeight();
        sw = shield.getWidth() * InputHandler.scale;
        sh = shield.getHeight() * InputHandler.scale;
        for(int i = 0; i < 4; i++) {
            PlayerView pv = new PlayerView(AbstractLabyrinth.players[i]);
            pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.55f);
            pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.575f);
            pv.player.newDeck();
            pv.player.ui = pv;
            players[i] = pv;


            EnemyView ev = new EnemyView(AbstractLabyrinth.currentFloor.currentRoom.enemies[i]);
            ev.setPosition(w * 0.575f + w * 0.1f * i - ev.sWidth / 2, h * 0.55f);
            ev.enemy.setAnimXY(w * 0.575f + w * 0.1f * i, h * 0.575f);
            ev.enemy.newDeck();
            ev.enemy.shuffleHand();
            ev.enemy.ui = ev;
            enemies[i] = ev;

            for(int j = 0; j < 5; j++) {
                StatusButton t = new StatusButton(pv.player.status[j]);
                t.setPosition(w * 0.437f - w * 0.1f * i + w * 0.014f * j - pv.sWidth / 2, h * 0.52f);
                playerStatus[i][j] = t;

                StatusButton t2 = new StatusButton(ev.enemy.status[j]);
                t2.setPosition(w * 0.507f + w * 0.1f * i + w * 0.014f * j + ev.sWidth / 2 - t2.sWidth, h * 0.52f);
                enemyStatus[i][j] = t2;
            }

            SkillButton s3 = new SkillButton();
            s3.setScale(0.5f);
            s3.setPosition(w * 0.505f + w * 0.1f * i + ev.sWidth / 2 - s3.sWidth, h * 0.825f);
            s3.canClick = false;
            enemySkills[i] = s3;
            setEnemy(enemies[i].enemy, i);
        }
        for(int i = 0; i < 4; i++) {
            AbstractPlayer p = AbstractLabyrinth.players[i];
            if(p.isAlive()) {
                cPanel.battlePanel.setPlayer(p);
                break;
            }
        }
        ActionHandler.bot(new PlayerTurnStartAction(true));
    }

    @Override
    public void update() {
        if(!cPanel.battlePanel.curPlayer.isAlive()) {
            for(int i = 0; i < 4; i++) {
                AbstractPlayer tp = players[i].player;
                if(tp.isAlive()) {
                    cPanel.battlePanel.setPlayer(tp);
                    break;
                }
            }
        }
        if(!cPanel.infoPanel.show) {
            looking = DEF_LOOK;
        } else if(cPanel.infoPanel.skill != null) {
            looking = AbstractSkill.getTargets(cPanel.infoPanel.skill);
        }

        for(int i = 0; i < 4; i++) {
            PlayerView pv = players[i];
            EnemyView ev = enemies[i];

            SkillButton ss = enemySkills[i];
            ss.skill = ev.enemy.hand[0];
            ss.update();
            ss.setPosition(ev.enemy.animX - w * 0.07f + ev.sWidth / 2 - ss.sWidth, h * 0.825f);

            for(int j = 0; j < 5; j++) {
                if (pv.player.isAlive()) {
                    StatusButton ts = playerStatus[i][j];
                    ts.status = AbstractLabyrinth.players[i].status[j];
                    ts.update();
                    ts.setPosition(pv.player.animX + w * (0.012f + 0.014f * j) - pv.sWidth / 2, h * 0.52f);
                }
                if (ev.enemy.isAlive()) {
                    StatusButton ts = enemyStatus[i][j];
                    ts.status = AbstractLabyrinth.currentFloor.currentRoom.enemies[i].status[j];
                    ts.update();
                    ts.setPosition(ev.enemy.animX + w * (-0.068f + 0.014f * j) + ev.sWidth / 2 - ts.sWidth, h * 0.52f);
                }
            }
        }

        for(int i = 0; i < 4; i++) {
            PlayerView pv = players[i];
            pv.isLooking = looking.contains(pv.player, false);
            pv.update();
            EnemyView ev = enemies[i];
            ev.isLooking = looking.contains(ev.enemy,  false);
            ev.update();
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if(isEnemyTurn) {
            for (int i = 0; i < 4; i++) {
                players[i].render(sb);
            }
            for (int i = 0; i < 4; i++) {
                enemies[i].render(sb);
            }
        } else {
            int ci = cPanel.battlePanel.curPlayer.index;
            for (int i = 0; i < 4; i++) {
                enemies[i].render(sb);
            }
            for (int i = 0; i < 4; i++) {
                if (i != ci) players[i].render(sb);
            }
            players[ci].render(sb);
        }

        sb.end();
        shr.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < 4; i++) {
            PlayerView tp = players[i];
            EnemyView te = enemies[i];
            float tw = tp.sWidth, th = tp.sHeight;
            float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
            float ex = te.enemy.animX - te.sWidth / 2, ey = te.enemy.animY - h * 0.025f;
            if(!tp.player.isDead) {
                boolean isBlock = tp.player.block > 0;
                if(isBlock) {
                    shr.setColor(bc);
                    shr.rect(px + tw * 0.075f, py - th * 0.01f, tw * 0.85f, th * 0.07f);
                }
                shr.setColor(hbc);
                shr.rect(px + tw * 0.1f, py, tw * 0.8f, th * 0.05f);
                shr.setColor(Color.SCARLET);
                shr.rect(px + tw * 0.1f, py, Math.max(tw * 0.8f * ((float) tp.player.health / (float) tp.player.maxHealth), 0), th * 0.05f);
            }
            if(!te.enemy.isDead) {
                boolean isBlock = te.enemy.block > 0;
                if(isBlock) {
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
        for(int i = 0; i < 4; i++) {
            PlayerView tp = players[i];
            EnemyView te = enemies[i];
            float tw = tp.sWidth, th = tp.sHeight;
            float px = tp.player.animX - tp.sWidth / 2, py = tp.player.animY - h * 0.025f;
            float ex = te.enemy.animX - te.sWidth / 2, ey = te.enemy.animY - h * 0.025f;
            if(!tp.player.isDead) {
                renderCenter(sb, HP, tp.player.health + "/" + tp.player.maxHealth, px, py + tp.sHeight * 0.05f / 2, tw, tp.sHeight * 0.05f);
                if(tp.player.block > 0) {
                    sb.draw(shield, px - sw * 0.5f, py + th * 0.01f - sh * 0.5f);
                    renderCenter(sb, BLOCK.font, Integer.toString(tp.player.block), px + tw * 0.03f, py + th * 0.025f);
                }
            }
            if(!te.enemy.isDead) {
                renderCenter(sb, HP, te.enemy.health + "/" + te.enemy.maxHealth, ex, ey + te.sHeight * 0.05f / 2, tw, te.sHeight * 0.05f);
                if(te.enemy.block > 0) {
                    sb.draw(shield, ex - sw * 0.5f, ey + th * 0.01f - sh * 0.5f);
                    renderCenter(sb, BLOCK.font, Integer.toString(te.enemy.block), ex + tw * 0.03f, ey + th * 0.025f);
                }
            }
        }
        for(int i = 0; i < 4; i++) {
            for(int j = 0; j < 5; j++) {
                if (players[i].player.isAlive()) playerStatus[i][j].render(sb);
                if (enemies[i].enemy.isAlive()) {
                    enemyStatus[i][j].render(sb);
                }
            }
            if (enemies[i].enemy.isAlive()) {
                enemySkills[i].render(sb);
            }
        }
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
}
