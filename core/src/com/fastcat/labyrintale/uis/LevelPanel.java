package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class LevelPanel extends AbstractUI {

    private final Sprite back = FileHandler.getUi().get("LEVEL_BACK");
    private final TempUI exp = new TempUI(FileHandler.getUi().get("LEVEL_EXP"));

    public LevelPanel() {
        super(FileHandler.getUi().get("LEVEL_LINE"));
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 1128 * scale);
        exp.setPosition(Gdx.graphics.getWidth() * 0.5f - exp.sWidth / 2, 1276 * scale);
        isPixmap = true;
        fontData = FontHandler.CARD_BIG_ORB;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        sb.draw(back, x, y, sWidth, sHeight);
        sb.draw(exp.img, exp.x, exp.y, 0, 0, exp.sWidth, exp.sHeight,
                Math.max(((float) AbstractLabyrinth.exp) / ((float) AbstractLabyrinth.maxExp), 0), 1, 0);
        sb.draw(img, x, y, sWidth, sHeight);

        FontHandler.renderCenter(sb, fontData, Integer.toString(AbstractLabyrinth.level),
                exp.x, exp.y + exp.sHeight / 2, exp.sWidth, exp.sHeight);
    }
}
