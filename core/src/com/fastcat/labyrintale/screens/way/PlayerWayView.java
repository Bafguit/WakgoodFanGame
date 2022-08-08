package com.fastcat.labyrintale.screens.way;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.Labyrintale.battleScreen;

public class PlayerWayView extends AbstractUI {

    public AbstractPlayer player;

    public PlayerWayView(AbstractPlayer cls) {
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
