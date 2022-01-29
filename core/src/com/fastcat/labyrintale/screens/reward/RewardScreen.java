package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.screens.deckview.BgImg;
import com.fastcat.labyrintale.screens.shop.ShopItemButton;

public class RewardScreen extends AbstractScreen {

    public BgImg bg = new BgImg();
    public RewardTypeText rewardTypeText;
    public RewardInfoText rewardInfoText;
    public PassRewardButton passButton;
    public Array<RewardItemButton> rewardButtons = new Array<>();
    public Array<AbstractReward> rewards;
    public RewardScreenType sType;

    public RewardScreen(RewardScreenType type, Array<AbstractReward> rewards) {
        this.rewards = rewards;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < rewards.size; i++) {
            float hf = (float) rewards.size / 2 - 0.5f;
            RewardItemButton temp = new RewardItemButton(rewards.get(i));
            temp.setPosition(w * (0.5f - (hf - i) * 0.1f) - temp.sWidth * 0.5f, h * 0.5f - temp.sHeight * 0.5f);
            rewardButtons.add(temp);
        }
        rewardTypeText = new RewardTypeText(type);
        passButton = new PassRewardButton();
        rewardInfoText = new RewardInfoText();
        sType = type;
    }

    @Override
    public void update() {
        boolean isOver = false;
        for(RewardItemButton b : rewardButtons) {
            b.update();
            if(b.over) {
                isOver = true;
                rewardInfoText.text = b.reward.desc;
            }
        }
        if(!isOver) rewardInfoText.text = "";
        passButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        rewardTypeText.render(sb);
        for(RewardItemButton b : rewardButtons) {
            b.render(sb);
        }
        passButton.render(sb);
    }

    @Override
    public void show() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {
        for(RewardItemButton t : rewardButtons) {
            t.dispose();
        }
    }

    public enum RewardScreenType {
        VICTORY, CHEST, EVENT
    }
}
