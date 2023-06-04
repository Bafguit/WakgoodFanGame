package com.fastcat.labyrintale.screens.charinfo;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.charInfoScreen;

public class CharInfoIcon extends AbstractUI {

    public int index;
    public AbstractPlayer p;

    public CharInfoIcon(int index) {
        super(FileHandler.getUi().get("BORDER_V2"));
        this.index = index;
        setScale(1.1f);
        p = AbstractLabyrinth.players[index];
        clickable = false;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled && p != null) {
            sb.setColor(p.isAlive() ? p.animColor : Color.DARK_GRAY);
            sb.draw(p.imgPanel, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {
        p = AbstractLabyrinth.players[index];
        if (over && p != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(p);
        }
    }
}
