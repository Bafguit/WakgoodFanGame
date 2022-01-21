package com.fastcat.labyrintale.screens.shop;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractReward;
import com.fastcat.labyrintale.abstracts.AbstractUI;

import static com.fastcat.labyrintale.handlers.FileHandler.CHAR_SELECT;

public class ShopItemButton extends AbstractUI {

    private Sprite border = CHAR_SELECT;
    public AbstractReward reward;

    public ShopItemButton(AbstractReward re) {
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
