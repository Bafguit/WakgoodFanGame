package com.fastcat.labyrintale.screens.playerselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.uis.BgImg;

public class PlayerSelectScreen extends AbstractScreen implements GetSelectedPlayer {

    public BgImg bg = new BgImg();
    public PlayerSelectText playerSelectText;
    public PlayerConfirmButton nextButton;
    public PlayerButton selected;
    public PlayerButton[] pPlayer;
    public GetSelectedPlayer gets;

    public PlayerSelectScreen(GetSelectedPlayer gets) {
        this(AbstractLabyrinth.players, gets);
    }

    public PlayerSelectScreen(AbstractPlayer[] players, GetSelectedPlayer gets) {
        playerSelectText = new PlayerSelectText();
        nextButton = new PlayerConfirmButton(this);
        this.gets = gets;
        int size = players.length;
        pPlayer = new PlayerButton[size];
        float w = Gdx.graphics.getWidth() * (1.0f / (size + 1)), h = Gdx.graphics.getHeight();
        for (int i = 0; i < size; i++) {
            PlayerButton adv = new PlayerButton(players[i], this);
            adv.setPosition(w * (i + 1) - adv.sWidth / 2, h * 0.6f);
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
        if (selected != null) AbstractLabyrinth.cPanel.infoPanel.setInfo(selected.player);
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
