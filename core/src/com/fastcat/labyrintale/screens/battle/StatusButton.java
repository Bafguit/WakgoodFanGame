package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class StatusButton extends AbstractUI {

    private final Sprite border = BORDER;
    public AbstractStatus status;

    public StatusButton(AbstractStatus s) {
        super(BORDER_SS);
        fontData = new FontHandler.FontData(MEDIUM, 16, true);
        status = s;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);

            if(status != null) {
                sb.draw(status.img, x, y, sWidth, sHeight);
                if(status.hasAmount) renderKeywordCenter(sb, fontData, valueColor() + status.amount, x, y + sHeight / 2, sWidth, sHeight);
                sb.draw(border, x, y, sWidth, sHeight);
            }
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {
        if(over && status != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(status);
            battleScreen.looking = getTargets(status);
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
