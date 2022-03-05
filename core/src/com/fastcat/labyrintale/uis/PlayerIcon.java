package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;
import static com.fastcat.labyrintale.abstracts.AbstractSkill.getTargets;
import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;
import static com.fastcat.labyrintale.handlers.FileHandler.BORDER_SS;
import static com.fastcat.labyrintale.handlers.FontHandler.FontData;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class PlayerIcon extends AbstractUI {

    public AbstractPlayer p;

    public PlayerIcon(AbstractPlayer p) {
        super(BORDER);
        this.p = p;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled && p != null) {
            sb.setColor(Color.WHITE);
            sb.draw(p.img, x, y, sWidth, sHeight);
            sb.draw(img, x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void updateButton() {
        if(over && p != null) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(p);
        }
    }

    public void setPlayer(AbstractPlayer p) {
        this.p = p;
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
