package com.fastcat.labyrintale.uis;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Array;
import com.fastcat.labyrintale.abstracts.AbstractLabyrinth;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import com.fastcat.labyrintale.handlers.StringHandler;
import com.fastcat.labyrintale.strings.KeyString;

import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

public class BleakView extends AbstractUI {

    private final AbstractUI B_0, B_20, B_40, B_60, B_80, B_100;

    public BleakView() {
        super(FileHandler.getUi().get("BLEAK"));
        setPosition(Gdx.graphics.getWidth() * 0.5f - sWidth * 0.5f, Gdx.graphics.getHeight() * 0.9f - sHeight * 0.5f);
        B_0 = new TempUI(FileHandler.getUi().get("BLEAK_0"));
        B_20 = new TempUI(FileHandler.getUi().get("BLEAK_20"));
        B_40 = new TempUI(FileHandler.getUi().get("BLEAK_40"));
        B_60 = new TempUI(FileHandler.getUi().get("BLEAK_60"));
        B_80 = new TempUI(FileHandler.getUi().get("BLEAK_80"));
        B_100 = new TempUI(FileHandler.getUi().get("BLEAK_100"));
        fontData = FontHandler.BLEAK;
        subDown = true;
        KeyString.KeyData k = StringHandler.keyString.get("Bleak");
        subs.add(new SubText(k.NAME, k.DESC));
    }

    @Override
    public void updateButton() {
        if(over) {
            AbstractLabyrinth.cPanel.infoPanel.setInfo("음산함", getBleakDesc(AbstractLabyrinth.bleak));
        }
    }

    @Override
    protected Array<SubText> getSubText() {
        return subs;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        sb.setColor(Color.WHITE);
        float x = Gdx.graphics.getWidth() * 0.5f, y = Gdx.graphics.getHeight() * 0.9f;
        if(AbstractLabyrinth.bleak < 20) sb.draw(B_0.img, x - B_0.sWidth * 0.5f, y - B_0.sHeight * 0.5f, B_0.sWidth, B_0.sHeight);
        else if(AbstractLabyrinth.bleak < 40) sb.draw(B_20.img, x - B_20.sWidth * 0.5f, y - B_20.sHeight * 0.5f, B_20.sWidth, B_20.sHeight);
        else if(AbstractLabyrinth.bleak < 60) sb.draw(B_40.img, x - B_40.sWidth * 0.5f, y - B_40.sHeight * 0.5f, B_40.sWidth, B_40.sHeight);
        else if(AbstractLabyrinth.bleak < 80) sb.draw(B_60.img, x - B_60.sWidth * 0.5f, y - B_60.sHeight * 0.5f, B_60.sWidth, B_60.sHeight);
        else if(AbstractLabyrinth.bleak < 100) sb.draw(B_80.img, x - B_80.sWidth * 0.5f, y - B_80.sHeight * 0.5f, B_80.sWidth, B_80.sHeight);
        else sb.draw(B_100.img, x - B_100.sWidth * 0.5f, y - B_100.sHeight * 0.5f, B_100.sWidth, B_100.sHeight);
        sb.draw(img, this.x, this.y, sWidth, sHeight);
        renderKeywordCenter(sb, fontData, Integer.toString(AbstractLabyrinth.bleak), this.x, this.y + sHeight * 0.5f, sWidth, sHeight);
    }

    private static String getBleakDesc(int bleak) {
        String s = "";
        if(bleak < 20) s += "아무런 변화가 없습니다.";
        else {
            if (bleak < 40) s += "미지 선택지에서 전투가 등장할 확률이 증가합니다.";
            if (bleak >= 40 && bleak < 60) s += "\n미지 선택지에서 휴식이 등장하지 않습니다.";
            if (bleak >= 60 && bleak < 80) s += "\n지도와 길의 정보가 가려지고 길의 순서가 무작위로 변경됩니다.";
            if (bleak >= 80 && bleak < 100) s += "\n";
            if (bleak == 100) s += "\n적의 행동이 보이지 않게 됩니다.";
        }
        return s;
    }
}
