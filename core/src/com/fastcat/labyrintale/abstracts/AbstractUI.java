package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.fastcat.labyrintale.handlers.LogHandler;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.generate;
import static com.fastcat.labyrintale.handlers.InputHandler.*;

public abstract class AbstractUI implements ScaleUpdateListener {

    private static final SpriteBatch uib = new SpriteBatch();

    protected LogHandler logger = new LogHandler(this.getClass().getName());
    protected Texture img;
    protected String text;
    protected FontData fontData;
    protected float x;
    protected float sx;
    protected float cx;
    protected float y;
    protected float sy;
    protected float cy;
    protected float width;
    protected float height;
    public float sWidth;
    public float sHeight;
    protected int fs;
    protected FontType ft;
    protected boolean fb;
    protected boolean over;
    protected boolean enabled;
    protected boolean showImg = true;

    public float uiScale;
    public boolean clicked;
    public boolean clicking;
    public boolean trackable = false;
    public boolean tracking = false;

    public AbstractUI(String imgPath) {
        this(imgPath, -10000, -10000);
    }

    public AbstractUI(Texture texture) {
        this(texture, -10000, -10000);
    }

    public AbstractUI(String imgPath, float x, float y) {
        this(new Texture(imgPath), x, y);
    }

    public AbstractUI(Texture texture, float x, float y) {
        this(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    public AbstractUI(String imgPath, float x, float y, float width, float height) {
        this(new Texture(imgPath), x, y, width, height);
    }

    public AbstractUI(Texture img, float x, float y, float width, float height) {
        this.img = img;
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sWidth = this.width * scale;
        sHeight = this.height * scale;
        sx = this.x;
        sy = this.y;
        over = false;
        enabled = true;
        uiScale = 1.0f;
        clicked = false;
        clicking = false;
    }

    public void update() {
        //x = sx * scale;
        //y = sy * scale;
        sWidth = width * scale * uiScale;
        sHeight = height * scale * uiScale;
        if(clicked) clicked = false;
        if(over) over = false;
        if(tracking) tracking = false;

        if(enabled) {
            this.over = mx > x && mx < x + sWidth && my > y && my < y + sHeight;

            if(over) {
                onOver();
                if(isLeftClick) {
                    clicked = true;
                    clicking = true;
                    cx = mx - x;
                    cy = my - y;
                    onClick();
                }

            }

            if(clicking && isLeftClicking) {
                onClicking();
            } else {
                clicking = false;
            }

            updateButton();
        }
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);
            sb.setColor(Color.WHITE);

            if(fontData != null) {
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    public void setX(float x) {
        this.x = x;
    }

    public void setY(float y) {
        this.y = y;
    }

    public void setPosition(float x, float y) {
        setX(x);
        setY(y);
    }

    public void setScale(float scale) {
        uiScale = scale;
    }

    protected void trackCursor(boolean center) {
        if(trackable && isCursorInScreen) {
            tracking = true;
            if (center) setPosition(mx - sWidth / 2, my - sHeight / 2);
            else setPosition(mx - cx, my - cy);
        }
    }

    public boolean isEnabled() {
        return enabled;
    }

    public boolean isOverlap(AbstractUI ui) {
        return x < ui.x + ui.sWidth && x + sWidth > ui.x && y < ui.y + ui.sHeight && y + sHeight > ui.y;
    }

    public void enable() {
        enabled = true;
    }

    public void disable() {
        enabled = false;
    }

    public void dispose() {
        img.dispose();
        fontData.font.dispose();
    }

    public void setText(String text) {
        setText(text, MEDIUM, 50, false);
    }

    public void setText(String text, FontType type, int s, boolean border) {
        this.fontData = new FontData(type, s, border);
        this.text = text;
    }

    @Override
    public final void scaleUpdate(float pre, float cur) {
        //setPosition(sx * cur, sy * cur);
    }

    public void onHide() {
        over = false;
        clicked = false;
        clicking = false;
    }

    protected void updateButton() { }

    protected void onOver() { }

    protected void onClick() { }

    protected void onClicking() { }
}
