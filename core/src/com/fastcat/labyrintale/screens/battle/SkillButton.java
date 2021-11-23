package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.handlers.FileHandler.*;

public class SkillButton extends AbstractUI {

    private Texture border = CHAR_SELECT;
    public AbstractSkill skill;
    public boolean isInfo = false;
    public boolean isOnLock = false;
    public boolean isCS = true;
    public boolean isSkill = true;
    public boolean isSelected = false;
    public boolean canClick = true;

    public SkillButton() {
        this(null);
    }

    public SkillButton(AbstractSkill skill) {
        super(CHAR_SELECT);
        this.skill = skill;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(isSelected) sb.setColor(Color.DARK_GRAY);
            else if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);

            if(showImg && skill != null) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(isCS) {
            isSelected = false;
            for (int i = 0; i < 4; i++) {
                SkillButton ss = battleScreen.preSkills[i];
                if (ss.isOnLock && skill.uid == ss.skill.uid) {
                    isSelected = true;
                    break;
                }
            }
        }
        if(!isInfo && isSkill && over && skill != null) {
            Labyrintale.battleScreen.skillInfo.skill = skill;
            battleScreen.nameText.text = skill.name;
            battleScreen.effectText.text = skill.desc;
            battleScreen.looking = skill.target;
        }
        if(isInfo && skill != null) {
            boolean ov = false;
            if(!battleScreen.advisor.over) {
                for (int i = 0; i < 4; i++) {
                    if (battleScreen.charSkills[i].over || battleScreen.preSkills[i].over || battleScreen.enemySkills[i].over) {
                        ov = true;
                        break;
                    }
                }
            } else ov = true;
            if(!ov) {
                skill = null;
                battleScreen.nameText.text = "";
            }
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(!isInfo && canClick) {
            if(isCS) {
                if(!isSelected) {
                    for(int i = 0; i < 4; i++) {
                        SkillButton chb = battleScreen.preSkills[i];
                        if(!chb.isOnLock) {
                            chb.isOnLock = true;
                            chb.skill = skill;
                            chb.showImg = true;
                            chb.img = img;
                            break;
                        }
                    }
                } else {
                    for(int i = 0; i < 4; i++) {
                        SkillButton chb = battleScreen.preSkills[i];
                        if(chb.isOnLock && chb.skill.uid == skill.uid) {
                            chb.removeChar();
                            break;
                        }
                    }
                }
            } else if(isOnLock) {
                removeChar();
            }
        }
    }

    public void removeChar() {
        skill = null;
        showImg = false;
        isOnLock = false;
    }
}
