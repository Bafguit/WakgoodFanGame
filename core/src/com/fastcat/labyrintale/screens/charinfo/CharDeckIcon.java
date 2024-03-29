package com.fastcat.labyrintale.screens.charinfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class CharDeckIcon extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final FontHandler.FontData up = FontHandler.UPGRADE;
    public AbstractSkill skill;

    public CharDeckIcon(AbstractSkill s) {
        super(FileHandler.getUi().get("BORDER_M"));
        skill = s;
        clickable = false;
        fontData = FontHandler.CARD_BIG_DESC;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : null;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);
            sb.draw(skill.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);

            if (!skill.passive) {
                sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                FontHandler.renderCenter(
                        sb,
                        fontData,
                        Integer.toString(skill.cost),
                        x + sWidth * 0.05f,
                        y + sWidth * 0.85f,
                        sWidth * 0.2f,
                        sWidth * 0.2f);
            }
            if (skill.upgradeCount > 0) {
                FontHandler.renderLineRight(
                        sb,
                        up,
                        "+" + skill.upgradeCount,
                        x + sWidth * 0.75f,
                        y + sWidth * 0.2f,
                        sWidth * 0.2f,
                        sWidth * 0.2f);
            }
        }
    }
}
