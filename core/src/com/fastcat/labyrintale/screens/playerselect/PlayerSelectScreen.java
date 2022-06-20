package com.fastcat.labyrintale.screens.playerselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.screens.deckview.BgImg;

public class PlayerSelectScreen extends AbstractScreen implements GetSelectedPlayer {

    public BgImg bg = new BgImg();
    public PlayerSelectText playerSelectText;
    public PlayerConfirmButton nextButton;
    public PlayerButton selected;
    public PlayerButton[] pPlayer = new PlayerButton[4];
    public GetSelectedPlayer gets;

    public PlayerSelectScreen(GetSelectedPlayer gets) {
        playerSelectText = new PlayerSelectText();
        nextButton = new PlayerConfirmButton(this);
        this.gets = gets;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < 4; i++) {
            PlayerButton adv = new PlayerButton(AbstractLabyrinth.players[i], this);
            adv.setPosition(w * 0.2f * (i + 1) - adv.sWidth / 2, h * 0.6f);
            pPlayer[i] = adv;
        }
    }

    @Override
    public void update() {
        for (PlayerButton advisorButton : pPlayer) {
            advisorButton.update();
        }
        nextButton.update();
        playerSelectText.update();
        if(selected != null) AbstractLabyrinth.cPanel.infoPanel.setInfo(selected.player);
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (PlayerButton advisorButton : pPlayer) {
            advisorButton.render(sb);
        }
        nextButton.render(sb);
        playerSelectText.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for (PlayerButton advisorButton : pPlayer) {
            advisorButton.dispose();
        }
    }

    @Override
    public void playerSelected(AbstractPlayer player) {
        gets.playerSelected(player);
    }
}
