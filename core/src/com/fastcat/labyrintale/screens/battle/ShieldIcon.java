package com.fastcat.labyrintale.screens.battle;

import static com.fastcat.labyrintale.handlers.FontHandler.BLOCK;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractEntity;
import com.fastcat.labyrintale.abstracts.AbstractStatus;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.KeyString;

public class ShieldIcon extends AbstractUI {

    public AbstractStatus status;
    public AbstractEntity e;
    public int amount = 0;

    public ShieldIcon(AbstractEntity e) {
        super(FileHandler.getUi().get("SHIELD"));
        fontData = BLOCK;
        overable = false;
        clickable = false;
        isPixmap = true;
        this.e = e;
        KeyString.KeyData data = StringHandler.keyString.get("Block");
        subs.add(new SubText(data.NAME, data.DESC));
    }

    @Override
    protected Array<SubText> getSubText() {
        return subs;
    }

    @Override
    protected void updateButton() {
        overable = e.block > 0;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (overable) {
            sb.setColor(Color.WHITE);
            sb.draw(img, x, y, sWidth, sHeight);
            renderKeywordCenter(sb, fontData, Integer.toString(e.block), x, y + sHeight * 0.5f, sWidth, sHeight);
        }
    }
}
