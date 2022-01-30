package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.skills.MoveLeft;
import com.fastcat.labyrintale.skills.MoveRight;

public class ControlPanel {

    public InfoPanel info;

    public ControlPanel() {
        info = new InfoPanel();
    }

    public void update() {
        info.update();
    }

    public void render(SpriteBatch sb) {
        info.render(sb);
    }

    public static class BattleGroup {
        public SkillButtonPanel[] skill = new SkillButtonPanel[4];
        public SkillButtonPanel[] mSkill = new SkillButtonPanel[2];
        public SkillButtonPanel aSkill;

        public BattleGroup() {
            AbstractPlayer p;
            mSkill[0] = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
            mSkill[1] = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
            for(int i = 0; i < 4; i++) {
                skill[i] = new SkillButtonPanel(SkillButtonPanel.SkillButtonType.PLAYER);
            }

            for(int i = 0; i < 4; i++) {
                p = AbstractLabyrinth.players[i];
                if(p.isAlive()) {
                    for(int j = 0; j < 4; j++) {
                        skill[j].skill = p.hand[3 - j];
                    }
                    mSkill[0].skill = p.mLeft;
                    mSkill[1].skill = p.mRight;
                    break;
                }
            }
        }
    }

    public enum ControlType {
        BATTLE, MAP, DECKVIEW, REWARD, SHOP
    }
}
