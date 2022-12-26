package com.fastcat.labyrintale.screens.result;

import static com.badlogic.gdx.graphics.Color.DARK_GRAY;
import static com.badlogic.gdx.graphics.Color.WHITE;
import static com.fastcat.labyrintale.handlers.FontHandler.BUTTON;
import static com.fastcat.labyrintale.handlers.FontHandler.renderKeywordCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.InputHandler;

import java.nio.ByteBuffer;
import java.util.zip.Deflater;

public class ScreenshotButton extends AbstractUI {

    private String date;

    public ScreenshotButton() {
        super(FileHandler.getUi().get("BUTTON"));
        setPosition(Gdx.graphics.getWidth() * 0.83f - sWidth, 72 * InputHandler.scale);
        fontData = BUTTON;
        text = "스크린샷";
        setDate("");
        isPixmap = true;
    }

    public void setDate(String s) {
        date = s;
        overable = true;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (!overable) sb.setColor(DARK_GRAY);
            else if (!over) sb.setColor(Color.LIGHT_GRAY);
            else sb.setColor(WHITE);
            if (showImg) sb.draw(img, x, y, sWidth, sHeight);

            sb.setColor(WHITE);
            if (fontData != null) {
                renderKeywordCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
            }
        }
    }

    @Override
    protected void onClick() {
        Pixmap pixmap = Pixmap.createFromFrameBuffer(
                0, 0, Gdx.graphics.getBackBufferWidth(), Gdx.graphics.getBackBufferHeight());
        ByteBuffer pixels = pixmap.getPixels();

        // This loop makes sure the whole screenshot is opaque and looks exactly like what the user is seeing
        int size = Gdx.graphics.getBackBufferWidth() * Gdx.graphics.getBackBufferHeight() * 4;
        for (int i = 3; i < size; i += 4) {
            pixels.put(i, (byte) 255);
        }

        Gdx.files.local("screenshots").mkdirs();
        PixmapIO.writePNG(
                Gdx.files.local("screenshots/screenshot_" + date + ".png"), pixmap, Deflater.DEFAULT_COMPRESSION, true);
        pixmap.dispose();
        text = "저장됨";
        overable = false;
    }
}
