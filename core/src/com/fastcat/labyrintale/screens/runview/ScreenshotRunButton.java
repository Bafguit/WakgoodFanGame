package com.fastcat.labyrintale.screens.runview;

import static com.fastcat.labyrintale.handlers.FontHandler.renderCenter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.PixmapIO;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.fastcat.labyrintale.Labyrintale;
import com.fastcat.labyrintale.abstracts.AbstractUI;
import com.fastcat.labyrintale.handlers.FileHandler;
import com.fastcat.labyrintale.handlers.FontHandler;
import java.nio.ByteBuffer;
import java.util.zip.Deflater;

public class ScreenshotRunButton extends AbstractUI {

    private final Color fColor = Color.GRAY.cpy();
    private final float aa = fColor.r;
    private float a = fColor.r;

    private String date;

    public ScreenshotRunButton() {
        super(FileHandler.getUi().get("BUTTON"));
        fontData = new FontHandler.FontData(FontHandler.FontType.MEDIUM, 53, false, false);
        text = "스크린샷";
        setDate("");
    }

    public void setDate(String s) {
        date = s;
        overable = true;
    }

    @Override
    protected void renderUi(SpriteBatch sb) {
        if (enabled) {
            if (overable && !over) {
                a -= Labyrintale.tick * 4;
                if (a <= aa) a = aa;
            } else {
                a += Labyrintale.tick * 4;
                if (a >= 1) a = 1;
            }

            fColor.set(a, a, a, 1);
            fontData.color = fColor;

            renderCenter(sb, fontData, text, x, y + sHeight / 2, sWidth, sHeight);
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
