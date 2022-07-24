package com.fastcat.labyrintale.screens.advisorselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractAdvisor;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class AdvisorButton extends AbstractUI {

    public AbstractAdvisor advisor;

    public AbstractAdvisor.AdvisorClass selected;
    public AdvisorSelectScreen select;

    public AdvisorButton(AbstractAdvisor adv, AdvisorSelectScreen select) {
        super(FileHandler.getUi().get("BORDER_M"));
        advisor = adv;
        this.select = select;
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if (enabled) {
            if (select.selected == this || over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(advisor.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        select.selected = this;
    }
}
