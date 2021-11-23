package com.fastcat.labyrintale.buttons;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.OrthographicCamera;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractButton;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.handlers.FileHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public class CardUIButton extends AbstractButton {

    //TODO 카드 크기는 640 * 1024

    private static final float IMG_Y = 556;
    private static final float NAME_Y = 940;
    private static final float COND_Y = 396;
    private static final float DESC_Y = 48;
    private static final float IMG_HEIGHT = 360;
    private static final float NAME_HEIGHT = 60;
    private static final float COND_HEIGHT = 136;
    private static final float DESC_HEIGHT = 324;
    private static final float INT_BORDER = 48;
    private static final float INT_ELEMENT = 24;

    private float stx;
    private float sty;
    private float angle = 0.0f;

    public boolean isStatic = false;
    public float rotatedX;
    public float rotatedY;
    public Texture orb;
    public Texture smallOrb;
    public Texture cardBg;
    public Texture cardImg;
    public Texture cardBorder;
    public FontData costFontData;
    public FontData nameFontData;
    public FontData descFontData;
    public AbstractSkill card;

    public CardUIButton(AbstractSkill card) {
        super(FileHandler.CARD_BG);
        this.card = card;
        this.cardBg = FileHandler.CARD_BG;
        this.costFontData = CARD_BIG_ORB.cpy();
        this.nameFontData = CARD_BIG_NAME.cpy();
        this.descFontData = CARD_BIG_DESC.cpy();
        this.trackable = true;

        this.setPosition(400, 100);
        stx = 400;
        sty = 100;
    }

    @Override
    protected void updateButton() {
        if(!clicking && isStatic) {
            setPosition(stx, sty);
        }
    }

    @Override
    protected void onClicking() {
        trackCursor(true);
    }

    @Override
    public void setScale(float scale) {
        super.setScale(scale);
        costFontData.scale = uiScale;
        nameFontData.scale = uiScale;
        descFontData.scale = uiScale;
    }

    @Override
    public void render(SpriteBatch sb) {
        if(enabled) {
            OrthographicCamera tc = Labyrintale.camera;
            float a = tracking ? 0 : angle;
            tc.rotate(-a);
            tc.update();
            sb.setProjectionMatrix(tc.combined);
            sb.setColor(Color.WHITE);
            Vector2 vc = getVectorC(x + sWidth / 2, y + sHeight / 2, a);
            rotatedX = vc.x - sWidth / 2;
            rotatedY = vc.y - sHeight / 2;
            sb.draw(img, rotatedX, rotatedY, sWidth, sHeight);
            /** 여기에 카드 이미지 입력 */
            renderCardCenter(sb, card, descFontData, card.cond,
                    rotatedX + INT_BORDER * scale * uiScale, rotatedY + (COND_Y + COND_HEIGHT / 2) * scale * uiScale,
                    sWidth - INT_BORDER * 2 * scale * uiScale, COND_HEIGHT * scale * uiScale);
            renderCardCenter(sb, card, descFontData, card.desc,
                    rotatedX + INT_BORDER * scale * uiScale, rotatedY + (DESC_Y + DESC_HEIGHT / 2) * scale * uiScale,
                    sWidth - INT_BORDER * 2 * scale * uiScale, DESC_HEIGHT * scale * uiScale);
            renderCenter(sb, nameFontData, card.name,
                    rotatedX + INT_BORDER * scale * uiScale, rotatedY + (NAME_Y + NAME_HEIGHT / 2) * scale * uiScale,
                    sWidth - INT_BORDER * 2 * scale * uiScale, NAME_HEIGHT * scale * uiScale);
            tc.rotate(a);
        }
    }

    public void setStaticPos(float x, float y) {
        stx = x;
        sty = y;
    }

    public void setAngle(float angle) {
        this.angle = angle;
    }

    public void rotate(float angle) {
        this.angle += angle;
    }

    private Vector2 getVectorC (float x, float y, float angle) {
        Vector2 pointA = new Vector2(x, y);
        Vector2 pointB = new Vector2(Gdx.graphics.getWidth() / 2, Gdx.graphics.getHeight() / 2);
        return pointA.sub(pointB).rotateDeg(angle).add(pointB);
    }
}
