package com.fastcat.labyrintale.screens.playerselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedPlayer;
import com.fastcat.labyrintale.uis.BgImg;

import static com.fastcat.labyrintale.abstracts.AbstractLabyrinth.Mode.SOLO;

public class PlayerSelectScreen extends AbstractScreen implements GetSelectedPlayer {

    public BgImg bg = new BgImg();
    public PlayerSelectText playerSelectText;
    public PlayerButton[] pPlayer;
    public GetSelectedPlayer gets;
    public GetRewardDone rewardDone;
    public CancelPlayerButton cancel;

    public PlayerSelectScreen(GetSelectedPlayer gets) {
        this(AbstractLabyrinth.players, gets, null);
    }

    public PlayerSelectScreen(AbstractPlayer[] players, GetSelectedPlayer gets, GetRewardDone rewardDone) {
        playerSelectText = new PlayerSelectText();
        this.gets = gets;
        this.rewardDone = rewardDone;
        int size = AbstractLabyrinth.mode == SOLO ? 1 : players.length;
        pPlayer = new PlayerButton[size];
        float w = Gdx.graphics.getWidth() * (1.0f / (size + 1)), h = 1440 * InputHandler.scale;
        for (int i = 0; i < size; i++) {
            PlayerButton adv = new PlayerButton(players[i], this);
            adv.setPosition(w * (i + 1) - adv.sWidth / 2, h * 0.55f);
            pPlayer[i] = adv;
        }
        cancel = new CancelPlayerButton(this);
    }

    @Override
    public void update() {
        for (PlayerButton advisorButton : pPlayer) {
            advisorButton.update();
        }
        playerSelectText.update();
        if (rewardDone != null) cancel.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        for (PlayerButton advisorButton : pPlayer) {
            advisorButton.render(sb);
        }
        playerSelectText.render(sb);
        if (rewardDone != null) cancel.render(sb);
    }

    @Override
    public void show() {}

    @Override
    public void hide() {}

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
