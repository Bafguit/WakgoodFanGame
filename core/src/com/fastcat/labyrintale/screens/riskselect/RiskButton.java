package com.fastcat.labyrintale.screens.riskselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.RestrictionHandler;
import com.fastcat.labyrintale.handlers.SettingHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.RiskString;

public class RiskButton extends AbstractUI {

    private RiskString.RiskData data;

    private Sprite icon;
    private RestrictionHandler.RiskType type;
    private boolean selected;
    private int diff;

    public RiskButton(RestrictionHandler.RiskType type, int diff) {
        super(FileHandler.getUi().get("BORDER"));
        icon = FileHandler.getRiskImg().get(type.name() + "_" + diff);
        this.type = type;
        this.diff = diff;
        data = StringHandler.riskString.get(type.name());
        subTexts = new Array<>();
        if(diff > 0) {
            subTexts.add(new SubText(data.NAME + " " + "I".repeat(diff), data.DESC[diff - 1]));
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return subTexts;
    }

    @Override
    protected void updateButton() {
        selected = SettingHandler.setting.risk.get(type) == diff;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if(selected) sb.setColor(Color.DARK_GRAY);
        else if (over) sb.setColor(Color.WHITE);
        else sb.setColor(Color.LIGHT_GRAY);
        sb.draw(icon, x, y, sWidth, sHeight);
    }

    @Override
    protected void onClick() {
        if(selected) SettingHandler.setting.risk.replace(type, 0);
        else SettingHandler.setting.risk.replace(type, diff);
    }
}
