package com.fastcat.labyrintale.utils;

import java.io.*;
import java.util.Properties;

public class BuildInfo {

    public static final String BUILD_VERSION;

    static {
        String buildVersion = "ERROR";
        try (InputStream in = BuildInfo.class.getResourceAsStream("/version.properties")) {
            Properties p = new Properties();
            p.load(in);

            buildVersion = (String) p.get("version");

        } catch (IOException e) {
            e.printStackTrace();
        }
        BUILD_VERSION = buildVersion;
    }
}
