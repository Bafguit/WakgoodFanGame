package com.fastcat.labyrintale.screens.reward;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.BORDER;
import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

public class RewardItemButton extends AbstractUI {

    private Sprite border = BORDER;
    public AbstractReward reward;

    public RewardItemButton(AbstractReward re) {
        super(re.img);
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

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        if(!reward.isDone) {
            reward.takeReward();
            reward.isDone = true;
        }
    }
}
