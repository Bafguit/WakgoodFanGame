package io.github.singlerr;

import io.github.singlerr.exporters.MonsterDataExporter;
import io.github.singlerr.exporters.PlayerDataExporter;
import java.io.File;

public class Main {
  public static void main(String[] args) {
    File parentFile = new File("exported");
    if (!parentFile.exists()) parentFile.mkdir();

    DataExportManager.getInstance()
        .registerDataExporters(MonsterDataExporter.class, PlayerDataExporter.class);
    DataExportManager.getInstance().exportAllDataTo(parentFile);
  }
}
