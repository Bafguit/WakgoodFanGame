package com.fastcat.labyrintale.screens.allskill;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class AllSlotButton extends AbstractUI {

    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    private final FontHandler.FontData fd = FontHandler.CARD_BIG_DESC;
    private final FontHandler.FontData up = FontHandler.UPGRADE;
    public AbstractPlayer player;
    public AbstractSkill skill;
    public int index;
    public AllSkillScreen select;

    public AllSlotButton(AbstractPlayer player, int index, AllSkillScreen select) {
        super(FileHandler.getUi().get("BORDER_M"));
        this.player = player;
        this.index = index;
        skill = player.deck.get(index);
        this.select = select;
        fontData = FontHandler.CARD_BIG_DESC;
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
            if (over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(skill.img, x, y, sWidth, sHeight);
            if (skill.owner != null) sb.setColor(skill.owner.pColor);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
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

    @Override
    protected void onClick() {
        select.slotSelected(player, index);
        Labyrintale.removeTempScreen(select);
    }
}
