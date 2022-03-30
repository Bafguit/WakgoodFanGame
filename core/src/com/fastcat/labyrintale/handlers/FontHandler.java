package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.GlyphLayout;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.utils.Align;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.abstracts.AbstractSkill;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.badlogic.gdx.graphics.Color.*;
import static com.badlogic.gdx.graphics.Color.DARK_GRAY;
import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.*;

public class FontHandler implements Disposable {

    private static final FreeTypeFontGenerator light = new FreeTypeFontGenerator(Gdx.files.internal("font/museumL.ttf"));
    private static final FreeTypeFontGenerator medium = new FreeTypeFontGenerator(Gdx.files.internal("font/museumM.ttf"));
    private static final FreeTypeFontGenerator bold = new FreeTypeFontGenerator(Gdx.files.internal("font/museumB.ttf"));
    //private static final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    private static final GlyphLayout layout = new GlyphLayout();

    private static final Pattern COLOR_PATTERN = Pattern.compile("&([a-z])<([^>]*)>");
    private static final Pattern VAR_PATTERN = Pattern.compile("\\{([A-Z])\\}");
    //private static final Pattern ORB_PATTERN = Pattern.compile("\\(@\\)");

    public static final FontData LOGO = new FontData(BOLD, 100, false, true);
    public static final FontData COOLDOWN = new FontData(BOLD, 60, true, true);
    public static final FontData MAIN_MENU = new FontData(MEDIUM, 40, false, true);
    public static final FontData CARD_BIG_ORB = new FontData(MEDIUM, 50, false, true);
    public static final FontData CARD_BIG_NAME = new FontData(BOLD, 36, false, true);
    public static final FontData CARD_BIG_DESC = new FontData(MEDIUM, 26, false, true);
    public static final FontData BORDER = new FontData(MEDIUM, 30, true, true);
    public static final FontData BLOCK = new FontData(MEDIUM, 20, true, true);
    public static final FontData HP = new FontData(MEDIUM, 19, false, true);

    public enum FontType {
        LIGHT, MEDIUM, BOLD
    }

    public FontHandler() {

    }

    public void update() {
        //mainMenuFont.getData().setScale(InputHelper.scale);
    }

    public static BitmapFont generate(FontType type, int size, boolean border) {
        return generate(type, size, WHITE, DARK_GRAY, border);
    }


    public static BitmapFont generate(FontType type, int size, Color color, Color bColor, boolean border) {
        FreeTypeFontParameter parameter = new FreeTypeFontParameter();
        parameter.characters = "";
        parameter.incremental = true;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.size = size;
        parameter.color = color;
        parameter.borderColor = bColor;
        parameter.borderWidth = border ? parameter.size * 0.05f : 0.0f;
        if(type.equals(BOLD)) return bold.generateFont(parameter);
        else if(type.equals(LIGHT)) return light.generateFont(parameter);
        else return medium.generateFont(parameter);
    }

    public static void renderCenter(SpriteBatch sb, BitmapFont font, String text, float x, float y) {
        layout.setText(font, text);
        font.draw(sb, text, x - layout.width / 2, y + layout.height / 2);
    }

    public static void renderCenter(SpriteBatch sb, FontData fontData, String text, Vector2 vector, float bw, float bh) {
        renderCenter(sb, fontData, text, vector.x, vector.y, bw, bh);
    }

    public static void renderCenter(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        layout.setText(font, text, fontData.color, bw, Align.center, false);
        float ry = y + (layout.height) / 2;
        font.draw(sb, layout, x, ry);
    }

    public static void renderLineLeft(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        layout.setText(font, text, fontData.color, bw, Align.left, false);
        font.draw(sb, layout, x, y);
    }

