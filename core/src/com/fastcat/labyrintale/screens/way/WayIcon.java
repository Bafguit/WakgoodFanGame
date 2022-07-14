package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class WayIcon extends AbstractUI {

    public Sprite icon;
    public Sprite bIcon;
    public WaySelectButton b;

    public WayIcon(WaySelectButton b, Sprite i) {
        super(FileHandler.ui.get("BORDER_M"));
        icon = i;
        this.b = b;
        bIcon = FileHandler.ui.get("MYSTERY");
        overable = false;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!b.over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if(b.showImg) sb.draw(AbstractLabyrinth.bleak >= 60 ? bIcon : icon, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }
}
