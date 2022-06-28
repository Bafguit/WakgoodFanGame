package com.fastcat.labyrintale.screens.expselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.rewards.ExpReward;

public class ExpButton extends AbstractUI {

    private final Sprite border = FileHandler.ui.get("BORDER_M");
    public ExpReward.ExpType type;
    public ExpSelectScreen select;
    public String name;
    public String desc;

    public ExpButton(ExpReward.ExpType type, ExpSelectScreen select) {
        super(getImage(type));
        this.type = type;
        this.select = select;
        name = getName(type);
        desc = getDesc(type);
    }

    private static Sprite getImage(ExpReward.ExpType type) { //TODO 이미지 바꾸기
        if(type == ExpReward.ExpType.SKILL_SLOT) return FileHandler.ui.get("DECK");
        else if(type == ExpReward.ExpType.HEAL) return FileHandler.ui.get("DECK");
        else if(type == ExpReward.ExpType.MAX_HEALTH) return FileHandler.ui.get("DECK");
        else return FileHandler.ui.get("DECK");
    }

    private static String getName(ExpReward.ExpType type) {
        if(type == ExpReward.ExpType.SKILL_SLOT) return "슬롯 강화";
        else if(type == ExpReward.ExpType.HEAL) return "회복";
        else if(type == ExpReward.ExpType.MAX_HEALTH) return "최대 체력 증가";
        else return "소생";
    }

    private static String getDesc(ExpReward.ExpType type) {
        if(type == ExpReward.ExpType.SKILL_SLOT) return "스킬 슬롯을 하나 선택해 강화합니다.";
        else if(type == ExpReward.ExpType.HEAL) return "체력을 4 회복합니다.";
        else if(type == ExpReward.ExpType.MAX_HEALTH) return "최대 체력이 2 증가합니다. (회복은 하지 않습니다.)";
        else return "사망한 플레이어를 되살립니다.";
    }

    @Override
    protected void updateButton() {

    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if (select.selected == this || over) sb.setColor(Color.WHITE);
            else sb.setColor(Color.LIGHT_GRAY);
            sb.draw(img, x, y, sWidth, sHeight);
            sb.draw(border, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);
        }
    }

    @Override
    protected void onOver() {

    }

    @Override
    protected void onClick() {
        select.selected = this;
    }
}
