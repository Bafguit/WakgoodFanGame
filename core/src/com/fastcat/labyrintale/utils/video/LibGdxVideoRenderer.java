package com.fastcat.labyrintale.utils.video;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Pixmap;
import com.badlogic.gdx.graphics.Texture;
import com.fastcat.labyrintale.utils.LibGdxHandle;
import net.indiespot.media.LibGDXHandle;
import net.indiespot.media.VideoRenderer;
import org.lwjgl.glfw.GLFW;

import java.awt.*;
import java.io.IOException;
import java.nio.ByteBuffer;

import static org.lwjgl.opengl.GL11.glViewport;

public final class LibGdxVideoRenderer implements VideoRenderer {
    private Texture texture;

    private int w;
    private int h;
    @Override
    public void init(Dimension dim) {
        w = dim.width;
        h = dim.height;
    }

    @Override
    public boolean isVisible() {
        return !GLFW.glfwWindowShouldClose(LibGdxHandle.getCurrentWindow().getWindowHandle());
    }

    @Override
    public void setStats(String stats) {
        Gdx.app.debug("video", stats);
    }

    @Override
    public void render(ByteBuffer rgba) {

        if (texture == null) texture = new Texture(w, h, Pixmap.Format.RGBA8888);

        texture.bind();

        Gdx.gl.glTexImage2D(GL20.GL_TEXTURE_2D, 0, GL20.GL_RGB, w, h, 0, GL20.GL_RGB,
                GL20.GL_UNSIGNED_BYTE, rgba);

    }

    @Override
    public void close() throws IOException {

    }

    public Texture getTexture() {
        return texture;
    }
}
