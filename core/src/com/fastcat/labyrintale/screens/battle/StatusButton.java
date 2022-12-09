package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FontHandler.STATUS;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class StatusButton extends AbstractUI {

    private final Sprite border = FileHandler.getUi().get("BORDER_SS");
    public AbstractStatus status;

    public StatusButton() {
        super(FileHandler.getUi().get("BORDER_SS"));
        fontData = STATUS;
        overable = true;
        clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);

            if (status != null) {
                sb.draw(status.img, x, y, sWidth, sHeight);
                if (status.hasAmount)
                    renderKeywordCenter(
                            sb,
                            fontData,
                            valueColor() + status.amount,
                            x - sWidth * 0.2f,
                            y + sHeight * 0.2f,
                            sWidth * 2,
                            sHeight);
                sb.draw(border, x, y, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void updateButton() {
        if (over && status != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(status);
            battleScreen.looking = getTargets(status);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return status != null ? status.key : null;
    }

    private String valueColor() {
        if (status.amount < 0) {
            return FontHandler.getHexColor(Color.SCARLET);
        } else {
            return "";
        }
    }
}
