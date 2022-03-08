package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.uis.PlayerIcon;

public class BattlePanel implements Disposable {
    public SkillButtonPanel[] skill = new SkillButtonPanel[4];
    public SkillButtonPanel[] mSkill = new SkillButtonPanel[2];
    public SkillButtonPanel aSkill;
    public AbstractPlayer curPlayer;
    public PlayerIcon cpIcon;

    public BattlePanel() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        aSkill = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.ADVISOR);
        aSkill.setPosition(w * 0.14f - aSkill.sWidth / 2, h * 0.225f);
        mSkill[0] = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
        mSkill[0].setPosition(w * 0.44f - aSkill.sWidth / 2, h * 0.225f);
        mSkill[1] = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
        mSkill[1].setPosition(w * 0.5f - aSkill.sWidth / 2, h * 0.225f);
        for(int i = 0; i < 4; i++) {
            SkillButtonPanel s = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
            s.setPosition(w * 0.38f - w * 0.06f * i - s.sWidth / 2, h * 0.225f);
            skill[i] = s;
        }
        cpIcon = new PlayerIcon(AbstractLabyrinth.players[0]);
        cpIcon.setPosition(w * 0.125f - cpIcon.sWidth / 2,h * 0.35f);
    }

    public void update() {
        for(int i = 0; i < 4; i++) {
            skill[i].update();
        }
        for(int i = 0; i < 2; i++) {
            mSkill[i].update();
        }
        aSkill.update();
        cpIcon.setPlayer(curPlayer);
        cpIcon.update();
    }

    public void render(SpriteBatch sb) {
        for(int i = 0; i < 4; i++) {
            skill[i].render(sb);
        }
        for(int i = 0; i < 2; i++) {
            mSkill[i].render(sb);
        }
        aSkill.render(sb);
        cpIcon.render(sb);
    }

    public void setPlayer(AbstractPlayer p) {
        if(p.isAlive()) {
            curPlayer = p;
            for(int j = 0; j < 4; j++) {
                skill[j].skill = p.hand[3 - j];
            }
            aSkill.skill = AbstractLabyrinth.advisor.skill;
            mSkill[0].skill = p.mLeft;
            mSkill[1].skill = p.mRight;
        }
    }

    @Override
    public void dispose() {
        for(int i = 0; i < 4; i++) {
            skill[i].dispose();
        }
        for(int i = 0; i < 2; i++) {
            mSkill[i].dispose();
        }
        aSkill.dispose();
    }
}
