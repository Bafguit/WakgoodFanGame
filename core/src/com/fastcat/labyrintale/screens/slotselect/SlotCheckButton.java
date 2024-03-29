package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class SlotCheckButton extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final FontHandler.FontData fd = FontHandler.CARD_BIG_DESC;
    public AbstractSkill skill;

    public SlotCheckButton(AbstractSkill skill) {
        super(FileHandler.getUi().get("BORDER_M"));
        this.skill = skill;
        clickable = false;
        fontData = FontHandler.COOLDOWN;
    }

    @Override
    protected Array<SubText> getSubText() {
        return skill != null ? skill.key : subs;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(skill);
        }
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
                        fd,
                        Integer.toString(skill.cost),
                        x + sWidth * 0.05f,
                        y + sWidth * 0.85f,
                        sWidth * 0.2f,
                        sWidth * 0.2f);
            }
        }
    }
}
