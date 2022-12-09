package com.fastcat.labyrintale.handlers;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;
import com.badlogic.gdx.utils.Array;
import java.io.IOException;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

public final class RunHandler {
    private static final Pattern DATE =
            Pattern.compile("run_[0-9]{4}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}_[0-9]{2}.json");

    public static Array<SaveHandler.SaveData> runs = new Array<>();

    public static void load() {
        SaveHandler.refresh();
        runs.clear();
        FileHandle file = Gdx.files.local("runs");
        if (file.isDirectory()) {
            FileHandle[] rs = file.list();
            if (rs != null && rs.length > 0) {
                for (int i = rs.length - 1; i >= 0; i--) {
                    FileHandle f = rs[i];
                    String n = f.name();
                    Matcher matcher = DATE.matcher(n);
                    if (matcher.find()) {
                        try {
                            SaveHandler.SaveData sd =
                                    SaveHandler.mapper.readValue(f.file(), SaveHandler.SaveData.class);
                            if (sd != null) runs.add(sd);
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }
    }
}
