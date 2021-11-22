package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractRoom;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.mapScreen;
import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.currentFloor;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;
import static com.fastcat.labyrintale.handlers.ImageHandler.*;

public class SkillButton extends AbstractUI {

    private Texture border = CHAR_SELECT;
    public AbstractSkill skill;
    public boolean isInfo = false;
    public boolean isLock = false;
    public boolean isSkill = true;
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
            if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);
            if(showImg && skill != null) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);

            if(fontData != null) {
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void updateButton() {
        if(!isInfo && isSkill && over && skill != null) {
            Labyrintale.battleScreen.skillInfo.skill = skill;
            battleScreen.nameText.text = skill.name;
            battleScreen.effectText.text = skill.desc;
        }
        if(isInfo && skill != null) {
            boolean ov = false;
            if(!battleScreen.advisor.over) {
                for (int i = 0; i < 4; i++) {
                    if (battleScreen.charSkills[i].over || battleScreen.preSkills[i].over) {
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

    }
}
