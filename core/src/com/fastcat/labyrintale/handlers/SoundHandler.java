package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.math.Interpolation;
import com.badlogic.gdx.utils.Array;
import com.badlogic.gdx.utils.Disposable;
import com.fastcat.labyrintale.Labyrintale;

import javax.sound.sampled.*;
import java.io.IOException;
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
        music.put("BATTLE_1", new MusicData(getClip("sound/bgm/battle_1.wav")));
        music.put("LOBBY", new MusicData(getClip("sound/bgm/lobby.wav")));
        music.put("MAP", new MusicData(getClip("sound/bgm/map.wav")));
    }

    private static Sound getSound(String url) {
        FileHandle fileHandle = Gdx.files.internal(url);
        return Gdx.audio.newSound(fileHandle);
    }

    private static Clip getClip(String url) {
        try {
            FileHandle fileHandle = Gdx.files.internal(url);
            AudioInputStream audioInputStream = AudioSystem
                    .getAudioInputStream(fileHandle.file().getAbsoluteFile());
            Clip clip = AudioSystem.getClip();
            clip.open(audioInputStream);
            return clip;
        } catch(UnsupportedAudioFileException | IOException | LineUnavailableException e) {
            Gdx.app.error("SoundHandler", "Error while loading music: " + url, e);
        }
        return null;
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
            Clip s = d.clip;
            s.loop(isLoop ? Clip.LOOP_CONTINUOUSLY : 0);
            if (fadeIn) {
                d.isFadingOut = false;
                d.fadeOutStartVolume = SettingHandler.setting.volumeBgm * 0.01f;
                d.setFadeTime(3);
                d.setVolume(0.0f);
                fadingMusic.add(d);
            } else d.setVolume(SettingHandler.setting.volumeBgm * 0.01f);
            musicPlaylist.add(d);
            s.start();
            System.out.println("KEY: " + key + " | VOLUME: " + d.getVolume() + " | STATUS: " + s.isActive());
        }
        return d;
    }

    public static void fadeOutMusic(String key) {
        MusicData d = music.get(key);
        if(d != null) {
            d.isFadingOut = true;
            d.isFading = true;
            d.fadeOutStartVolume = d.getVolume();
            d.setFadeTime(3);
            fadingMusic.add(d);
        }
    }

    public void update() {
        for(MusicData data : musicPlaylist) {
            if(!data.isFading) data.setVolume(SettingHandler.setting.volumeBgm * 0.01f);
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
        public Clip clip;
        public float volume;

        public MusicData(Clip clip) {
            this.clip = clip;
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
            setVolume(Interpolation.fade.apply(0.0F, fadeOutStartVolume, 1.0F - fadeTimer / fadeTime));
        }

        public void updateFadeOut() {
            fadeTimer -= Labyrintale.tick;
            if (fadeTimer < 0.0F) {
                fadeTimer = 0.0F;
                isDone = true;
                if(stop) clip.stop();
                else {
                    long pos = clip.getMicrosecondPosition();
                    clip.stop();
                    clip.setMicrosecondPosition(pos);
                };
                fadingMusic.removeValue(this, false);
                musicPlaylist.removeValue(this, false);
            } else {
                setVolume(Interpolation.fade.apply(fadeOutStartVolume, 0.0F, 1.0F - fadeTimer / fadeTime));
            }
        }

        public float getVolume() {
            return volume;
        }

        public void setVolume(float volume) {
            if (volume < 0f || volume > 1f) {
                Gdx.app.error("SoundHandler", "Volume not valid: " + volume);
                volume = Math.max(0, Math.min(volume, 1));
            }
            this.volume = volume;
            FloatControl gainControl = (FloatControl) clip.getControl(FloatControl.Type.MASTER_GAIN);
            System.out.println(20f * (float) Math.log10(volume) + "\t" + gainControl.getMinimum());
            gainControl.setValue(Math.max(20f * (float) Math.log10(volume), gainControl.getMinimum()));
        }

        @Override
        public void dispose() {
            //music.dispose();
        }
    }
}
