package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen.InfoType;

import java.util.Objects;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class DeckSkillButton extends AbstractUI {

    private Sprite border = BORDER;
    public AbstractSkill skill;
    public boolean isInfo = false;

    public DeckSkillButton() {
        this(null);
        isInfo = true;
        fontData = CARD_BIG_DESC;
        disable();
    }

    public DeckSkillButton(AbstractSkill skill) {
        super(BORDER);
        this.skill = skill;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(showImg && skill != null) {
                sb.draw(isInfo ? skill.imgBig : skill.img, x, y, sWidth, sHeight);
                if(isInfo) renderCenter(sb, fontData, AbstractSkill.getTargetString(skill.target), x, y - sHeight * 0.1f, sWidth, sHeight);
            }
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(skill != null && screen instanceof DeckViewScreen) {
            if (!isInfo && over) {
                if(skill.upgraded) {
                    setTo(InfoType.UPGRADED);
                    ((DeckViewScreen)screen).upInfo.skill = skill;
                } else {
                    setTo(InfoType.NORMAL);
                    AbstractSkill ts = skill.cpy();
                    Objects.requireNonNull(ts).upgrade();
                    ((DeckViewScreen)screen).info.skill = skill;
                    ((DeckViewScreen)screen).upInfo.skill = ts;
                }
            }
        }
    }

    private void setTo(InfoType it) {
        if(screen instanceof DeckViewScreen) {
            DeckViewScreen s = ((DeckViewScreen) screen);
            s.iType = it;
            float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
            if (s.iType == InfoType.NORMAL) {
                s.showArrow = true;
                s.info.setPosition(w * 0.55f, h * 0.75f - ((DeckViewScreen) screen).info.sHeight / 2);
                s.skillNameText.setPosition(w * 0.69f, h * 0.85f);
                s.skillEffectText.setPosition(w * 0.69f, h * 0.85f - 45 * scale);
                s.upInfo.setPosition(w * 0.55f, h * 0.25f - ((DeckViewScreen) screen).upInfo.sHeight / 2);
                s.upNameText.setPosition(w * 0.69f, h * 0.35f);
                s.upEffectText.setPosition(w * 0.69f, h * 0.35f - 45 * scale);
                s.info.enable();
            } else {
                s.showArrow = false;
                s.upInfo.setPosition(w * 0.55f, h * 0.5f - ((DeckViewScreen) screen).upInfo.sHeight / 2);
                s.upNameText.setPosition(w * 0.69f, h * 0.6f);
                s.upEffectText.setPosition(w * 0.69f, h * 0.6f - 45 * scale);
            }
            s.upInfo.enable();
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(screen instanceof DeckViewScreen) {
            DeckViewScreen s = ((DeckViewScreen) screen);
            if(s.type == DeckViewScreen.ViewType.REMOVE) {
                //TODO 여기에 스킬 제거 입력
            }
        }
    }
}
