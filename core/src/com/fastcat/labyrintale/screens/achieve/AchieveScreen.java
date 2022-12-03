package com.fastcat.labyrintale.screens.achieve;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.AchieveHandler;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class AchieveScreen extends AbstractScreen {

    private AbstractUI.TempUI bg = new AbstractUI.TempUI(FileHandler.getUi().get("ACHIEVE"));
    public AchvCloseButton close = new AchvCloseButton(this);
    public AchieveIcon[][] achvs = new AchieveIcon[7][3];

    public AchieveScreen() {
        bg.setPosition(0, 0);
        int cnt = 0;
        AchieveHandler.Achievement[] a = AchieveHandler.Achievement.values();
        for(int i = 0; i < 3; i++) {
            for(int j = 0; j < 7; j++) {
                AchieveIcon c = new AchieveIcon(a[cnt]);
                c.setPosition((516 + 226 * j) * scale, (785 - 214 * i) * scale);
                achvs[j][i] = c;
                cnt++;
            }
        }
    }

    @Override
    public void update() {
        close.update();
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 3; j++) {
                achvs[i][j].update();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);
        close.render(sb);
        for(int i = 0; i < 7; i++) {
            for(int j = 0; j < 3; j++) {
                achvs[i][j].render(sb);
            }
        }
    }

    @Override
    public void show() {

    }
}
