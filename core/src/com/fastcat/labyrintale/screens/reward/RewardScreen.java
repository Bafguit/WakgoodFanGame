package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.screens.tutorial.TutorialScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class RewardScreen extends AbstractScreen {

    public PassRewardButton passButton;
    public Array<RewardItemButton> rewardButtons = new Array<>();
    public Array<AbstractReward> rewards;

    public RewardScreen(Array<AbstractReward> rewards) {
        setBg(FileHandler.getUi().get("REWARD"));
        cType = ControlPanel.ControlType.BASIC;
        this.rewards = rewards;
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for (int i = 0; i < rewards.size; i++) {
            float hf = (float) rewards.size / 2 - 0.5f;
            RewardItemButton temp = new RewardItemButton(rewards.get(i));
            temp.setPosition(w * (0.5f - (hf - i) * 0.065f) - temp.sWidth * 0.5f, 852 * InputHandler.scale);
            rewardButtons.add(temp);
        }
        passButton = new PassRewardButton(this);
    }

    @Override
    public void update() {
        for (RewardItemButton b : rewardButtons) {
            b.update();
        }
        passButton.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        for (RewardItemButton b : rewardButtons) {
            b.render(sb);
        }
        passButton.render(sb);
    }

    @Override
    public void show() {
        Labyrintale.getBaseScreen().cType = ControlPanel.ControlType.BASIC;
        AbstractLabyrinth.cPanel.update();
        if (SettingHandler.setting.rewardTutorial) {
            Labyrintale.openTutorial(TutorialScreen.TutorialType.REWARD);
            SettingHandler.setting.rewardTutorial = false;
            SettingHandler.save();
        }
    }

    @Override
    public void hide() {}

    @Override
    public void dispose() {
        for (RewardItemButton t : rewardButtons) {
            t.dispose();
        }
    }
}
