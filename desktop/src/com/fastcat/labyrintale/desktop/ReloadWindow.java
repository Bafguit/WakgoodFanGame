package com.fastcat.labyrintale.desktop;

import com.fastcat.labyrintale.prototype.ConfigurationProvider;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.tracker.Tracker;

import javax.swing.*;
import java.awt.*;
import java.io.File;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;

public final class ReloadWindow extends JFrame {

    public ReloadWindow(File parentFile) {
        setTitle("설정 다시 로드");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

        JButton reloadButton = new JButton("설정 다시 불러오기");
        reloadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
        reloadButton.addActionListener(
                e -> {
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
                    //JOptionPane.showMessageDialog(this, "완료", "완료", JOptionPane.OK_OPTION);
                });
        add(reloadButton);
        pack();
        setSize(500, 500);
        setLocationRelativeTo(null);
    }
}
