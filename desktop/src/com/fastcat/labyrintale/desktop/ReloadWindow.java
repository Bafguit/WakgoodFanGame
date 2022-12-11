package com.fastcat.labyrintale.desktop;

import com.fastcat.labyrintale.prototype.ConfigurationProvider;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.tracker.Tracker;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import javax.swing.*;

public final class ReloadWindow extends JFrame {

    public ReloadWindow(File parentFile) {
        setTitle("Reloads Setting");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton reloadButton = new JButton("Reload");
        reloadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reloadButton.addActionListener(e -> {
            try {
                GameConfiguration.getInstance().updateAllProviders(parentFile);
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
            for (ConfigurationProvider<?> configurationProvider :
                    GameConfiguration.getInstance().getConfigurationProviders()) {
                for (Tracker<?> tracker : configurationProvider.getTrackers()) {
                    configurationProvider.applyTracker(tracker);
                }
            }
        });
        add(reloadButton);
        pack();
        setSize(500, 500);
        setLocationRelativeTo(null);
    }
}
