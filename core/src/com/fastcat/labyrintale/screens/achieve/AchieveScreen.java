package com.fastcat.labyrintale.screens.achieve;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.AchieveHandler;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.uis.BgImg;

public class AchieveScreen extends AbstractScreen {

    public AchvCloseButton close = new AchvCloseButton(this);
    public AchieveIcon[][] achvs = new AchieveIcon[7][3];
    private final AbstractUI.TempUI bg =
            new AbstractUI.TempUI(FileHandler.getUi().get("ACHIEVE"));
    private float alpha = 0;
    private boolean anim = false;
    private boolean isDown = true;
    private final BgImg bgImg;

    public AchieveScreen() {
        bg.setPosition(0, Gdx.graphics.getHeight());
        bgImg = new BgImg(0);
        int cnt = 0;
        AchieveHandler.Achievement[] a = AchieveHandler.Achievement.values();
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 7; j++) {
                AchieveIcon c = new AchieveIcon(a[cnt]);
                c.setPosition((516 + 226 * j) * scale, (785 - 214 * i) * scale);
                c.setParent(bg);
                achvs[j][i] = c;
                cnt++;
            }
        }
        close.setParent(bg);
    }

    @Override
    public void update() {
        if (anim) {
            float h = Gdx.graphics.getHeight();
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
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                achvs[i][j].update();
            }
        }
    }

    @Override
    public void render(SpriteBatch sb) {
        bgImg.render(sb);
        bg.render(sb);
        close.render(sb);
        for (int i = 0; i < 7; i++) {
            for (int j = 0; j < 3; j++) {
                achvs[i][j].render(sb);
            }
        }
    }

    public void close() {
        anim = true;
        isDown = false;
    }

    @Override
    public void show() {
        anim = true;
        isDown = true;
    }
}
