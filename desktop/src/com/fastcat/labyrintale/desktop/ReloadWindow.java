package com.fastcat.labyrintale.desktop;

import com.fastcat.labyrintale.prototype.ConfigurationProvider;
import com.fastcat.labyrintale.prototype.GameConfiguration;
import com.fastcat.labyrintale.prototype.tracker.Tracker;
import java.awt.*;
import javax.swing.*;

public final class ReloadWindow extends JFrame {

  public ReloadWindow() {
    setTitle("설정 다시 로드");
    setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);

    JButton reloadButton = new JButton("설정 다시 불러오기");
    reloadButton.setAlignmentX(Component.CENTER_ALIGNMENT);
    reloadButton.addActionListener(
        e -> {
          for (ConfigurationProvider<?> configurationProvider :
              GameConfiguration.getInstance().getConfigurationProviders()) {
            for (Tracker<?> tracker : configurationProvider.getTrackers()) {
              configurationProvider.applyTracker(tracker);
            }
          }
          JOptionPane.showMessageDialog(this, "완료", "완료", JOptionPane.OK_OPTION);
        });
    add(reloadButton);
    pack();
    setSize(500, 500);
    setLocationRelativeTo(null);
  }
}
