package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractScreen;

public class RewardScreen extends AbstractScreen {

    public Array<RewardType> rewards;

    public RewardScreen(Array<RewardType> rewards) {
        this.rewards = rewards;
    }

    @Override
    public void update() {

    }

    @Override
    public void render(SpriteBatch sb) {

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

    public enum RewardType {
        SKILL, GOLD, TALENT
    }
}
