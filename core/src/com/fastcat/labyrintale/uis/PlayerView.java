package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

public class PlayerView extends AbstractUI {

    public AbstractPlayer player;

    public PlayerView(AbstractPlayer cls) {
        super(FileHandler.getUi().get("ENTITY_POINT"));
        player = cls;
        showImg = false;
    }

    @Override
    protected void updateButton() {
        clickable = player.isAlive();
    }

    @Override
    public void render(SpriteBatch sb) {
        if (!player.isDead) {
            sb.setColor(Color.WHITE);
            player.render(sb);
        }
    }

    @Override
    protected void onClick() {
        if (player != null && player.isAlive()) {

        }
    }
}
