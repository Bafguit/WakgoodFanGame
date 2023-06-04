package com.fastcat.labyrintale.uis;

import static com.fastcat.labyrintale.handlers.FontHandler.BORDER_40;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;

public class LevelPanel extends AbstractUI {

    private final Sprite back = FileHandler.getUi().get("LEVEL_BACK");
    private final TempUI exp = new TempUI(FileHandler.getUi().get("LEVEL_EXP"));
    private final float fx;
    private final float fy;
    private final float tx;

    public LevelPanel() {
        super(FileHandler.getUi().get("LEVEL_LINE"));
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth / 2, 1128 * scale);
        exp.setPosition(Gdx.graphics.getWidth() * 0.5f - exp.sWidth / 2, 1276 * scale);
        isPixmap = true;
        clickable = false;
        fontData = FontHandler.CARD_BIG_ORB;
        fx = x + 403 * scale;
        fy = y + sHeight - 230 * scale;
        tx = x + 669 * scale;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        sb.draw(back, x, y, sWidth, sHeight);
        sb.draw(
                exp.img,
                exp.x,
                exp.y,
                0,
                0,
                exp.sWidth,
                exp.sHeight,
                Math.max(((float) AbstractLabyrinth.exp) / ((float) AbstractLabyrinth.maxExp), 0),
                1,
                0);
        sb.draw(img, x, y, sWidth, sHeight);
        FontHandler.renderLineLeft(
                sb, BORDER_40, AbstractLabyrinth.currentFloor.floorNum + "층", fx, fy, 200 * scale, fy);
        FontHandler.renderLineLeft(
                sb, BORDER_40, AbstractLabyrinth.minute + ":" + AbstractLabyrinth.second, tx, fy, 200 * scale, fy);

        FontHandler.renderCenter(
                sb,
                fontData,
                Integer.toString(AbstractLabyrinth.level),
                exp.x,
                exp.y + exp.sHeight / 2,
                exp.sWidth,
                exp.sHeight);
    }
}
