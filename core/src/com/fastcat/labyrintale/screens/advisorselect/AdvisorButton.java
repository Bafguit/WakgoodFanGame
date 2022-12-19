package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class AdvisorButton extends AbstractUI {

    public AbstractItem advisor;

    public TempUI adv = new TempUI(FileHandler.getUi().get("BORDER_M"));
    public AbstractAdvisor.AdvisorClass selected;
    public AdvisorSelectScreen select;

    public AdvisorButton(AbstractItem adv, AdvisorSelectScreen select) {
        super(FileHandler.getUi().get("BORDER_ADV"));
        advisor = adv;
        this.select = select;
        isPixmap = true;
    }

    @Override
    protected Array<SubText> getSubText() {
        return advisor != null ? advisor.key : subs;
    }

    @Override
    protected void updateButton() {
        if (over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(advisor);
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (select.selected == this || over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(advisor.img, x + sWidth / 2 - adv.sWidth / 2, y + 68 * InputHandler.scale, adv.sWidth, adv.sHeight);
        }
    }

    @Override
    protected void onClick() {
        if (AbstractLabyrinth.advisor != null) AbstractLabyrinth.advisor.onRemove();
        AbstractLabyrinth.advisor = advisor;
        AbstractLabyrinth.advisor.onGain();
        Labyrintale.removeTempScreen(select);
    }
}
