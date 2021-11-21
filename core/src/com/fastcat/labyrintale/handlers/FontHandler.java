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
import com.fastcat.labyrintale.abstracts.AbstractSkill;
import com.fastcat.labyrintale.abstracts.AbstractTalent;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import static com.badlogic.gdx.graphics.Color.*;
import static com.badlogic.gdx.graphics.Color.DARK_GRAY;
import static com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator.*;
import static com.fastcat.labyrintale.handlers.FontHandler.FontType.*;

public class FontHandler {

    private static final FreeTypeFontGenerator light = new FreeTypeFontGenerator(Gdx.files.internal("font/museumL.ttf"));
    private static final FreeTypeFontGenerator medium = new FreeTypeFontGenerator(Gdx.files.internal("font/museumM.ttf"));
    private static final FreeTypeFontGenerator bold = new FreeTypeFontGenerator(Gdx.files.internal("font/museumB.ttf"));
    private static final FreeTypeFontParameter parameter = new FreeTypeFontParameter();
    private static final GlyphLayout layout = new GlyphLayout();

    private static final Pattern COLOR_PATTERN = Pattern.compile("&([a-z])<([^>]*)>");
    private static final Pattern VAR_PATTERN = Pattern.compile("\\{([A-Z])\\}");
    private static final Pattern ORB_PATTERN = Pattern.compile("\\(@\\)");

    public static final FontData LOGO = new FontData(BOLD, 100, false);
    public static final FontData MAIN_MENU = new FontData(MEDIUM, 40, false);
    public static final BitmapFont STANDARD = generate(MEDIUM, 50, false);
    public static final FontData CARD_BIG_ORB = new FontData(MEDIUM, 50, true);
    public static final FontData CARD_BIG_NAME = new FontData(BOLD, 48, false);
    public static final FontData CARD_BIG_DESC = new FontData(MEDIUM, 36, false);

    private static Texture imgG = new Texture("orb.png");

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
        parameter.characters = "";
        parameter.incremental = true;
        parameter.shadowOffsetX = 2;
        parameter.shadowOffsetY = 2;
        parameter.size = size;
        parameter.color = color;
        parameter.borderColor = bColor;
        parameter.borderWidth = border ? parameter.size * 0.1f : 0.0f;
        final BitmapFont ff;
        if(type.equals(BOLD)) ff = bold.generateFont(parameter);
        else if(type.equals(LIGHT)) ff = light.generateFont(parameter);
        else ff = medium.generateFont(parameter);
        return ff;
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

    public static void renderKeywordCenter(SpriteBatch sb, FontData fontData, String text, float x, float y, float bw, float bh) {
        renderLine(sb, fontData, text, getHexColor(CHARTREUSE) + "$1" + getHexColor(fontData.color), "$1", x, y, bw, bh);
    }

    public static void renderSkillCenter(SpriteBatch sb, AbstractTalent skill, FontData fontData, String text, float x, float y, float bw, float bh) {
        renderLine(sb, fontData, text, skill.getKeyColor("$1") + skill.getKeyValue("$1") + getHexColor(fontData.color), skill.getKeyValue("$1"), x, y, bw, bh);
    }

    public static void renderCardCenter(SpriteBatch sb, AbstractSkill card, FontData fontData, String text, Vector2 vector, float bw, float bh) {
        renderCardCenter(sb, card, fontData, text, vector.x, vector.y, bw, bh);
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
        text = ORB_PATTERN.matcher(text).replaceAll("　");
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
        for(GlyphLayout.GlyphRun run : layout.runs) {
            if(run.glyphs.toString().contains("　")) {
                float gx = x + run.x, gy = ry + run.y - font.getCapHeight();
                float[] xAdvances = run.xAdvances.items;
                Object[] glyphs = run.glyphs.items;
                for (int i = 0; i < run.glyphs.size; i++) {
                    gx += xAdvances[i];
                    if (glyphs[i].toString().equals("　")) {
                        sb.draw(imgG, gx, gy);
                    }
                }
            }
        }
    }

    private static void renderLine(SpriteBatch sb, FontData fontData, String text, String realValue, String tempValue, float x, float y, float bw, float bh) {
        BitmapFont font = fontData.font;
        String realText = COLOR_PATTERN.matcher(text).replaceAll(getColorKey("$1") + "$2" + getHexColor(fontData.color));
        realText = VAR_PATTERN.matcher(realText).replaceAll(realValue);
        realText = ORB_PATTERN.matcher(realText).replaceAll("　");
        String tempText = COLOR_PATTERN.matcher(text).replaceAll("$2");
        tempText = VAR_PATTERN.matcher(tempText).replaceAll(tempValue);
        tempText = ORB_PATTERN.matcher(tempText).replaceAll("　");
        font.getData().setScale(fontData.scale);
        while(true) {
            layout.setText(font, tempText, fontData.color, bw, Align.center, true);
            if(layout.runs.size * font.getLineHeight() > bh && font.getScaleY() > 0.5f) {
                font.getData().setScale(font.getScaleY() * 0.9f);
            } else {
                break;
            }
        }
        float ry = y + (layout.runs.size * font.getLineHeight() - font.getCapHeight() + font.getXHeight() * 1.2f) / 2;
        font.draw(sb, layout, x, ry);
        for(GlyphLayout.GlyphRun run : layout.runs) {
            if(run.glyphs.toString().contains("　")) {
                float gx = x + run.x, gy = ry + run.y - font.getCapHeight();
                float[] xAdvances = run.xAdvances.items;
                Object[] glyphs = run.glyphs.items;
                for (int i = 0; i < run.glyphs.size; i++) {
                    gx += xAdvances[i];
                    if (glyphs[i].toString().equals("　")) {
                        sb.draw(imgG, gx, gy);
                    }
                }
            }
        }
    }

    public static String getHexColor(Color color) {
        return "[" + String.format("#%06X", (0xFFFFFF & rgb888(color))) + "]";
    }

    public static class FontData {
        public BitmapFont font;
        public FontType type;
        public Color color;
        public float size;
        public float scale = 1.0f;

        public FontData(FontType type, int size, boolean border) {
            this(type, size, border, WHITE, DARK_GRAY);
        }

        public FontData(FontType type, int size, Color color) {
            this(type, size, false, color, DARK_GRAY);
        }

        public FontData(FontType type, int size, boolean border, Color color, Color bColor) {
            this.font = generate(type, size, color, bColor, border);
            this.font.getData().markupEnabled = true;
            this.color = this.font.getColor();
            this.type = type;
            this.size = size;
        }

        public final FontData cpy() {
            return new FontData(type, (int) size, color);
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
