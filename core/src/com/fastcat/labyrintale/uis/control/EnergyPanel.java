package com.fastcat.labyrintale.uis.control;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;


public class EnergyPanel extends AbstractUI {

    public EnergyPanel() {
        super(FileHandler.ui.get("ENERGY_ORB"));
        fontData = FontHandler.COOLDOWN;
        overable = false;
        clickable = false;
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if(AbstractLabyrinth.energy == 0) sb.setColor(Color.GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            FontHandler.renderCenter(sb, fontData, Integer.toString(AbstractLabyrinth.energy), x, y + sHeight / 2, sWidth, sHeight);
        }
    }
}
