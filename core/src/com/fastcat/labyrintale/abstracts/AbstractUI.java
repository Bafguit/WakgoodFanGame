package com.fastcat.labyrintale.abstracts;

import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.*;
import static com.fastcat.labyrintale.handlers.InputHandler.*;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.Sprite;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;
import com.fastcat.labyrintale.handlers.LogHandler;
import com.fastcat.labyrintale.handlers.SoundHandler;

public abstract class AbstractUI implements Disposable {

    protected final Array<SubText> subs = new Array<>();
    public AbstractScreen screen;
    public Array<SubText> subTexts;
    public AbstractUI parent;
    public SubText.SubWay subWay = SubText.SubWay.UP;
    public Sprite img;
    public String text;
    public FontData fontData;
    public float x;
    public float y;
    public float localX;
    public float localY;
    public float width;
    public float height;
    public float sWidth;
    public float sHeight;
    public boolean over;
    public boolean isPixmap = false;
    public boolean hasOver = false;
    public boolean overable = true;
    public boolean instantClick = false;
    public boolean enabled;
    public boolean showImg = true;
    public float uiScale;
    public boolean clicked;
    public boolean clicking;
    public boolean clickable = true;
    public boolean trackable = false;
    public boolean tracking = false;
    public boolean mute = false;
    protected LogHandler logger = new LogHandler(this.getClass().getName());
    protected float cursorX;
    protected float cursorY;
    private boolean justOver = false;
    private boolean hasClick = false;

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
        this.img = new Sprite(img);
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        sWidth = this.width * scale;
        sHeight = this.height * scale;
        localX = this.x;
        localY = this.y;
        over = false;
        enabled = true;
        uiScale = 1.0f;
        clicked = false;
        clicking = false;
    }

    public final void update() {
        // x = sx * scale;
        // y = sy * scale;
        if (parent != null) {
            x = parent.x + localX;
            y = parent.y + localY;
        }
        sWidth = width * scale * uiScale;
        sHeight = height * scale * uiScale;
        if (isDesktop) {
            clicked = isLeftClick;
            clicking = isLeftClicking;
            hasClick = true;
        } else {
            clicked = (instantClick || hasClick) && isLeftClick;
            clicking = (instantClick || hasClick) && isLeftClicking;
            hasOver = mx > x && mx < x + sWidth && my > y && my < y + sHeight;
            if (isLeftClick) {
                hasClick = hasOver && !clicked;
            }
        }

        if (enabled && !Labyrintale.fading) {
            justOver = over;
            over = mx > x && mx < x + sWidth && my > y && my < y + sHeight;

            if (over && isPixmap) {
                Color c = getSpritePixColor();
                over = c.a > 0;
            }

            if (over) {
                if (!justOver) {
                    onOver();
                    justOver = true;
                }
                if (overable) {
                    cursorX = mx - x;
                    cursorY = my - y;
                    Labyrintale.subText = this;
                    subTexts = getSubText();
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

    public final void render(SpriteBatch sb) {
        sb.setColor(WHITE);
        renderUi(sb);
    }

    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(WHITE);
            if (showImg) sb.draw(img, x, y, sWidth, sHeight);

            sb.setColor(WHITE);
            if (fontData != null) {
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    public final void renderSub(SpriteBatch sb) {
        if (subTexts != null) {
            if (subTexts.size > 0) {
                sb.setColor(WHITE);
                float sc, subP;
                if (subWay == SubText.SubWay.DOWN) {
                    sc = -10 * scale;
                    subP = y + sc;
                    for (SubText s : subTexts) {
                        subP = s.render(sb, x + sWidth / 2, subP, subWay).y + sc;
                    }
                } else if (subWay == SubText.SubWay.UP) {
                    sc = 10 * scale;
                    subP = y + sHeight + sc;
                    for (SubText s : subTexts) {
                        subP = s.render(sb, x + sWidth / 2, subP, subWay).y + sc;
                    }
                } else if (subWay == SubText.SubWay.LEFT) {
                    sc = -10 * scale;
                    subP = x + sc;
                    for (SubText s : subTexts) {
                        subP = s.render(sb, subP, y + sHeight, subWay).x + sc;
                    }
                }
            }
        }
    }

    public Color getSpritePixColor() {
        Texture texture = img.getTexture();

        int LocalX = (int) ((mx - x) / scale);
        // we need to "invert" Y, because the screen coordinate origin is top-left
        int LocalY = (int) (((Gdx.graphics.getHeight() - my) - (Gdx.graphics.getHeight() - (y + sHeight))) / scale);

        if (!texture.getTextureData().isPrepared()) {
            texture.getTextureData().prepare();
        }
        Pixmap pixmap = texture.getTextureData().consumePixmap();
        Color c = new Color(pixmap.getPixel(LocalX, LocalY));
        pixmap.dispose();
        return c;
    }

    public void setParent(AbstractUI ui) {
        parent = ui;
        x = parent.x + localX;
        y = parent.y + localY;
    }

    protected Array<SubText> getSubText() {
        return new Array<>();
    }

    public void setX(float x) {
        this.x = localX = x;
        this.img.setX(x);
    }

    public void setY(float y) {
        this.y = localY = y;
        this.img.setY(y);
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
        if (trackable && isCursorInScreen) {
            tracking = true;
            if (center) setPosition(mx - sWidth / 2, my - sHeight / 2);
            else setPosition(mx - cursorX, my - cursorY);
        }
    }

    public void show() {}

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

    public void dispose() {}

    public void onHide() {
        over = false;
        showImg = false;
        clicked = false;
        clicking = false;
    }

    protected void updateButton() {}

    protected void onOver() {}

    protected void onClick() {}

    protected void onClicking() {}

    public static class TempUI extends AbstractUI {
        public TempUI(Sprite texture) {
            super(texture);
            overable = false;
        }

        public TempUI(Sprite texture, float x, float y) {
            super(texture, x, y);
            overable = false;
        }

        public TempUI(Sprite texture, float x, float y, float w, float h) {
            super(texture, x, y, w, h);
            overable = false;
        }
    }

    public static class SubText {
        private final GlyphLayout nameLayout;
        private final GlyphLayout descLayout;
        private final TempUI top;
        private final TempUI mid;
        private final TempUI bot;
        private final FontData nameFont;
        private final FontData descFont;
        public TempUI icon;
        public String name;
        public String desc;
        public int line;
        public float ww, hh, mh;

        public SubText(String name, String desc) {
            this.name = name;
            this.desc = desc;
            top = new TempUI(FileHandler.getUi().get("SUB_TOP"));
            mid = new TempUI(FileHandler.getUi().get("SUB_MID"));
            bot = new TempUI(FileHandler.getUi().get("SUB_BOT"));
            nameLayout = new GlyphLayout();
            descLayout = new GlyphLayout();
            nameFont = SUB_NAME;
            descFont = SUB_DESC;
            nameFont.font.getData().setScale(nameFont.scale);
            descFont.font.getData().setScale(descFont.scale);
            nameLayout.setText(nameFont.font, name, nameFont.color, mid.sWidth * 0.94f, Align.left, false);
            descLayout.setText(descFont.font, desc, descFont.color, mid.sWidth * 0.94f, Align.bottomLeft, true);
            line = descLayout.runs.size;
            ww = mid.sWidth;
            hh = bot.sHeight;
            mh = mid.sHeight * 0.35f + descLayout.height;
        }

        public SubText(Sprite icon, String name, String desc) {
            this(name, desc);
            this.icon = new TempUI(icon);
        }

        public Vector2 render(SpriteBatch sb, float x, float y, SubWay way) {
            float xx = 0, yy = 0;
            if (way == SubWay.UP) {
                xx = x - ww * 0.5f;
                yy = 0;
                sb.draw(bot.img, xx, y, ww, hh);
                sb.draw(mid.img, xx, y + (yy += hh), ww, mh);
                sb.draw(top.img, xx, y + (yy += mh), ww, hh);
                yy += hh;
                float ny = y + yy - ww * 0.03f, dy = ny - nameLayout.height * 1.5f;
                nameFont.draw(sb, nameLayout, nameFont.alpha, x - ww * 0.47f, ny);
                descFont.draw(sb, descLayout, descFont.alpha, x - ww * 0.47f, dy);
            } else if (way == SubWay.DOWN) {
                xx = x - ww * 0.5f;
                yy = 0;
                sb.draw(top.img, xx, y + (yy -= hh), ww, hh);
                sb.draw(mid.img, xx, y + (yy -= mh), ww, mh);
                sb.draw(bot.img, xx, y + (yy -= hh), ww, hh);
                float ny = y - ww * 0.03f, dy = ny - nameLayout.height * 1.5f;
                nameFont.draw(sb, nameLayout, nameFont.alpha, x - ww * 0.47f, ny);
                descFont.draw(sb, descLayout, descFont.alpha, x - ww * 0.47f, dy);
            } else if (way == SubWay.LEFT) {
                xx = x - ww;
                yy = 0;
                sb.draw(top.img, xx, y + (yy -= hh), ww, hh);
                sb.draw(mid.img, xx, y + (yy -= mh), ww, mh);
                sb.draw(bot.img, xx, y + (yy -= hh), ww, hh);
                float ny = y - ww * 0.03f, dy = ny - nameLayout.height * 1.5f;
                nameFont.draw(sb, nameLayout, nameFont.alpha, xx + ww * 0.03f, ny);
                descFont.draw(sb, descLayout, descFont.alpha, xx + ww * 0.03f, dy);
            } else if (way == SubWay.RIGHT) {
                xx = x;
                yy = 0;
                sb.draw(top.img, xx, y + (yy -= hh), ww, hh);
                sb.draw(mid.img, xx, y + (yy -= mh), ww, mh);
                sb.draw(bot.img, xx, y + (yy -= hh), ww, hh);
                float ny = y - ww * 0.03f, dy = ny - nameLayout.height * 1.5f;
                nameFont.draw(sb, nameLayout, nameFont.alpha, xx + ww * 0.03f, ny);
                descFont.draw(sb, descLayout, descFont.alpha, xx + ww * 0.03f, dy);
                xx += ww;
            }
            return new Vector2(xx, y + yy);
        }

        public enum SubWay {
            DOWN,
            UP,
            RIGHT,
            LEFT
        }
    }
}
