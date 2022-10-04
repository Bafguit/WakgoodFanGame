package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.BitmapFontCache;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.badlogic.gdx.graphics.Color.*;
import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.FreeTypeFontParameter;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.BOLD;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.MEDIUM;

@NoArgsConstructor(access = AccessLevel.PUBLIC)
public final class FontHandler implements Disposable {
    private static final FreeTypeFontGenerator medium = new FreeTypeFontGenerator(Gdx.files.internal("font/hlb.ttf"));
    private static final FreeTypeFontGenerator bold = new FreeTypeFontGenerator(Gdx.files.internal("font/hlb.ttf"));
    public static final FontData TURN_CHANGE = new FontData(BOLD, 100, false);
    public static final FontData COOLDOWN = new FontData(BOLD, 80, true);
    public static final FontData ENERGY = new FontData(BOLD, 64, true);
    public static final FontData MAIN_MENU = new FontData(MEDIUM, 53, false);
    public static final FontData CARD_BIG_ORB = new FontData(MEDIUM, 67, false);
    //private static final Pattern ORB_PATTERN = Pattern.compile("\\(@\\)");
    public static final FontData CARD_BIG_NAME = new FontData(BOLD, 48, WHITE, true, true);
    public static final FontData CARD_BIG_DESC = new FontData(MEDIUM, 40, WHITE, true, true);
    public static final FontData BIG_DESC = new FontData(MEDIUM, 32, WHITE, true, true);
    public static final FontData SEED = new FontData(MEDIUM, 44, WHITE, true, false);
    public static final FontData SHOP_OK = new FontData(MEDIUM, 30, WHITE, true, true);
    public static final FontData SHOP_NO = new FontData(MEDIUM, 30, SCARLET, true, true);
    public static final FontData INFO_NAME = new FontData(BOLD, 48, false);
    public static final FontData INFO_HP = new FontData(MEDIUM, 35, false);
    public static final FontData EVENT_TITLE = new FontData(MEDIUM, 44, true);
    public static final FontData EVENT_DESC = new FontData(MEDIUM, 32, true);
    public static final FontData EVENT_CHOICE = new FontData(MEDIUM, 32, false);
    public static final FontData BORDER = new FontData(MEDIUM, 40, true);
    public static final FontData BLOCK = new FontData(MEDIUM, 36, CYAN, true, true);
    public static final FontData HP = new FontData(MEDIUM, 26, false);
    public static final FontData STATUS = new FontData(MEDIUM, 24, true);
    public static final FontData REST_DESC = new FontData(MEDIUM, 48, false);
    public static final FontData WAY = new FontData(MEDIUM, 32, false);
    public static final FontData SETTING = new FontData(BOLD, 44, true);
    public static final FontData BLEAK = new FontData(BOLD, 80, Color.valueOf("#4a1564"), false, false);
    public static final FontData SUB_NAME = new FontData(MEDIUM, 32, true);
    public static final FontData SUB_DESC = new FontData(MEDIUM, 26, false);
    public static final FontData EXP = new FontData(MEDIUM, 26, true);

    //private static final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    public static final GlyphLayout layout = new GlyphLayout();
    private static final Pattern COLOR_PATTERN = Pattern.compile("&([a-z])<([^>]*)>");
    private static final Pattern VAR_PATTERN = Pattern.compile("\\{([A-Z])\\}");
    /***
     * Instance of handler.
     * Initialized on getInstance()
     */
    private static FontHandler instance;

    /***
     * Returns instance of handler, if not exist, create new.
     * @return instance of handler
     */
    public static FontHandler getInstance() {
        if (instance == null)
            return (instance = new FontHandler());
        return instance;
    }

    public static BitmapFont generate(FontType type, int size, boolean border) {
        return generate(type, size, WHITE, DARK_GRAY, border);
    }

    public static BitmapFont generate(FontType type, int size, Color color, Color bColor, boolean border) {
        return generate(type, size, color, bColor, true, border);
    }

