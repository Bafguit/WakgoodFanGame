package com.fastcat.labyrintale.screens.riskselect;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractScreen;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.RestrictionHandler.RiskType;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.uis.BgImg;

import java.util.HashMap;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class RiskSelectScreen extends AbstractScreen {

    private final BgImg bg = new BgImg();
    private CloseRiskScreenButton close;
    private DiffView diffView;
    public RiskIcon[] selected;
    public HashMap<RiskType, RiskButton[]> risk;

    public RiskSelectScreen() {
        risk = new HashMap<>();
        selected = new RiskIcon[10];
        diffView = new DiffView();

        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight();
        for(int i = 0; i < 10; i++) {
            RiskType t = RiskType.values()[i];
            RiskButton[] bs = new RiskButton[3];
            for(int j = 0; j < 3; j++) {
                RiskButton b = new RiskButton(t, j + 1);
                b.setPosition((w * 0.1f) + (w * 0.085f * i), h * 0.5f - b.sHeight * 0.5f - h * 0.1f * j);
                bs[j] = b;
            }
            risk.put(RiskType.values()[i], bs);
            selected[i] = new RiskIcon(t, SettingHandler.setting.risk.get(t));
        }
        close = new CloseRiskScreenButton(this);
    }

    @Override
    public void update() {
        for(RiskType t : RiskType.values()) {
            for(int j = 0; j < 3; j++) {
                risk.get(t)[j].update();
            }
        }

        float w = Gdx.graphics.getWidth(), h = Gdx.graphics.getHeight(), x = w * 0.1f;

        for(int i = 0; i < 10; i++) {
            RiskIcon c = selected[i];
            int d = SettingHandler.setting.risk.get(c.getType());
            c.setDiff(d);
            c.update();
            if(d > 0) {
                c.setPosition(x, h * 0.7f - c.sHeight * 0.5f);
                x += w * 0.085;
            } else c.setPosition(-10000, -10000);
        }

        close.update();
        diffView.update();
    }

    @Override
    public void render(SpriteBatch sb) {
        bg.render(sb);

        for(RiskType t : RiskType.values()) {
            for(int j = 0; j < 3; j++) {
                risk.get(t)[j].render(sb);
            }
        }

        for(int i = 0; i < 10; i++) {
            selected[i].render(sb);
        }

        close.render(sb);
        diffView.render(sb);
    }

    @Override
    public void show() {

    }

    private static class DiffView extends AbstractUI {

        public DiffView() {
            super(FileHandler.getUi().get("NEXT"));
            setPosition(Gdx.graphics.getWidth() * 0.1f, Gdx.graphics.getHeight() * 0.9f);
            fontData = FontHandler.MAIN_MENU;
            text = "0";
        }

        @Override
        protected void updateButton() {
            int s = 0;
            for(Integer i : SettingHandler.setting.risk.values()) {
                s += i;
            }
            text = Integer.toString(s);
        }

        @Override
        protected void renderUi(SpriteBatch sb) {
            if(enabled) {
                sb.setColor(Color.WHITE);
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }
}
