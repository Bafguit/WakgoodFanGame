package com.fastcat.labyrintale.screens.setting;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.uis.BgImg;

import java.util.HashMap;

public class SettingScreen extends AbstractScreen {

    private String[] res;
    private int[] width;
    private int[] height;
    public BgImg bgImg = new BgImg();
    public OptionPanel panel;
    public CloseSettingButton close;
    public SelectionGroup monitor;
    public SelectionGroup screenMode;
    public SelectionGroup resolution;
    public SlideBarGroup volumeSfx;
    public SlideBarGroup volumeBgm;
    public CheckBoxGroup shake;
    public CheckBoxGroup fastMode;
    public boolean changed = false;

    public SettingScreen() {
        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        panel = new OptionPanel();
        close = new CloseSettingButton(this);
        Graphics.Monitor[] mo = Gdx.graphics.getMonitors();
        String[] ms = new String[mo.length];
        int[] mi = new int[mo.length];
        for(int i = 0; i < mo.length; i++) {
            ms[i] = mo[i].name;
            mi[i] = i;
        }
        monitor = new SelectionGroup("모니터", ms, w * 0.15f, h * 0.6f);
        monitor.index = SettingHandler.setting.monitor;
        monitor.item = mi;
        monitor.setText();
        screenMode = new SelectionGroup("화면 모드", new String[]{"창 모드", "전체 화면", "전체 창 모드"}, w * 0.15f, h * 0.5f);
        screenMode.index = SettingHandler.setting.screenMode;
        screenMode.setText();
        setResolution();
        resolution = new SelectionGroup("해상도", res, w * 0.15f, h * 0.4f);
        resolution.item = width;
        resolution.item2 = height;
        int id = 0;
        for(int i = 0; i < resolution.item.length; i++) {
            if(resolution.item[i] == SettingHandler.setting.width) {
                id = i;
                break;
            }
        }
        resolution.index = id;
        resolution.setText();
        volumeBgm = new SlideBarGroup("배경음악", SettingHandler.setting.volumeBgm, w * 0.5f, h * 0.65f);
        volumeBgm.setText();
        volumeSfx = new SlideBarGroup("효과음", SettingHandler.setting.volumeSfx, w * 0.5f, h * 0.55f);
        volumeSfx.setText();
        shake = new CheckBoxGroup("화면 흔들림", SettingHandler.setting.shake, w * 0.5f, h * 0.45f);
        fastMode = new CheckBoxGroup("빠른 전투", SettingHandler.setting.fastMode, w * 0.5f, h * 0.35f);
    }

    @Override
    public void update() {
        resolution.can = screenMode.index == 0;
        close.update();
        monitor.update();
        screenMode.update();
        resolution.update();
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
        monitor.render(sb);
        screenMode.render(sb);
        resolution.render(sb);
        volumeSfx.render(sb);
        volumeBgm.render(sb);
        shake.render(sb);
        fastMode.render(sb);
        close.render(sb);
    }

    private void setResolution() {
        Graphics.DisplayMode dp = Gdx.graphics.getDisplayMode(Gdx.graphics.getMonitors()[monitor.getItem()]);
        int mw = dp.width, mh = mw * 9 / 16;
        Array<String> s = new Array<>();
        Array<Integer> w = new Array<>(), h = new Array<>();
        s.add(mw + "x" + mh);
        w.add(mw);
        h.add(mh);
        if(mw > 3840) {
            s.add("3840x2160");
            w.add(3840);
            h.add(2160);
        }
        if(mw > 2560) {
            s.add("2560x1440");
            w.add(2560);
            h.add(1440);
        }
        if(mw > 1920) {
            s.add("1920x1080");
            w.add(1920);
            h.add(1080);
        }
        if(mw > 1600) {
            s.add("1600x900");
            w.add(1600);
            h.add(900);
        }
        if(mw > 1280) {
            s.add("1280x720");
            w.add(1280);
            h.add(720);
        }
        res = s.toArray(String.class);
        width = new int[w.size];
        height = new int[h.size];
        for(int i = 0; i < w.size; i++) {
            width[i] = w.get(i);
            height[i] = h.get(i);
        }
    }

    @Override
    public void show() {
        changed = false;
    }
}