    public static void renderLineTopLeft(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        font.getData().setScale(fontData.scale);
        font.getData().setLineHeight(fontData.size * 1.3f);
        layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        float ry = y + (layout.height) / 2;
        font.draw(sb, layout, x, y);
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
        while(matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        font.draw(sb, layout, x, y);
    }

    public static void renderColorCenter(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while(matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        layout.setText(font, text, fontData.color, bw, Align.center, true);
        float ry = y + (layout.height) / 2;
        font.draw(sb, layout, x, ry);
    }

    public static void renderCardLeft(SpriteBatch sb, AbstractSkill card, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while(matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        matcher = VAR_PATTERN.matcher(text);
        while(matcher.find()) {
            String mt = matcher.group(1);
            text = matcher.replaceFirst(card.getKeyColor(mt) + card.getKeyValue(mt) + getHexColor(fontData.color));
            matcher = VAR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        font.getData().setLineHeight(fontData.size * 1.3f);
        layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        /*if(layout.runs.size * font.getLineHeight() > bh) {
            font.getData().setScale(font.getScaleY() * 0.8f);
            layout.setText(font, text, fontData.color, bw, Align.topLeft, true);
        }*/
        font.draw(sb, layout, x, y);
    }

    public static void renderCardCenter(SpriteBatch sb, AbstractSkill card, FontData fontData, String text, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        Matcher matcher = COLOR_PATTERN.matcher(text);
        while(matcher.find()) {
            String mt = matcher.group(1);
            String mmt = matcher.group(2);
            text = matcher.replaceFirst(getColorKey(mt) + mmt + getHexColor(fontData.color));
            matcher = COLOR_PATTERN.matcher(text);
        }
        matcher = VAR_PATTERN.matcher(text);
        while(matcher.find()) {
            String mt = matcher.group(1);
            text = matcher.replaceFirst(card.getKeyColor(mt) + card.getKeyValue(mt) + getHexColor(fontData.color));
            matcher = VAR_PATTERN.matcher(text);
        }
        font.getData().setScale(fontData.scale);
        font.getData().setLineHeight(fontData.size + 20);
        while(true) {
            layout.setText(font, text, fontData.color, bw, Align.center, true);
            if(layout.runs.size * font.getLineHeight() > bh && font.getScaleY() > 0.5f) {
                font.getData().setScale(font.getScaleY() * 0.9f);
            } else {
                break;
            }
        }
        float ry = y + (layout.height) / 2;
        font.draw(sb, layout, x, ry);
    }

    private static void renderLine(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw) {
        BitmapFont font = fontData.font;
        font.getData().setScale(fontData.scale);
        layout.setText(font, text, fontData.color, bw, Align.center, true);
        float ry = y + (layout.height) / 2;
        font.draw(sb, layout, x, ry);
    }

    @Override
    public void dispose() {
        light.dispose();
        medium.dispose();
        bold.dispose();
        LOGO.dispose();
        MAIN_MENU.dispose();
        CARD_BIG_ORB.dispose();
        CARD_BIG_NAME.dispose();
        CARD_BIG_DESC.dispose();
        BORDER.dispose();
        BLOCK.dispose();
        HP.dispose();
    }

    public static String getHexColor(Color color) {
        return "[" + String.format("#%06X", (0xFFFFFF & rgb888(color))) + "]";
    }

    public static class FontData implements Disposable {
        public BitmapFont font;
        public FontType type;
        public Color color;
        public int size;
        public float scale = 1.0f;
        public boolean isStatic = false;

        public FontData(FontType type, int size, boolean border, boolean stc) {
            this(type, size, border, WHITE, DARK_GRAY);
            isStatic = stc;
        }

        public FontData(FontType type, int size, boolean border) {
            this(type, size, border, WHITE, DARK_GRAY);
        }

        public FontData(FontType type, int size, Color color) {
            this(type, size, false, color, DARK_GRAY);
        }

        public FontData(FontType type, int size, boolean border, Color color, Color bColor) {
            this.size = (int) (size * InputHandler.scale);
            this.font = generate(type, this.size, color, bColor, border);
            this.font.getData().markupEnabled = true;
            this.color = this.font.getColor();
            this.type = type;
        }

        public final FontData cpy() {
            return new FontData(type, (int) size, color);
        }

        @Override
        public void dispose() {
            font.dispose();
        }
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
}
