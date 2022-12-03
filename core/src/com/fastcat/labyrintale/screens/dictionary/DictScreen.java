package com.fastcat.labyrintale.screens.dictionary;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.*;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class DictScreen extends AbstractScreen {

    private AbstractUI.TempUI bg = new AbstractUI.TempUI(FileHandler.getUi().get("DICT"));
    public DictType type;
    public DictCloseButton close;
    public DictCharTabButton charTab = new DictCharTabButton(this);
    public DictItemTabButton itemTab = new DictItemTabButton(this);
    public DictCharButton cSelected;
    public DictItemRarityButton iSelected;
    public DictItemRarityButton[] items = new DictItemRarityButton[6];
    public DictCharButton[] chars = new DictCharButton[8];

    public DictScreen() {
        type = DictType.CHAR;
        close = new DictCloseButton(this);
        bg.setPosition(0, 0);
        AbstractPlayer.PlayerClass[] cls = AbstractPlayer.PlayerClass.values();
        for(int i = 0; i < 8; i++) {
            DictCharButton c = new DictCharButton(AbstractLabyrinth.getPlayerInstance(cls[i]), this);
            c.setPosition((464 + 214 * i) * scale, 970f * scale - c.sHeight / 2);
            chars[i] = c;
        }
        cSelected = chars[0];

        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        AbstractItem.ItemRarity[] rare = AbstractItem.ItemRarity.values();
        for(int i = 0; i < 6; i++) {
            DictItemRarityButton b = new DictItemRarityButton(this, rare[i + 2]);
            b.setPosition(w * 0.25f + w * 0.1f * i - b.sWidth / 2, 971.3f * scale - b.sHeight / 2);
            items[i] = b;
        }
        iSelected = items[0];
    }

    @Override
    public void update() {
        close.update();
        charTab.update();
        itemTab.update();
        if(type == DictType.CHAR) {
            for (int i = 0; i < 8; i++) {
                DictCharButton b = chars[i];
                if(cSelected != null) b.group.up.checked = cSelected.group.up.checked;
                b.update();
            }
        } else if(type == DictType.ITEM) {
            for(int i = 0; i < 6; i++) {
                items[i].update();
            }
        }
        if(InputHandler.cancel) {
            Labyrintale.removeTempScreen(this);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        close.render(sb);
        charTab.render(sb);
        itemTab.render(sb);
        if(type == DictType.CHAR) {
            for (int i = 0; i < 8; i++) {
                chars[i].render(sb);
            }
        } else if(type == DictType.ITEM) {
            for(int i = 0; i < 6; i++) {
                items[i].render(sb);
            }
        }
    }

    @Override
    public void show() {
        type = DictType.CHAR;
    }

    public enum DictType {
        CHAR, ITEM
    }
}
