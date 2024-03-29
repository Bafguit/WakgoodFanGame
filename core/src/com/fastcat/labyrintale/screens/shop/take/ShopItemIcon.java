package com.fastcat.labyrintale.screens.shop.take;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

public class ShopItemIcon extends AbstractUI {

    private static final FontHandler.FontData fontData = FontHandler.MAIN_MENU_SHADOW;
    private final Sprite cost = FileHandler.getUi().get("ENERGY_ORB");
    public ShopTakeScreen screen;
    public Sprite icon;
    public String text;

    public ShopItemIcon(ShopTakeScreen screen) {
        super(FileHandler.getUi().get("BORDER_M"));
        clickable = false;
        this.screen = screen;
        if (screen.type == ShopTakeScreen.TakeType.SKILL) {
            icon = screen.skill.img;
            text = "교체할 스킬을 선택하세요.";
        } else {
            icon = screen.item.img;
            text = "교체할 아이템을 선택하세요.";
        }
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            sb.setColor(Color.WHITE);

            FontHandler.renderCenter(
                    sb, fontData, text, x - sWidth, 1123 * InputHandler.scale, sWidth * 3f, sHeight);
            FontHandler.renderCenter(sb, fontData, "▼", x, 828 * InputHandler.scale, sWidth, sHeight);

            sb.draw(icon, x, y, sWidth, sHeight);
            if (screen.type == ShopTakeScreen.TakeType.SKILL) {
                sb.draw(img, x, y, sWidth, sHeight);
                sb.draw(cost, x - sWidth * 0.05f, y + sWidth * 0.65f, sWidth * 0.4f, sWidth * 0.4f);
                FontHandler.renderCenter(
                        sb,
                        FontHandler.CARD_BIG_DESC,
                        Integer.toString(screen.skill.cost),
                        x + sWidth * 0.05f,
                        y + sWidth * 0.85f,
                        sWidth * 0.2f,
                        sWidth * 0.2f);
            } else if (screen.type == ShopTakeScreen.TakeType.ITEM) {
                sb.setColor(screen.item.getRarityColor());
                sb.draw(img, x, y, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void updateButton() {
        if (over) {
            if (screen.type == ShopTakeScreen.TakeType.SKILL) {
                AbstractLabyrinth.cPanel.infoPanel.setInfo(screen.skill);
            } else {
                AbstractLabyrinth.cPanel.infoPanel.setInfo(screen.item);
            }
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        if (screen.type == ShopTakeScreen.TakeType.SKILL) {
            return screen.skill.key;
        } else {
            return screen.item.key;
        }
    }
}
