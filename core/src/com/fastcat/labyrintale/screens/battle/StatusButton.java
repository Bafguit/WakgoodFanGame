package com.fastcat.labyrintale.screens.battle;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FileHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

public class StatusButton extends AbstractUI {

    private final Sprite border = FileHandler.ui.get("BORDER_SS");
    public AbstractStatus status;

    public StatusButton(AbstractStatus s) {
        super(FileHandler.ui.get("BORDER_SS"));
        fontData = new FontHandler.FontData(MEDIUM, 24, true);
        status = s;
        overable = true;
        clickable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            sb.setColor(Color.WHITE);

            if(status != null) {
                sb.draw(status.img, x, y, sWidth, sHeight);
                if(status.hasAmount) renderKeywordCenter(sb, fontData, valueColor() + status.amount, x, y + sHeight / 2, sWidth, sHeight);
                sb.draw(border, x, y, sWidth, sHeight);
            }
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
}
