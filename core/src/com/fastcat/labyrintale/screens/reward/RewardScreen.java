package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.screens.deckview.BgImg;

public class RewardScreen extends AbstractScreen {

    public BgImg bg = new BgImg();
    private final BattleEndText battleEndText = new BattleEndText();
    public Array<AbstractReward> rewards;

    public RewardScreen(Array<AbstractReward> rewards) {
        this.rewards = rewards;
    }

    @Override
    public void update() {
        battleEndText.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        battleEndText.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }


}
