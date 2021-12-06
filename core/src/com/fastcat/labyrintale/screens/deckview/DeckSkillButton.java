package com.fastcat.labyrintale.screens.deckview;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen.InfoType;

import java.util.Objects;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.Labyrintale.deckViewScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.ActionHandler.isRunning;
import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class DeckSkillButton extends AbstractUI {

    private Sprite border = CHAR_SELECT;
    public AbstractSkill skill;
    public boolean isInfo = false;

    public DeckSkillButton() {
        this(null);
        isInfo = true;
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

            if(showImg && skill != null) sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(skill != null) {
            if (!isInfo && over) {
                if(skill.upgraded) {
                    if(deckViewScreen.iType != InfoType.UPGRADED) setTo(InfoType.UPGRADED);
                    deckViewScreen.upInfo.skill = skill;
                    deckViewScreen.upNameText.text = skill.name;
                    deckViewScreen.upEffectText.text = skill.desc;
                } else {
                    if(deckViewScreen.iType != InfoType.NORMAL) setTo(InfoType.UPGRADED);
                    AbstractSkill ts = skill.cpy();
                    Objects.requireNonNull(ts).upgrade();
                    deckViewScreen.info.skill = skill;
                    deckViewScreen.skillNameText.text = skill.name;
                    deckViewScreen.skillEffectText.text = skill.desc;
                    deckViewScreen.upInfo.skill = ts;
                    deckViewScreen.upNameText.text = ts.name;
                    deckViewScreen.upEffectText.text = ts.desc;
                }
            } else if(isInfo) {
                boolean ov = false;
                for(DeckSkillButton b : deckViewScreen.buttons) {
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
            deckViewScreen.info.setPosition(w * 0.05f, h * 0.15f - deckViewScreen.info.sHeight / 2);
            deckViewScreen.skillNameText.setPosition(w * 0.19f, h * 0.25f);
            deckViewScreen.skillEffectText.setPosition(w * 0.19f, h * 0.25f - 45 * scale);
            deckViewScreen.upInfo.setPosition(w * 0.55f, h * 0.15f - deckViewScreen.upInfo.sHeight / 2);
            deckViewScreen.upNameText.setPosition(w * 0.69f, h * 0.25f);
            deckViewScreen.upEffectText.setPosition(w * 0.69f, h * 0.25f - 45 * scale);
            deckViewScreen.info.enable();
        } else {
            deckViewScreen.showArrow = false;
            deckViewScreen.upInfo.setPosition(w * 0.3f, h * 0.15f - deckViewScreen.upInfo.sHeight / 2);
            deckViewScreen.upNameText.setPosition(w * 0.44f, h * 0.25f);
            deckViewScreen.upEffectText.setPosition(w * 0.44f, h * 0.25f - 45 * scale);
        }
        deckViewScreen.upInfo.enable();
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
