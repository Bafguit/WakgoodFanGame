package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;

import java.util.HashMap;

public class SoundHandler implements Disposable {

    public static final HashMap<String, Sound> sfx = new HashMap<>();

    public static final HashMap<String, MusicData> music = new HashMap<>();
    public static final Array<MusicData> musicPlaylist = new Array<>();
    public static final Array<MusicData> fadingMusic = new Array<>();

    public SoundHandler() {
        generateSound();
        generateMusic();
    }

    private static void generateSound() {
        sfx.put("LILPAA", getSound("sound/sfx/skill/lilpaa.mp3"));
        sfx.put("ATTACK_TEST", getSound("sound/sfx/attack/attackTest.ogg"));
        sfx.put("CLICK", getSound("sound/sfx/click.wav"));
        sfx.put("OVER", getSound("sound/sfx/over.wav"));
    }

    private static void generateMusic() {
        music.put("BATTLE_1", new MusicData(getMusic("sound/bgm/battle_1.mp3")));
        music.put("LOBBY", new MusicData(getMusic("sound/bgm/lobby.mp3")));
        music.put("MAP", new MusicData(getMusic("sound/bgm/map.mp3")));
    }

    private static Sound getSound(String url) {
        FileHandle fileHandle = Gdx.files.internal(url);
        return Gdx.audio.newSound(fileHandle);
    }

    private static Music getMusic(String url) {
        FileHandle fileHandle = Gdx.files.internal(url);
        return Gdx.audio.newMusic(fileHandle);
    }

    public static Sound playSfx(String key) {
        Sound s = sfx.get(key);
        if(s != null) {
            s.play(SettingHandler.setting.volumeSfx * 0.01f);
        }
        return s;
    }

    public static MusicData playMusic(String key, boolean isLoop, boolean fadeIn) {
        MusicData d = music.get(key);
        if(d != null) {
            Music s = d.music;
            s.setLooping(isLoop);
            if (fadeIn) {
                d.isFadingOut = false;
                d.fadeOutStartVolume = SettingHandler.setting.volumeBgm * 0.01f;
                d.setFadeTime(3);
                s.setVolume(0.0f);
                fadingMusic.add(d);
            } else s.setVolume(SettingHandler.setting.volumeBgm * 0.01f);
            musicPlaylist.add(d);
            s.play();
            System.out.println("KEY: " + key + " | VOLUME: " + s.getVolume() + " | STATUS: " + s.isPlaying());
        }
        return d;
    }

    public static void fadeOutMusic(String key) {
        MusicData d = music.get(key);
        if(d != null) {
            d.isFadingOut = true;
            d.isFading = true;
            d.fadeOutStartVolume = d.music.getVolume();
            d.setFadeTime(3);
            fadingMusic.add(d);
        }
    }

    public void update() {
        for(MusicData data : musicPlaylist) {
            if(!data.isFading) data.music.setVolume(SettingHandler.setting.volumeBgm * 0.01f);
        }
        for(MusicData data : fadingMusic) {
            if (!data.isFadingOut) {
                data.updateFadeIn();
            } else {
                data.updateFadeOut();
            }
        }

    }

    @Override
    public void dispose() {
        for(Sound s : sfx.values()) {
            s.dispose();
        }
        for(MusicData m : music.values()) {
            m.dispose();
        }
    }

    public static class MusicData implements Disposable {
        public float fadeTime = 4.0f;
        public float fadeTimer = 0.0F;
        public boolean isFadingOut = false;
        public boolean isFading = false;
        private float fadeOutStartVolume;
        public boolean isDone = false;
        public boolean stop = true;
        public Music music;

        public MusicData(Music music) {
            this.music = music;
        }

        public void setFadeTime(float time) {
            fadeTime = fadeTimer = time;
        }

        public void updateFadeIn() {
            fadeTimer -= Labyrintale.tick;
            if (fadeTimer < 0.0F) {
                fadeTimer = 0.0F;
                fadingMusic.removeValue(this, false);
            }
            music.setVolume(Interpolation.fade.apply(0.0F, fadeOutStartVolume, 1.0F - fadeTimer / fadeTime));
        }

        public void updateFadeOut() {
            fadeTimer -= Labyrintale.tick;
            if (fadeTimer < 0.0F) {
                fadeTimer = 0.0F;
                isDone = true;
                if(stop) music.stop();
                else music.pause();
                fadingMusic.removeValue(this, false);
                musicPlaylist.removeValue(this, false);
            } else {
                music.setVolume(Interpolation.fade.apply(fadeOutStartVolume, 0.0F, 1.0F - fadeTimer / fadeTime));
            }
        }

        @Override
        public void dispose() {
            music.dispose();
        }
    }
}
