package io.github.singlerr;

import com.fastcat.labyrintale.utils.ReflectionUtils;
import com.opencsv.CSVWriter;
import com.opencsv.exceptions.CsvDataTypeMismatchException;
import com.opencsv.exceptions.CsvRequiredFieldEmptyException;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import javax.swing.*;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;

@NoArgsConstructor(access = AccessLevel.PRIVATE)
public final class DataExportManager {

  private static DataExportManager instance;

  private List<Class<? extends DataExporter<?>>> registeredExporters;

  /***
   * Get instance of singleton object.
   * @return singleton object
   */
  public static DataExportManager getInstance() {
    if (instance == null) instance = new DataExportManager();
    return instance;
  }

  /***
   * Register {@link DataExporter} classes.
   * @param exporterClasses array of {@link DataExporter} implementation class
   */
  public void registerDataExporters(Class<? extends DataExporter<?>>... exporterClasses) {
    registeredExporters = Arrays.asList(exporterClasses);
  }

  /***
   * Export all data to {@param parentFile}. File name will be {@link DataExporter#getTargetFileName()}
   * @param parentFile parent directory of exported data files
   */
  public void exportAllDataTo(File parentFile) {
    if (registeredExporters == null)
      throw new RuntimeException("No DataExporter classes are registered.");
    // To allow access one file from multiple DataExporters, we need to store CSVWriter by its file
    // name and get writer later.
    HashMap<String, CSVWriter> writerMap = new HashMap<>();
    ArrayList<DataExporter<?>> exporters = new ArrayList<>();
    for (Class<? extends DataExporter<?>> registeredExporter : registeredExporters) {
      try {
        DataExporter<?> exporter =
            (DataExporter<?>) ReflectionUtils.instantiateByEmptyConstructor(registeredExporter);
        exporters.add(exporter);
        writerMap.put(
            exporter.getTargetFileName(),
            new CSVWriter(
                new FileWriter(
                    new File(parentFile, exporter.getTargetFileName()), Charset.forName("MS949"))));
      } catch (Exception ex) {
        JOptionPane.showMessageDialog(
            null,
            "Cannot export "
                + registeredExporter.getName()
                + " something went wrong: "
                + ex.getMessage());
      }
    }

    for (DataExporter<?> exporter : exporters) {
      try {
        exporter.export(writerMap.get(exporter.getTargetFileName()));
      } catch (CsvRequiredFieldEmptyException | CsvDataTypeMismatchException e) {
        JOptionPane.showMessageDialog(
            null,
            "Cannot export "
                + exporter.getExportedClass().getName()
                + " something went wrong: "
                + e.getMessage());
      }
    }

    for (CSVWriter writer : writerMap.values()) {
      try {
        writer.close();
      } catch (IOException e) {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }
    }
  }
}
