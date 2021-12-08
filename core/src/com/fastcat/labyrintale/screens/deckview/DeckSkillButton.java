package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.battle.EnemyView;
import com.fastcat.labyrintale.screens.battle.PlayerView;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen.InfoType;

import java.util.Objects;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.deckViewScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class DeckSkillButton extends AbstractUI {

    private Sprite border = CHAR_SELECT;
    public AbstractSkill skill;
    public boolean isInfo = false;

    public DeckSkillButton() {
        this(null);
        isInfo = true;
        fontData = CARD_BIG_DESC;
        disable();
    }

    public DeckSkillButton(AbstractSkill skill) {
        super(CHAR_SELECT);
        this.skill = skill;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(showImg && skill != null) {
                sb.draw(skill.img, x, y, sWidth, sHeight);
                if(isInfo) renderCenter(sb, fontData, getTargetString(skill.target), x, y - sHeight * 0.1f, sWidth, sHeight);
            }
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(skill != null) {
            if (!isInfo && over) {
                if(skill.upgraded) {
                    setTo(InfoType.UPGRADED);
                    deckViewScreen.upInfo.skill = skill;
                } else {
                    setTo(InfoType.NORMAL);
                    AbstractSkill ts = skill.cpy();
                    Objects.requireNonNull(ts).upgrade();
                    deckViewScreen.info.skill = skill;
                    deckViewScreen.upInfo.skill = ts;
                }
            } else if(isInfo) {
                boolean ov = false;
                for(DeckSkillButton b : deckViewScreen.bs) {
                    if(b.over) {
                        ov = true;
                        break;
                    }
                }
                enabled = ov;
            }
        }
    }

    private void setTo(InfoType it) {
        deckViewScreen.iType = it;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        if (deckViewScreen.iType == InfoType.NORMAL) {
            deckViewScreen.showArrow = true;
            deckViewScreen.info.setPosition(w * 0.55f, h * 0.75f - deckViewScreen.info.sHeight / 2);
            deckViewScreen.skillNameText.setPosition(w * 0.69f, h * 0.85f);
            deckViewScreen.skillEffectText.setPosition(w * 0.69f, h * 0.85f - 45 * scale);
            deckViewScreen.upInfo.setPosition(w * 0.55f, h * 0.25f - deckViewScreen.upInfo.sHeight / 2);
            deckViewScreen.upNameText.setPosition(w * 0.69f, h * 0.35f);
            deckViewScreen.upEffectText.setPosition(w * 0.69f, h * 0.35f - 45 * scale);
            deckViewScreen.info.enable();
        } else {
            deckViewScreen.showArrow = false;
            deckViewScreen.upInfo.setPosition(w * 0.55f, h * 0.5f - deckViewScreen.upInfo.sHeight / 2);
            deckViewScreen.upNameText.setPosition(w * 0.69f, h * 0.6f);
            deckViewScreen.upEffectText.setPosition(w * 0.69f, h * 0.6f - 45 * scale);
        }
        deckViewScreen.upInfo.enable();
    }

    public static String getTargetString(AbstractSkill.CardTarget target) {
        switch(target) {
            case P_F:
                return "○○○●　○○○○";
            case E_F:
                return "○○○○　●○○○";
            case P_L:
                return "●○○○　○○○○";
            case E_L:
                return "○○○○　○○○●";
            case P_DF:
                return "○○●●　○○○○";
            case E_DF:
                return "○○○○　●●○○";
            case P_DL:
                return "●●○○　○○○○";
            case E_DL:
                return "○○○○　○○●●";
            case P_ALL:
                return "●●●●　○○○○";
            case E_ALL:
                return "○○○○　●●●●";
            case ALL:
                return "●●●●　●●●●";
        }
        return "Target is Null";
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
