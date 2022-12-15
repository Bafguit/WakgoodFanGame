package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.KeyString;

public class EnergyPanel extends AbstractUI {

    private final RestoreEnergy res;

    public EnergyPanel() {
        super(FileHandler.getUi().get("ENERGY_ORB"));
        fontData = FontHandler.ENERGY;
        clickable = false;
        res = new RestoreEnergy(this);
        KeyString.KeyData k = StringHandler.keyString.get("Energy");
        subs.add(new SubText(k.NAME, k.DESC));
        isPixmap = true;
    }

    @Override
    protected void updateButton() {
        res.update();
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (AbstractLabyrinth.energy == 0) sb.setColor(Color.GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            FontHandler.renderCenter(
                    sb,
                    fontData,
                    AbstractLabyrinth.energy + "/" + AbstractLabyrinth.MAX_ENERGY,
                    x,
                    y + sHeight / 2,
                    sWidth,
                    sHeight);
            if(over) res.render(sb);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return subs;
    }

    private static class RestoreEnergy extends AbstractUI {

        private final EnergyPanel panel;

        public RestoreEnergy(EnergyPanel panel) {
            super(FileHandler.getUi().get("ENERGY_PANEL"));
            this.panel = panel;
            fontData = FontHandler.ENERGY_PANEL;
            setPosition(panel.x - sWidth, panel.y + panel.sHeight / 2 - sHeight * 0.55f);
            //setPosition(panel.x + panel.sWidth / 2 - sWidth / 2, panel.y + panel.sHeight * 1.05f);
            overable = false;
        }

        @Override
        protected void updateButton() {
            setPosition(panel.x - sWidth, panel.y + panel.sHeight / 2 - sHeight * 0.55f);
            //setPosition(panel.x + panel.sWidth / 2 - sWidth / 2, panel.y + panel.sHeight * 1.05f);
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            sb.draw(img, x, y, sWidth, sHeight);
            FontHandler.renderColorCenter(sb, fontData,
                    "매 라운드마다 &b<" + AbstractLabyrinth.charge + "> 회복",
                    x, y + sHeight * 0.52f, sWidth);
        }
    }
}
