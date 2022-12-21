package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.uis.BgImg;
import com.fastcat.labyrintale.uis.control.ControlPanel;

import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class SettingScreen extends AbstractScreen {

    public BgImg bgImg;
    public OptionPanel panel;
    public CloseSettingButton close;
    public ReturnToMainButton main;
    public ExitGameButton exit;
    public GiveUpButton giveUp;
    public SelectionGroup screenMode;
    public SelectionGroup resolution;
    public SlideBarGroup volumeSfx;
    public SlideBarGroup volumeBgm;
    public CheckBoxGroup shake;
    public CheckBoxGroup fastMode;
    public boolean anim = false;
    private String[] res;
    private int[] width;
    private int[] height;
    private float alpha = 0;

    public SettingScreen() {
        float w = Gdx.graphics.getWidth(), h = 1440 * scale;
        bgImg = new BgImg(0);
        panel = new OptionPanel();
        panel.setY(h);
        cType = ControlPanel.ControlType.HIDE;
        close = new CloseSettingButton(this);
        close.setParent(panel);
        main = new ReturnToMainButton(this);
        main.setParent(panel);
        exit = new ExitGameButton(this);
        exit.setParent(panel);
        giveUp = new GiveUpButton(this);
        giveUp.setParent(panel);

        screenMode = new SelectionGroup(new String[] {"창 모드", "전체 화면", "전체 창 모드"}, 1163 * scale, 926 * scale);
        screenMode.index = SettingHandler.setting.screenMode;
        screenMode.setText();
        setResolution();
        screenMode.setParent(panel);
        resolution = new SelectionGroup(res, 1163 * scale, 854 * scale);
        resolution.item = width;
        resolution.item2 = height;
        int id = 0;
        for (int i = 0; i < resolution.item.length; i++) {
            if (resolution.item[i] == SettingHandler.setting.width) {
                id = i;
                break;
            }
        }
        resolution.index = id;
        resolution.setText();
        resolution.setParent(panel);
        volumeBgm = new SlideBarGroup(SettingHandler.setting.volumeBgm, 1163 * scale, 656 * scale);
        volumeBgm.setParent(panel);
        volumeSfx = new SlideBarGroup(SettingHandler.setting.volumeSfx, 1163 * scale, 584 * scale);
        volumeSfx.setParent(panel);
        shake = new CheckBoxGroup(SettingHandler.setting.shake, 1221 * scale, 381 * scale);
        shake.setParent(panel);
        fastMode = new CheckBoxGroup(SettingHandler.setting.fastMode, 1221 * scale, 309 * scale);
        fastMode.setParent(panel);
    }

    @Override
    public void update() {
        if (anim) {
            float h = 1440 * scale;
            if (Labyrintale.setting) {
                alpha += Labyrintale.tick * 5 * 0.8f;
                panel.y -= h * 5 * Labyrintale.tick;
                if (alpha >= 0.8f) {
                    alpha = 0.8f;
                }
                if (panel.y <= 0) {
                    panel.y = 0;
                    anim = false;
                }
            } else {
                alpha -= Labyrintale.tick * 5 * 0.8f;
                panel.y += h * 5 * Labyrintale.tick;
                if (alpha <= 0) {
                    alpha = 0;
                }
                if (panel.y >= h) {
                    panel.y = h;
                    anim = false;
                }
            }
            bgImg.img.setAlpha(alpha);
        }

        resolution.can = screenMode.index == 0;
        close.update();
        if (Labyrintale.labyrinth != null) {
            main.update();
            exit.update();
            giveUp.update();
        }
        screenMode.update();
        SettingHandler.setting.screenMode = screenMode.index;
        resolution.can = screenMode.index == 0;
        resolution.update();
        SettingHandler.setting.width = width[resolution.index];
        SettingHandler.setting.height = height[resolution.index];
        volumeSfx.update();
        SettingHandler.setting.volumeSfx = volumeSfx.getValue();
        volumeBgm.update();
        SettingHandler.setting.volumeBgm = volumeBgm.getValue();
        shake.update();
        SettingHandler.setting.shake = shake.getValue();
        fastMode.update();
        SettingHandler.setting.fastMode = fastMode.getValue();
    }

    @Override
    public void render(SpriteBatch sb) {
        bgImg.render(sb);
        panel.render(sb);
        screenMode.render(sb);
        resolution.render(sb);
        volumeSfx.render(sb);
        volumeBgm.render(sb);
        shake.render(sb);
        fastMode.render(sb);
        close.render(sb);
        if (Labyrintale.labyrinth != null) {
            main.render(sb);
            exit.render(sb);
            giveUp.render(sb);
        }
    }

    private void setResolution() {
        Graphics.DisplayMode dp = Gdx.graphics.getDisplayMode(Gdx.graphics.getMonitor());
        int mw = dp.width, mh = mw * 9 / 16;
        Array<String> s = new Array<>();
        Array<Integer> w = new Array<>(), h = new Array<>();
        s.add(mw + "x" + mh);
        w.add(mw);
        h.add(mh);
        if (mw > 3840) {
            s.add("3840x2160");
            w.add(3840);
            h.add(2160);
        }
        if (mw > 2560) {
            s.add("2560x1440");
            w.add(2560);
            h.add(1440);
        }
        if (mw > 1920) {
            s.add("1920x1080");
            w.add(1920);
            h.add(1080);
        }
        if (mw > 1600) {
            s.add("1600x900");
            w.add(1600);
            h.add(900);
        }
        if (mw > 1280) {
            s.add("1280x720");
            w.add(1280);
            h.add(720);
        }
        res = s.toArray(String.class);
        width = new int[w.size];
        height = new int[h.size];
        for (int i = 0; i < w.size; i++) {
            width[i] = w.get(i);
            height[i] = h.get(i);
        }
    }

    @Override
    public void show() {}

    @Override
    public void hide() {
        anim = false;
        alpha = 0;
        panel.setY(Gdx.graphics.getHeight());
        update();
        bgImg.img.setAlpha(0);
    }
}
