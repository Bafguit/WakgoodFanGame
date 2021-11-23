package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.skills.Strike;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.WAK_BABY;

public class BattleScreen extends AbstractScreen {

    private static boolean[] DEF_LOOK = new boolean[] {false, false, false, false, false, false, false, false};

    public NameText nameText;
    public EffectText effectText;
    public SkillButton advisor;
    public SkillButton skillInfo;
    public SkillButton[] charSkills = new SkillButton[16];
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
        advisor = new SkillButton(new Strike());
        advisor.skill.img = WAK_BABY;
        advisor.setPosition(w * 0.16f - advisor.sWidth / 2, h * 0.125f);
        skillInfo = new SkillButton();
        skillInfo.isInfo = true;
        skillInfo.isSkill = false;
        skillInfo.canClick = false;
        skillInfo.isCS = false;
        skillInfo.setScale(2.5f);
        skillInfo.setPosition(w * 0.55f, h * 0.15f - skillInfo.sHeight / 2);
        for(int i = 0; i < 4; i++) {
            SkillButton s = new SkillButton(new Strike());
            s.setPosition(w * 0.24f + w * 0.06f * i - s.sWidth / 2, h * 0.125f);
            charSkills[i] = s;

            SkillButton s2 = new SkillButton();
            s2.setPosition(w * 0.410f + w * 0.06f * i - s.sWidth / 2, h * 0.3f);
            s2.isCS = false;
            preSkills[i] = s2;

            SkillButton s3 = new SkillButton(new Strike());
            s3.setScale(0.6f);
            s3.setPosition(w * 0.575f + w * 0.1f * i - s3.sWidth / 2, h * 0.8f);
            s3.isCS = false;
            s3.canClick = false;
            enemySkills[i] = s3;

            PlayerView pv = new PlayerView(charSelectScreen.chars[i].selected);
            pv.setPosition(w * 0.425f - w * 0.1f * i - pv.sWidth / 2, h * 0.45f);
            pv.player.setAnimXY(w * 0.425f - w * 0.1f * i, h * 0.475f);
            players[i] = pv;

            //TODO EnemyView
            EnemyView ev = new EnemyView();
            ev.setPosition(w * 0.575f + w * 0.1f * i - ev.sWidth / 2, h * 0.45f);
            enemies[i] = ev;
        }
        currentPlayer = players[0].player;
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
            charSkills[i].update();
            preSkills[i].update();
            enemySkills[i].update();
        }
        advisor.update();
        skillInfo.update();
        nameText.update();
        effectText.update();
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
        for(int i = 0; i < 4; i++) {
            charSkills[i].render(sb);
            preSkills[i].render(sb);
            enemySkills[i].render(sb);
        }
        advisor.render(sb);
        skillInfo.render(sb);
        nameText.render(sb);
        effectText.render(sb);
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
