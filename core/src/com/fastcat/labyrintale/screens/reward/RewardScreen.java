package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.uis.control.ControlPanel;

public class RewardScreen extends AbstractScreen {

  public RewardTypeText rewardTypeText;
  public PassRewardButton passButton;
  public Array<RewardItemButton> rewardButtons = new Array<>();
  public Array<AbstractReward> rewards;
  public RewardScreenType sType;

  public RewardScreen(RewardScreenType type, Array<AbstractReward> rewards) {
    cType = ControlPanel.ControlType.BASIC;
    this.rewards = rewards;
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    for (int i = 0; i < rewards.size; i++) {
      float hf = (float) rewards.size / 2 - 0.5f;
      RewardItemButton temp = new RewardItemButton(rewards.get(i));
      temp.setPosition(
          w * (0.75f - (hf - i) * 0.065f) - temp.sWidth * 0.5f, h * 0.7f - temp.sHeight * 0.5f);
      rewardButtons.add(temp);
    }
    rewardTypeText = new RewardTypeText(type);
    passButton = new PassRewardButton(this);
    sType = type;
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
    rewardTypeText.render(sb);
    for (RewardItemButton b : rewardButtons) {
      b.render(sb);
    }
    passButton.render(sb);
  }

  @Override
  public void show() {
    Labyrintale.getBaseScreen().cType = ControlPanel.ControlType.BASIC;
  }

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    for (RewardItemButton t : rewardButtons) {
      t.dispose();
    }
  }

  public enum RewardScreenType {
    VICTORY,
    REST,
    CHEST,
    EVENT
  }
}
