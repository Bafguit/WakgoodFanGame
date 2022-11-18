package com.fastcat.labyrintale.screens.slotselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractPlayer;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.interfaces.GetRewardDone;
import com.fastcat.labyrintale.interfaces.GetSelectedSlot;
import com.fastcat.labyrintale.uis.BgImg;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class SlotSelectScreen extends AbstractScreen implements GetSelectedSlot {

  public BgImg bg = new BgImg();
  public SlotSelectText slotSelectText;
  public PlayerSlotIcon[] pIcons = new PlayerSlotIcon[4];
  public SlotButton[][] pPlayer;
  public GetSelectedSlot gets;
  public GetRewardDone rewardDone;
  public CancelUpgradeButton cancel;
  public SlotType type;

  public SlotSelectScreen(GetSelectedSlot gets, GetRewardDone rewardDone) {
    this(gets, SlotType.UPGRADE);
    this.rewardDone = rewardDone;
  }

  public SlotSelectScreen(GetSelectedSlot gets, SlotType type) {
    slotSelectText = new SlotSelectText();
    this.gets = gets;
    this.type = type;
    pPlayer = new SlotButton[4][3];
    float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
    int cnt = 0;
    for(int l = 0; l < 2; l++) {
      for (int k = 0; k < 2; k++) {
        AbstractPlayer p = AbstractLabyrinth.players[cnt];
        float bw = w * (0.15f + 0.46f * k);
        PlayerSlotIcon c = new PlayerSlotIcon(p);
        c.setPosition(bw - c.sWidth / 2, h * (0.69f - 0.275f * l));
        for (int i = 0; i < 3; i++) {
          SlotButton adv = new SlotButton(p, i, this);
          adv.setPosition(bw + w * 0.08f * (i + 1) - adv.sWidth / 2, h * (0.69f - 0.275f * l));
          pPlayer[cnt][i] = adv;
        }
        pIcons[cnt++] = c;
      }
    }
    cancel = new CancelUpgradeButton(this);
  }

  @Override
  public void update() {
    for(int i = 0; i < 4; i++) {
      pIcons[i].update();
      for(int j = 0; j < 3; j++) {
        pPlayer[i][j].update();
      }
    }
    slotSelectText.update();
    if(rewardDone != null) cancel.update();
  }

  @Override
  public void render(SpriteBatch sb) {
    bg.render(sb);
    for(int i = 0; i < 4; i++) {
      pIcons[i].render(sb);
      for(int j = 0; j < 3; j++) {
        pPlayer[i][j].render(sb);
      }
    }
    slotSelectText.render(sb);
    if(rewardDone != null) cancel.render(sb);
  }

  @Override
  public void show() {}

  @Override
  public void hide() {}

  @Override
  public void dispose() {
    for(int i = 0; i < 4; i++) {
      pIcons[i].dispose();
      for(int j = 0; j < 3; j++) {
        pPlayer[i][j].dispose();
      }
    }
  }

  @Override
  public void slotSelected(AbstractPlayer player, int index) {
    if (type == SlotType.UPGRADE) {
      player.upgradeSkill(index, 1);
    }
    gets.slotSelected(player, index);
    if(rewardDone != null) rewardDone.isRewardDone(true);
    Labyrintale.removeTempScreen(this);
  }

  private static class PlayerSlotIcon extends AbstractUI {

    private AbstractPlayer player;

    public PlayerSlotIcon(AbstractPlayer player) {
      super(FileHandler.getUi().get("BORDER_M"));
      this.player = player;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
      sb.setColor(Color.WHITE);
      if(player != null) sb.draw(player.img, x, y, sWidth, sHeight);
      sb.draw(img, x, y, sWidth, sHeight);
    }
  }

  public enum SlotType {
    UPGRADE
  }
}
