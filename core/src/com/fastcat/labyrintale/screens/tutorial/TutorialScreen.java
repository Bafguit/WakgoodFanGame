package com.fastcat.labyrintale.screens.tutorial;

import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.FileHandler;

public class TutorialScreen extends AbstractScreen {

    public TutorialType type;
    public NextPageButton nextPage;
    public PrePageButton close;
    public TutorialImg bg;
    public int max;
    public int index;
    private Array<Sprite> img;

    public TutorialScreen() {
        index = 0;
        nextPage = new NextPageButton(this);
        close = new PrePageButton(this);
        bg = new TutorialImg();
    }

    private static Array<Sprite> getImage(TutorialType type) {
        Array<Sprite> temp = new Array<>();
        if (type == TutorialType.CHARSELECT) {
            for (int i = 1; i <= 3; i++) {
                temp.add(FileHandler.getTutorialImg().get(type + "_" + i));
            }
        } else if (type == TutorialType.BATTLE) {
            for (int i = 1; i <= 3; i++) {
                temp.add(FileHandler.getTutorialImg().get(type + "_" + i));
            }
        } else if (type == TutorialType.WAY) {
            for (int i = 1; i <= 2; i++) {
                temp.add(FileHandler.getTutorialImg().get(type + "_" + i));
            }
        } else if (type == TutorialType.REWARD) {
            for (int i = 1; i <= 2; i++) {
                temp.add(FileHandler.getTutorialImg().get(type + "_" + i));
            }
        }
        return temp;
    }

    @Override
    public void update() {
        if (type != null) {
            if (type == TutorialType.BATTLE) {
                AbstractLabyrinth.cPanel.battlePanel.setEntity(AbstractLabyrinth.players[0]);
            }
            nextPage.update();
            close.update();
            bg.image = img.get(index);
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        if (type != null) {
            bg.render(sb);
            nextPage.render(sb);
            close.render(sb);
        }
    }

    @Override
    public void show() {}

    public void setType(TutorialType type) {
        this.type = type;
        img = getImage(type);
        max = img.size - 1;
        index = 0;
    }

    public enum TutorialType {
        CHARSELECT,
        BATTLE,
        WAY,
        REWARD
    }
}
