package com.fastcat.labyrintale.screens.dictionary;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.BgImg;

public class DictScreen extends AbstractScreen {

    public DictType type;
    public DictCloseButton close;
    public DictCharTabButton charTab = new DictCharTabButton(this);
    public DictItemTabButton itemTab = new DictItemTabButton(this);
    public DictCharButton cSelected;
    public DictItemRarityButton iSelected;
    public DictItemRarityButton[] items = new DictItemRarityButton[6];
    public DictCharButton[] chars = new DictCharButton[8];
    private final AbstractUI.TempUI bg =
            new AbstractUI.TempUI(FileHandler.getUi().get("DICT"));
    private float alpha = 0;
    private boolean anim = false;
    private boolean isDown = true;
    private final BgImg bgImg;

    public DictScreen() {
        setBg(FileHandler.getBg().get("BG_DICT"));
        type = DictType.CHAR;
        bgImg = new BgImg(0);
        close = new DictCloseButton();
        close.setParent(bg);
        bg.setPosition(0, 0);
        AbstractPlayer.PlayerClass[] cls = AbstractPlayer.PlayerClass.values();
        for (int i = 0; i < 8; i++) {
            DictCharButton c = new DictCharButton(AbstractLabyrinth.getPlayerInstance(cls[i]), this);
            c.setPosition(Gdx.graphics.getWidth() * (0.22f + 0.08f * i) - c.sWidth / 2, 140 * scale);
            c.setParent(bg);
            chars[i] = c;
        }
        cSelected = chars[0];

        float w = Gdx.graphics.getWidth();
        AbstractItem.ItemRarity[] rare = AbstractItem.ItemRarity.values();
        for (int i = 0; i < 6; i++) {
            DictItemRarityButton b = new DictItemRarityButton(this, rare[i + 2]);
            b.setPosition(w * 0.25f + w * 0.1f * i - b.sWidth / 2, scale * 205);
            b.setParent(bg);
            items[i] = b;
        }
        iSelected = items[0];

        charTab.setParent(bg);
        itemTab.setParent(bg);

        close.update();
        charTab.update();
        itemTab.update();
        if (type == DictType.CHAR) {
            for (int i = 0; i < 8; i++) {
                DictCharButton b = chars[i];
                if (cSelected != null) b.group.up.checked = cSelected.group.up.checked;
                b.update();
            }
        } else if (type == DictType.ITEM) {
            for (int i = 0; i < 6; i++) {
                items[i].update();
            }
        }
    }

    @Override
    public void update() {
        if (anim) {
            float h = 1440 * InputHandler.scale;
            if (isDown) {
                alpha += Labyrintale.tick * 5 * 0.8f;
                bg.y -= h * 5 * Labyrintale.tick;
                if (alpha >= 0.8f) {
                    alpha = 0.8f;
                }
                if (bg.y <= 0) {
                    bg.y = 0;
                    anim = false;
                }
            } else {
                alpha -= Labyrintale.tick * 5 * 0.8f;
                bg.y += h * 5 * Labyrintale.tick;
                if (alpha <= 0) {
                    alpha = 0;
                }
                if (bg.y >= h) {
                    bg.y = h;
                    anim = false;
                    Labyrintale.removeTempScreen(this);
                }
            }
            bgImg.img.setAlpha(alpha);
        } else if (InputHandler.cancel) {
            close();
        }
        close.update();
        charTab.update();
        itemTab.update();
        if (type == DictType.CHAR) {
            for (int i = 0; i < 8; i++) {
                DictCharButton b = chars[i];
                if (cSelected != null) b.group.up.checked = cSelected.group.up.checked;
                b.update();
            }
        } else if (type == DictType.ITEM) {
            for (int i = 0; i < 6; i++) {
                items[i].update();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        //bgImg.render(sb);
        //bg.render(sb);
        close.render(sb);
        charTab.render(sb);
        itemTab.render(sb);
        if (type == DictType.CHAR) {
            for (int i = 0; i < 8; i++) {
                chars[i].render(sb);
            }
        } else if (type == DictType.ITEM) {
            for (int i = 0; i < 6; i++) {
                items[i].render(sb);
            }
        }
    }

    public void close() {
        //anim = true;
        //isDown = false;
    }

    @Override
    public void show() {
        //anim = true;
        //isDown = true;
        type = DictType.CHAR;
    }

    public enum DictType {
        CHAR,
        ITEM
    }
}
