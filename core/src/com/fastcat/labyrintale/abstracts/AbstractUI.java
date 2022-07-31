package com.fastcat.labyrintale.abstracts;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Quaternion;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.LogHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

import java.io.Serializable;

import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;
import static com.fastcat.labyrintale.handlers.FontHandler.generate;
import static com.fastcat.labyrintale.handlers.InputHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.scale;

public abstract class AbstractUI implements Disposable {

    private static final SpriteBatch uib = new SpriteBatch();

    public AbstractScreen screen;
    protected LogHandler logger = new LogHandler(this.getClass().getName());
    public Sprite img;
    public String text;
    public FontData fontData;
    public float x;
    protected float sx;
    protected float cx;
    public float y;
    protected float sy;
    protected float cy;
    public float width;
    public float height;
    public float sWidth;
    public float sHeight;
    public boolean over;
    private boolean justOver = false;
    public boolean overable = true;
    public boolean enabled;
    public boolean showImg = true;

    public float uiScale;
    public boolean clicked;
    public boolean clicking;
    public boolean clickable = true;
    public boolean trackable = false;
    public boolean tracking = false;
    public boolean mute = false;

    public AbstractUI(String imgPath) {
        this(imgPath, -10000, -10000);
    }

    public AbstractUI(Sprite texture) {
        this(texture, -10000, -10000);
    }

    public AbstractUI(String imgPath, float x, float y) {
        this(new Sprite(new Texture(imgPath)), x, y);
    }

    public AbstractUI(Sprite texture, float x, float y) {
        this(texture, x, y, texture.getWidth(), texture.getHeight());
    }

    public AbstractUI(String imgPath, float x, float y, float width, float height) {
        this(new Sprite(new Texture(imgPath)), x, y, width, height);
    }

    public AbstractUI(Sprite img, float x, float y, float width, float height) {
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

    public final void update() {
        //x = sx * scale;
        //y = sy * scale;
        sWidth = width * scale * uiScale;
        sHeight = height * scale * uiScale;
        clicked = isLeftClick;
        clicking = isLeftClicking;

        if(enabled && !Labyrintale.fading) {
            justOver = over;
            over = mx > x && mx < x + sWidth && my > y && my < y + sHeight;

            if(over) {
                if(!justOver) {
                    //if(overable) SoundHandler.playSfx("OVER");
                    onOver();
                    justOver = true;
                }
                if(overable) {
                    cx = mx - x;
                    cy = my - y;
                    if (clicked) {
                        if (clickable) {
                            if (!mute) SoundHandler.playSfx("CLICK");
                            onClick();
                        }
                    }
                    if (clicking) onClicking();
                }
            }

            updateButton();
        }
    }

    public void render(SpriteBatch sb) {
        if(enabled) {
            if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(Color.WHITE);
            if(showImg) sb.draw(img, x, y, sWidth, sHeight);

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
        sWidth = width * InputHandler.scale * uiScale;
        sHeight = height * InputHandler.scale * uiScale;
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

    }

    public void onHide() {
        over = false;
        showImg = false;
        clicked = false;
        clicking = false;
    }

    protected void updateButton() { }

    protected void onOver() { }

    protected void onClick() { }

    protected void onClicking() { }
}
