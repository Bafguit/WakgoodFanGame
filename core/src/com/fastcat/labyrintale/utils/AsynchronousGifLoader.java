package com.fastcat.labyrintale.utils;

import com.badlogic.gdx.assets.AssetDescriptor;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.assets.loaders.AsynchronousAssetLoader;
import com.badlogic.gdx.assets.loaders.FileHandleResolver;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.graphics.g2d.Animation;
import com.badlogic.gdx.utils.Array;

public class AsynchronousGifLoader extends AsynchronousAssetLoader<Gif, GifLoaderParameters> {

    private GifDecoder gifDecoder;

    public AsynchronousGifLoader(FileHandleResolver resolver) {
        super(resolver);
    }

    @Override
    public void loadAsync(AssetManager manager, String fileName, FileHandle file, GifLoaderParameters parameter) {
        gifDecoder = new GifDecoder();
        gifDecoder.read(file.read());
    }

    @Override
    public Gif loadSync(AssetManager manager, String fileName, FileHandle file, GifLoaderParameters parameter) {
        return new Gif(gifDecoder.getAnimation(Animation.PlayMode.LOOP));
    }

    @Override
    public Array<AssetDescriptor> getDependencies(String fileName, FileHandle file, GifLoaderParameters parameter) {
        return null;
    }
}
