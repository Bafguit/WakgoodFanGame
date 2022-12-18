package com.fastcat.labyrintale.screens.achieve;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractItem;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.AchieveHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.KeyString;

public class AchieveIcon extends AbstractUI {

    public final AchieveHandler.Achievement achv;
    private final Sprite image;
    public AbstractItem.ItemRarity rarity;
    public int grade;

    public AchieveIcon(AchieveHandler.Achievement achv) {
        super(FileHandler.getUi().get("BORDER_P"));
        this.achv = achv;
        fontData = FontHandler.BIG_DESC;
        clickable = false;
        image = FileHandler.getAchvImg().get(achv);
        KeyString.KeyData data = StringHandler.achvString.get(achv);
        subs.add(new SubText(data.NAME, data.DESC));
    }

    @Override
    protected void updateButton() {
        grade = AchieveHandler.achvs.get(achv);
        if (grade == 1) rarity = AbstractItem.ItemRarity.BRONZE;
        else if (grade == 2) rarity = AbstractItem.ItemRarity.SILVER;
        else if (grade == 3) rarity = AbstractItem.ItemRarity.GOLD;
        else rarity = AbstractItem.ItemRarity.STARTER;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (grade == 0) sb.setColor(Color.DARK_GRAY);
        else sb.setColor(Color.WHITE);
        sb.draw(image, x, y, sWidth, sHeight);
        if (grade > 0) sb.setColor(AbstractItem.getRarityColor(rarity));
        sb.draw(img, x, y, sWidth, sHeight);
        sb.setColor(Color.WHITE);
    }

    @Override
    protected Array<SubText> getSubText() {
        return subs;
    }
}
