package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.screens.deckview.DeckSkillButton.getTargetString;

public class StatusButton extends AbstractUI {

    private final Sprite border = CHAR_SELECT;
    public AbstractStatus status;
    public boolean isInfo = false;

    public StatusButton() {
        super(CHAR_SELECT);
        fontData = new FontHandler.FontData(MEDIUM, 16, true);
    }

    public StatusButton(AbstractStatus s) {
        this();
        setScale(0.25f);
        status = s;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over && !isInfo) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(status != null) {
                sb.draw(/*isInfo ? status.imgBig : */status.img, x, y, sWidth, sHeight);
                if(!isInfo && status.hasAmount) renderKeywordCenter(sb, fontData, valueColor() + status.amount, x, y + sHeight / 2, sWidth, sHeight);
                if(isInfo) renderCenter(sb, CARD_BIG_DESC, getTargetString(status.target), x, y - sHeight * 0.1f, sWidth, sHeight);
            }
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(!isInfo && over && status != null) {
            battleScreen.statusInfo.status = status;
            battleScreen.nameText.text = status.name;
            battleScreen.effectText2.text = status.getDesc();
            battleScreen.looking = getTargets(status.target);
        } else if(isInfo && status != null) {
            boolean ov = false;
            for (int i = 0; i < 4; i++) {
                for(int j = 0; j < 5; j++) {
                    if (battleScreen.playerStatus[i][j].over || battleScreen.enemyStatus[i][j].over) {
                        ov = true;
                        break;
                    }
                }
            }
            if(!ov) {
                status = null;
                battleScreen.nameText.text = "";
                battleScreen.effectText2.text = "";
            }
        }
    }

    private String valueColor() {
        if(status.amount < 0) {
            return FontHandler.getHexColor(Color.SCARLET);
        } else {
            return "";
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
