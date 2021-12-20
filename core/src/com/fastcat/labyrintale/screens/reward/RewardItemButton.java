package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen;
import com.fastcat.labyrintale.screens.deckview.DeckViewScreen.InfoType;

import java.util.Objects;

import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;
import static com.fastcat.labyrintale.handlers.FontHandler.CARD_BIG_DESC;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class RewardItemButton extends AbstractUI {

    private Sprite border = CHAR_SELECT;
    public AbstractReward reward;

    public RewardItemButton(AbstractReward re) {
        super(getImg(re.type));
        this.reward = re;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            if(reward.isDone) sb.setColor(Color.DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void updateButton() {

    }

    private static Sprite getImg(AbstractReward.RewardType type) {
        switch (type) {
            default:
                return FileHandler.REWARD_CARD;
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {

    }
}
