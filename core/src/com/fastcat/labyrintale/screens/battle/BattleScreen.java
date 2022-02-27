package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.status.TestStatus;
import com.fastcat.labyrintale.uis.control.BattlePanel;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.cPanel;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class BattleScreen extends AbstractScreen {

    public static final Array<AbstractEntity> DEF_LOOK = new Array<>();
    public static final Color hbc = new Color(0.4f, 0, 0, 1);
    public static final Color bc = new Color(0.549f, 0.573f, 0.675f, 1);

    public ShapeRenderer shr = new ShapeRenderer();
    public DrawPileButton drawPileButton;
    public DiscardPileButton discardPileButton;
    public EndTurnButton endTurnButton;
    public StatusButton[][] playerStatus = new StatusButton[4][5];
    public StatusButton[][] enemyStatus = new StatusButton[4][5];
    public SkillButton[] enemySkills = new SkillButton[4];
    public PlayerView[] players = new PlayerView[4];
    public EnemyView[] enemies = new EnemyView[4];
    public boolean isEnemyTurn = false;
    public Array<AbstractEntity> looking;

    public BattleScreen() {
        cType = ControlPanel.ControlType.BATTLE;
        cPanel.battlePanel = new BattlePanel();
        setBg(FileHandler.BG_BATTLE);
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        drawPileButton = new DrawPileButton();
        discardPileButton = new DiscardPileButton();
        endTurnButton = new EndTurnButton();
        for(int i = 0; i < 4; i++) {
            PlayerView pv = new PlayerView(AbstractLabyrinth.players[i]);
            pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.5f);
            pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.525f);
            pv.player.newDeck();
            pv.player.shuffleHand();
            pv.player.status[0] = new TestStatus(pv.player);
            pv.player.ui = pv;
            players[i] = pv;


            EnemyView ev = new EnemyView(AbstractLabyrinth.currentFloor.currentRoom.enemies[i]);
            ev.setPosition(w * 0.575f + w * 0.1f * i - ev.sWidth / 2, h * 0.5f);
            ev.enemy.setAnimXY(w * 0.575f + w * 0.1f * i, h * 0.525f);
            ev.enemy.newDeck();
            ev.enemy.shuffleHand();
            ev.enemy.ui = ev;
            enemies[i] = ev;

            for(int j = 0; j < 5; j++) {
                StatusButton t = new StatusButton(pv.player.status[j]);
                t.setPosition(w * 0.437f - w * 0.1f * i + w * 0.014f * j - pv.sWidth / 2, h * 0.47f);
                playerStatus[i][j] = t;

                StatusButton t2 = new StatusButton(ev.enemy.status[j]);
                t2.setPosition(w * 0.507f + w * 0.1f * i + w * 0.014f * j + ev.sWidth / 2 - t2.sWidth, h * 0.47f);
                enemyStatus[i][j] = t2;
            }

            SkillButton s3 = new SkillButton();
            s3.setScale(0.5f);
            s3.setPosition(w * 0.505f + w * 0.1f * i + ev.sWidth / 2 - s3.sWidth, h * 0.775f);
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

            if (ev.enemy.isAlive()) {
                enemySkills[i].skill = ev.enemy.hand[0];
                enemySkills[i].update();
            }

            for(int j = 0; j < 5; j++) {
                if (pv.player.isAlive()) {
                    StatusButton ts = playerStatus[i][j];
                    ts.status = AbstractLabyrinth.players[i].status[j];
                    ts.update();
                }
                if (ev.enemy.isAlive()) {
                    StatusButton ts = enemyStatus[i][j];
                    ts.status = AbstractLabyrinth.currentFloor.currentRoom.enemies[i].status[j];
                    ts.update();
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
        drawPileButton.update();
        discardPileButton.update();
        endTurnButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for(int i = 3; i >= 0; i--) {
            if(isEnemyTurn) players[i].render(sb);
            else enemies[i].render(sb);
        }
        for(int i = 3; i >= 0; i--) {
            if(!isEnemyTurn) players[i].render(sb);
            else enemies[i].render(sb);
        }

        sb.end();
        shr.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < 4; i++) {
            PlayerView tp = players[i];
            EnemyView te = enemies[i];
            float tw = tp.sWidth;
            float th = tp.sHeight;
            if(!tp.player.isDead) {
                boolean isBlock = tp.player.block > 0;
                if(isBlock) {
                    shr.setColor(bc);
                    shr.rect(tp.x + tw * 0.075f, tp.y - th * 0.01f, tw * 0.85f, th * 0.07f);
                }
                shr.setColor(hbc);
                shr.rect(tp.x + tw * 0.1f, tp.y, tw * 0.8f, th * 0.05f);
                shr.setColor(Color.SCARLET.cpy());
                shr.rect(tp.x + tw * 0.1f, tp.y, Math.max(tw * 0.8f * ((float) tp.player.health / (float) tp.player.maxHealth), 0), th * 0.05f);
                if(isBlock) {
                    shr.setColor(bc);
                    shr.circle(tp.x + tw * 0.05f, tp.y + th * 0.025f, th * 0.06f);
                    shr.end();
                    sb.begin();
                    renderCenter(sb, BLOCK.font, Integer.toString(tp.player.block), tp.x + tw * 0.05f, tp.y + th * 0.025f);
                    sb.end();
                    shr.begin(ShapeRenderer.ShapeType.Filled);
                }
            }
            if(!te.enemy.isDead) {
                boolean isBlock = te.enemy.block > 0;
                if(isBlock) {
                    shr.setColor(bc);
                    shr.rect(te.x + tw * 0.075f, te.y - th * 0.01f, tw * 0.85f, th * 0.07f);
                }
                shr.setColor(hbc);
                shr.rect(te.x + tw * 0.1f, te.y, tw * 0.8f, th * 0.05f);
                shr.setColor(Color.SCARLET.cpy());
                shr.rect(te.x + tw * 0.1f, te.y, Math.max(tw * 0.8f * ((float) te.enemy.health / (float) te.enemy.maxHealth), 0), th * 0.05f);

                if(isBlock) {
                    shr.setColor(bc);
                    shr.circle(te.x + tw * 0.05f, te.y + th * 0.025f, th * 0.06f);
                    shr.end();
                    sb.begin();
                    renderCenter(sb, BLOCK.font, Integer.toString(te.enemy.block), te.x + tw * 0.05f, te.y + th * 0.025f);
                    sb.end();
                    shr.begin(ShapeRenderer.ShapeType.Filled);
                }
            }
        }
        shr.end();
        sb.begin();
        for(int i = 0; i < 4; i++) {
            PlayerView tp = players[i];
            EnemyView te = enemies[i];
            float tw = tp.sWidth;
            if(!tp.player.isDead) renderCenter(sb, HP, tp.player.health + "/" + tp.player.maxHealth, tp.x, tp.y + tp.sHeight * 0.05f / 2, tw, tp.sHeight * 0.05f);
            if(!te.enemy.isDead) renderCenter(sb, HP, te.enemy.health + "/" + te.enemy.maxHealth, te.x, te.y + te.sHeight * 0.05f / 2, tw, te.sHeight * 0.05f);
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
        drawPileButton.render(sb);
        discardPileButton.render(sb);
        endTurnButton.render(sb);
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