    public static BitmapFont generate(FontType type, int size, Color color, Color bColor, boolean shadow, boolean border) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters = "";
        parameter.incremental = true;
        parameter.shadowOffsetX = parameter.shadowOffsetY = shadow ? 2 : 0;
        parameter.size = size;
        parameter.color = color;
        parameter.borderColor = bColor;
        parameter.borderWidth = border ? parameter.size * 0.03f : 0.0f;
        if (type.equals(BOLD)) return bold.generateFont(parameter);
        else return medium.generateFont(parameter);
    }

    public static void renderCenter(SpriteBatch sb, FontData font, String text, float x, float y) {
        layout.setText(font.font, text);
        font.draw(sb, layout, font.alpha, x - layout.width / 2, y + layout.height / 2);
    }

    public static void renderCenter(SpriteBatch sb, FontData fontData, String text, Vector2 vector, float bw, float bh) {
        renderCenter(sb, fontData, text, vector.x, vector.y, bw, bh);
    }

    public static void renderCenter(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        layout.setText(font, text, fontData.color, bw, Align.center, false);
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    public static void renderLineLeft(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        layout.setText(font, text, fontData.color, bw, Align.left, false);
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    public static void renderLineRight(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        layout.setText(font, text, fontData.color, bw, Align.right, false);
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    public static void renderLineBotLeft(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        layout.setText(font, text, fontData.color, bw, Align.left, false);
        fontData.draw(sb, layout, fontData.alpha, x, y);
    }

    public static void renderLineTopLeft(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    public static void renderKeywordCenter(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        renderLine(sb, fontData, text, x, y, bw);
    }

    public static void renderCardCenter(SpriteBatch sb, AbstractSkill card, FontData fontData, String text, Vector2 vector, float bw, float bh) {
        renderCardCenter(sb, card, fontData, text, vector.x, vector.y, bw, bh);
    }

    public static void renderColorLeft(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        fontData.draw(sb, layout, fontData.alpha, x, y);
    }

    public static void renderColorCenter(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        layout.setText(font, text, fontData.color, bw, Align.center, true);
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    public static void renderCardLeft(SpriteBatch sb, AbstractSkill card, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        matcher = VAR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            text = matcher.replaceFirst(card.getKeyValue(mt) + getHexColor(fontData.color));
            matcher = VAR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        font.getData().setLineHeight(fontData.size * 1.3f);
        layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        /*if(layout.runs.size * font.getLineHeight() > bh) {
            font.getData().setScale(font.getScaleY() * 0.8f);
            layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        }*/
        fontData.draw(sb, layout, fontData.alpha, x, y);
    }

    public static void renderCardCenter(SpriteBatch sb, AbstractSkill card, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        matcher = VAR_PATTERN.matcher(text);
        while (matcher.find()) {
            String mt = matcher.group(1);
            text = matcher.replaceFirst(card.getKeyValue(mt) + getHexColor(fontData.color));
            matcher = VAR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        font.getData().setLineHeight(fontData.size + 20);
        while (true) {
            layout.setText(font, text, fontData.color, bw, Align.center, true);
            if (layout.runs.size * font.getLineHeight() > bh && font.getScaleY() > 0.5f) {
                font.getData().setScale(font.getScaleY() * 0.9f);
            } else {
                break;
            }
        }
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    private static void renderLine(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw) {
        BitmapFont font = fontData.font;
        font.getData().setScale(fontData.scale);
        layout.setText(font, text, fontData.color, bw, Align.center, true);
        float ry = y + (layout.height) / 2;
        fontData.draw(sb, layout, fontData.alpha, x, ry);
    }

    public static String getHexColor(Color color) {
        return "[" + String.format("#%06X", (0xFFFFFF & rgb888(color))) + "]";
    }

    private static String getColorKey(String key) {
        switch (key) {
            case "b":
                return getHexColor(CYAN);
            case "g":
                return getHexColor(CHARTREUSE);
            case "r":
                return getHexColor(SCARLET);
            case "y":
                return getHexColor(YELLOW);
            default:
                return getHexColor(WHITE);
        }
    }

    public void update() {
        //mainMenuFont.getData().setScale(InputHelper.scale);
    }

    @Override
    public void dispose() {
        medium.dispose();
        bold.dispose();
        TURN_CHANGE.dispose();
        COOLDOWN.dispose();
        ENERGY.dispose();
        MAIN_MENU.dispose();
        CARD_BIG_ORB.dispose();
        CARD_BIG_NAME.dispose();
        CARD_BIG_DESC.dispose();
        BIG_DESC.dispose();
        SEED.dispose();
        SHOP_OK.dispose();
        SHOP_NO.dispose();
        INFO_NAME.dispose();
        INFO_HP.dispose();
        EVENT_TITLE.dispose();
        EVENT_DESC.dispose();
        EVENT_CHOICE.dispose();
        BORDER.dispose();
        BLOCK.dispose();
        HP.dispose();
        STATUS.dispose();
        REST_DESC.dispose();
        SETTING.dispose();
        WAY.dispose();
        BLEAK.dispose();
        SUB_NAME.dispose();
        SUB_DESC.dispose();
    }

    public enum FontType {
        MEDIUM, BOLD
    }

    public static class FontData implements Disposable {
        public BitmapFont font;
        public FontType type;
        public Color color;
        public Color bColor;
        public int size;
        public float scale = 1.0f;
        public float alpha = 1.0f;
        public boolean shadow;
        public boolean border;

        public FontData(FontType type, int size, Color color, boolean shadow, boolean border) {
            this(type, size, shadow, border, color, DARK_GRAY);
        }

        public FontData(FontType type, int size, boolean border) {
            this(type, size, true, border, WHITE, DARK_GRAY);
        }

        public FontData(FontType type, int size, Color color) {
            this(type, size, true, false, color, DARK_GRAY);
        }

        public FontData(FontType type, int size, boolean shadow, boolean border, Color color, Color bColor) {
            this.size = (int) (size * InputHandler.scale);
            this.color = color.cpy();
            this.bColor = bColor.cpy();
            this.font = generate(type, this.size, this.color, this.bColor, shadow, border);

            this.font.getData().markupEnabled = true;
            this.type = type;
            this.shadow = shadow;
            this.border = border;
        }

        public final FontData cpy() {
            return new FontData(type, size, shadow, border, new Color(color), new Color(bColor));
        }

        public final void draw(SpriteBatch sb, GlyphLayout layout, float alpha, float x, float y) {
            BitmapFontCache cache = font.getCache();
            cache.clear();
            cache.addText(layout, x, y);
            cache.draw(sb, alpha);
        }

        @Override
        public void dispose() {
            font.dispose();
        }
    }
}
