package com.fastcat.labyrintale.screens.healselect;

import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.rewards.HealReward;
import com.fastcat.labyrintale.strings.KeyString;

public class HealButton extends AbstractUI {

    private final Sprite border = FileHandler.getUi().get("BORDER_M");
    public HealReward.HealType type;
    public HealSelectScreen select;
    public String name;
    public String desc;

    public HealButton(HealReward.HealType type, HealSelectScreen select) {
        super(getImage(type));
        this.type = type;
        this.select = select;
        name = getName(type);
        desc = getDesc(type);
    }

    private static Sprite getImage(HealReward.HealType type) { //TODO 이미지 바꾸기
        if (type == HealReward.HealType.HEAL) return FileHandler.getUi().get("DECK");
        else if (type == HealReward.HealType.MAX_HEALTH) return FileHandler.getUi().get("DECK");
        else return FileHandler.getUi().get("DECK");
    }

    private static String getName(HealReward.HealType type) {
        if (type == HealReward.HealType.HEAL) return "회복";
        else if (type == HealReward.HealType.MAX_HEALTH) return "최대 체력 증가";
        else return "소생";
    }

    private static String getDesc(HealReward.HealType type) {
        if (type == HealReward.HealType.HEAL) return "체력을 4 회복합니다.";
        else if (type == HealReward.HealType.MAX_HEALTH) return "최대 체력이 2 증가합니다. (회복은 하지 않습니다.)";
        else return "사망한 플레이어를 되살립니다.";
    }

    @Override
    protected void updateButton() {
        if(over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo(name, desc);
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return subs;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!clickable) sb.setColor(Color.DARK_GRAY);
            else if (select.selected == this || over) sb.setColor(Color.WHITE);
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