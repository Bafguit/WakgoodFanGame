package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.screens.deckview.DeckSkillButton;

import java.util.Objects;

import static com.fastcat.labyrintale.Labyrintale.*;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

public class SkillButton extends AbstractUI {

    private final Sprite border = CHAR_SELECT;
    public AbstractSkill skill;
    public boolean isInfo = false;
    public boolean isOnLock = false;
    public boolean isCS = true;
    public boolean isSkill = true;
    public boolean isSelected = false;
    public boolean canClick = true;
    public boolean advisor = false;
    public boolean available = true;

    public SkillButton() {
        this(null);
    }

    public SkillButton(AbstractSkill skill) {
        super(CHAR_SELECT);
        this.skill = skill;
        fontData = CARD_BIG_DESC;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(isSelected || !available || (advisor && !canClick) || (isRunning && (advisor || isCS))) sb.setColor(Color.DARK_GRAY);
            else if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);

            if(skill != null) {
                if (showImg) sb.draw(isInfo ? skill.imgBig : skill.img, x, y, sWidth, sHeight);
                if (isInfo)
                    renderCenter(sb, fontData, DeckSkillButton.getTargetString(Objects.requireNonNull(skill).target), x, y - sHeight * 0.1f, sWidth, sHeight);
            }
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(skill != null) {
            if (isCS) {
                isSelected = false;
                for (int i = 0; i < 4; i++) {
                    SkillButton ss = battleScreen.preSkills[i];
                    if (ss.isOnLock && skill == ss.skill) {
                        isSelected = true;
                        break;
                    }
                }
            }
            if (!isInfo && isSkill) {
                if(skill.owner != null && !skill.owner.isAlive()) {
                    skill = null;
                } else if(over) {
                    Labyrintale.battleScreen.skillInfo.skill = skill;
                    battleScreen.nameText.text = skill.name;
                    battleScreen.effectText.text = skill.desc;
                    battleScreen.looking = getTargets(skill);
                }
            } else if (isInfo) {
                boolean ov = false;
                if (!battleScreen.advisor.over) {
                    for (int i = 0; i < 4; i++) {
                        if (battleScreen.charSkills[i].over || battleScreen.preSkills[i].over || battleScreen.enemySkills[i].over) {
                            ov = true;
                            break;
                        }
                    }
                } else ov = true;
                if (!ov) {
                    skill = null;
                    battleScreen.nameText.text = "";
                }
            }
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(!isInfo && canClick && !battleScreen.isEnemyTurn && !isRunning) {
            if(isCS) {
                if(!isSelected) {
                    if(advisor || skill.isTrick) {
                        skill.useCard();
                    } else {
                        for (int i = 0; i < 4; i++) {
                            SkillButton chb = battleScreen.preSkills[i];
                            if (!chb.isOnLock && chb.available) {
                                chb.isOnLock = true;
                                chb.skill = skill;
                                chb.showImg = true;
                                chb.img = img;
                                chb.isSelected = true;
                                skill.useCard();
                                break;
                            }
                        }
                    }
                }
            }
        }
    }

    public void removeChar() {
        skill = null;
        showImg = false;
        isOnLock = false;
        isSelected = false;
    }
}
