package com.fastcat.labyrintale.screens.riskselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.RestrictionHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;

public class RiskIcon extends AbstractUI {

    private Sprite[] icon;
    private RestrictionHandler.RiskType type;
    private int diff;

    public RiskIcon(RestrictionHandler.RiskType type, int a) {
        super(FileHandler.getUi().get("BORDER"));
        icon = new Sprite[4];
        icon[0] = img;
        for(int i = 1; i < 4; i++) {
            icon[i] = FileHandler.getRiskImg().get(type.name() + "_" + i);
        }
        this.type = type;
        diff = a;
    }

    public RestrictionHandler.RiskType getType() {
        return type;
    }

    public void setDiff(int a) {
        diff = a;
    }

    @Override
    protected void updateButton() {
        overable = diff > 0;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if(overable) {
            sb.setColor(Color.WHITE);
            sb.draw(icon[diff], x, y, sWidth, sHeight);
        }
    }

    @Override
    protected void onClick() {
        SettingHandler.setting.risk.replace(type, 0);
    }
}
