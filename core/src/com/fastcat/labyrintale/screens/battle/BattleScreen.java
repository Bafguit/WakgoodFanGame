package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.enemies.TestEnemy;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.skills.player.Strike;
import com.fastcat.labyrintale.status.TestStatus;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.WAK_BABY;
import static com.fastcat.labyrintale.handlers.FontHandler.*;

public class BattleScreen extends AbstractScreen {

    public static final boolean[] DEF_LOOK = new boolean[] {false, false, false, false, false, false, false, false};
    public static final Color hbc = new Color(0.4f, 0, 0, 1);

    public ShapeRenderer shr = new ShapeRenderer();
    public ShapeRenderer shr2 = new ShapeRenderer();
    public NameText nameText;
    public EffectText effectText;
    public EffectText2 effectText2;
    public SkillButton advisor;
    public SkillButton skillInfo;
    public StatusButton statusInfo;
    public StatusButton[] playerStatus = new StatusButton[4];
    public StatusButton[] enemyStatus = new StatusButton[4];
    public SkillButton[] charSkills = new SkillButton[4];
    public SkillButton[] preSkills = new SkillButton[4];
    public SkillButton[] enemySkills = new SkillButton[4];
    public PlayerView[] players = new PlayerView[4];
    public EnemyView[] enemies = new EnemyView[4];
    public boolean isEnemyTurn = false;
    public boolean isLooking = false;
    public boolean[] looking = new boolean[8];
    public AbstractPlayer currentPlayer;

    public BattleScreen() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        nameText = new NameText();
        effectText = new EffectText();
        effectText2 = new EffectText2();
        advisor = new SkillButton(new Strike());
        advisor.skill.img = advisorSelectScreen.advisor.img;
        advisor.setPosition(w * 0.16f - advisor.sWidth / 2, h * 0.125f);
        statusInfo = new StatusButton();
        statusInfo.isInfo = true;
        skillInfo = new SkillButton();
        skillInfo.isInfo = true;
        skillInfo.isSkill = false;
        skillInfo.canClick = false;
        skillInfo.isCS = false;
        skillInfo.setScale(2.5f);
        skillInfo.setPosition(w * 0.55f, h * 0.15f - skillInfo.sHeight / 2);
        for(int i = 0; i < 4; i++) {
            PlayerView pv = new PlayerView(charSelectScreen.chars[i].selected);
            pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.45f);
            pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.475f);
            pv.player.shuffleHand();
            pv.player.status = new TestStatus();
            players[i] = pv;

            EnemyView ev = new EnemyView(AbstractLabyrinth.currentFloor.currentRoom.enemies[i]);
            ev.setPosition(w * 0.575f + w * 0.1f * i - ev.sWidth / 2, h * 0.45f);
            ev.enemy.setAnimXY(w * 0.575f + w * 0.1f * i, h * 0.475f);
            ev.enemy.shuffleHand();
            enemies[i] = ev;

            StatusButton t = new StatusButton(players[i].player);
            t.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.725f);
            playerStatus[i] = t;

            StatusButton t2 = new StatusButton(enemies[i].enemy);
            t2.setPosition(w * 0.575f + w * 0.1f * i + ev.sWidth / 2 - t2.sWidth, h * 0.725f);
            enemyStatus[i] = t2;

            SkillButton s = new SkillButton();
            s.setPosition(w * 0.42f - w * 0.06f * i - s.sWidth / 2, h * 0.125f);
            charSkills[i] = s;

            SkillButton s2 = new SkillButton();
            s2.setPosition(w * 0.410f + w * 0.06f * i - s.sWidth / 2, h * 0.3f);
            s2.isCS = false;
            preSkills[i] = s2;

            SkillButton s3 = new SkillButton();
            s3.setScale(0.6f);
            s3.setPosition(w * 0.575f + w * 0.1f * i - s3.sWidth / 2, h * 0.8f);
            s3.isCS = false;
            s3.canClick = false;
            enemySkills[i] = s3;
            setEnemy(enemies[i].enemy, i);
        }
        setCurrentPlayer(players[0].player);
    }

    @Override
    public void update() {
        if(skillInfo.skill == null) {
            isLooking = false;
            looking = DEF_LOOK;
        } else {
            isLooking = true;
        }
        for(int i = 0; i < 4; i++) {
            players[i].isLooking = looking[i];
            players[i].update();
            enemies[i].isLooking = looking[i + 4];
            enemies[i].update();
            playerStatus[i].update();
            enemyStatus[i].update();
            charSkills[i].update();
            preSkills[i].update();
            enemySkills[i].update();
        }
        advisor.update();
        skillInfo.update();
        nameText.update();
        effectText.update();
        effectText2.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for(int i=0; i< 4; i++) {
            if(isEnemyTurn) players[i].render(sb);
            else enemies[i].render(sb);
        }
        for(int i=0; i < 4; i++) {
            if(!isEnemyTurn) players[i].render(sb);
            else enemies[i].render(sb);
        }
        sb.end();
        shr.begin(ShapeRenderer.ShapeType.Filled);
        for(int i = 0; i < 4; i++) {
            PlayerView tp = players[i];
            EnemyView te = enemies[i];
            float tw = tp.sWidth;
            shr.setColor(hbc);
            shr.rect(tp.x + tw * 0.1f, tp.y, tw * 0.8f, tp.sHeight * 0.05f);
            shr.rect(te.x + tw * 0.1f, te.y, tw * 0.8f, tp.sHeight * 0.05f);
            shr.setColor(Color.SCARLET.cpy());
            shr.rect(tp.x + tw * 0.1f, tp.y, Math.max(tw * 0.8f * ((float) tp.player.health / (float) tp.player.maxHealth), 0), tp.sHeight * 0.05f);
            shr.rect(te.x + tw * 0.1f, te.y, Math.max(tw * 0.8f * ((float) te.enemy.health / (float) te.enemy.maxHealth), 0), tp.sHeight * 0.05f);
        }
        shr.end();
        sb.begin();
        for(int i = 0; i < 4; i++) {
            PlayerView tp = players[i];
            EnemyView te = enemies[i];
            float tw = tp.sWidth;
            renderCenter(sb, HP, tp.player.health + "/" + tp.player.maxHealth, tp.x, tp.y + tp.sHeight * 0.05f / 2, tw, tp.sHeight * 0.05f);
            renderCenter(sb, HP, te.enemy.health + "/" + te.enemy.maxHealth, te.x, te.y + te.sHeight * 0.05f / 2, tw, te.sHeight * 0.05f);
        }
        for(int i = 0; i < 4; i++) {
            playerStatus[i].render(sb);
            enemyStatus[i].render(sb);
            charSkills[i].render(sb);
            preSkills[i].render(sb);
            enemySkills[i].render(sb);
        }
        advisor.render(sb);
        skillInfo.render(sb);
        nameText.render(sb);
        effectText.render(sb);
        effectText2.render(sb);
    }

    public void setCurrentPlayer(AbstractPlayer p) {
        currentPlayer = p;
        for(int i = 0; i < 4; i++) {
            charSkills[i].skill = currentPlayer.hand[i];
        }
    }

    public void setEnemy(AbstractEnemy e, int index) {
        enemies[index].enemy = e;
        enemySkills[index].skill = enemies[index].enemy.hand[0];
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
